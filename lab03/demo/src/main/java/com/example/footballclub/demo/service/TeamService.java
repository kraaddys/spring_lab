package com.example.footballclub.demo.service;

import com.example.footballclub.demo.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamService {

    private final JdbcTemplate jdbcTemplate;
    private final PlayerService playerService;
    private final CoachService coachService;
    private final LeagueService leagueService;

    public TeamService(JdbcTemplate jdbcTemplate, PlayerService playerService, CoachService coachService, LeagueService leagueService) {
        this.jdbcTemplate = jdbcTemplate;
        this.playerService = playerService;
        this.coachService = coachService;
        this.leagueService = leagueService;
    }

    private RowMapper<SimpleTeam> teamRowMapper = (rs, rowNum) -> new SimpleTeam(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("coach_id"),
            rs.getLong("league_id")
    );

    public List<TeamDTO> getAllTeams() {
        String sql = "SELECT * FROM team";
        List<SimpleTeam> simpleTeams = jdbcTemplate.query(sql, teamRowMapper);
        return simpleTeams.stream().map(this::mapToTeamDTO).toList();
    }

    public TeamDTO getTeamById(Long id) {
        String sql = "SELECT * FROM team WHERE id = ?";
        SimpleTeam simpleTeam = jdbcTemplate.queryForObject(sql, teamRowMapper, id);
        return mapToTeamDTO(simpleTeam);
    }

    public void createTeam(TeamDTO teamDTO, List<Long> playerIds) {
        // Проверяем, что тренер и лига существуют
        coachService.getCoachById(teamDTO.getCoach().getId());
        leagueService.getLeagueById(teamDTO.getLeague().getId());

        // Проверяем существование игроков
        for (Long playerId : playerIds) {
            playerService.getPlayerById(playerId);
        }

        // Создаем команду
        String sql = "INSERT INTO team (name, coach_id, league_id) VALUES (?, ?, ?) RETURNING id";
        Long teamId = jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> rs.getLong(1),
                teamDTO.getName(),
                teamDTO.getCoach().getId(),
                teamDTO.getLeague().getId()
        );

        // Привязываем игроков к команде
        for (Long playerId : playerIds) {
            jdbcTemplate.update("INSERT INTO team_player (team_id, player_id) VALUES (?, ?)", teamId, playerId);
        }
    }

    public void updateTeam(Long id, TeamDTO teamDTO, List<Long> playerIds) {
        // Проверка существования
        getTeamById(id);
        coachService.getCoachById(teamDTO.getCoach().getId());
        leagueService.getLeagueById(teamDTO.getLeague().getId());

        for (Long playerId : playerIds) {
            playerService.getPlayerById(playerId);
        }

        // Обновляем команду
        String sql = "UPDATE team SET name = ?, coach_id = ?, league_id = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                teamDTO.getName(),
                teamDTO.getCoach().getId(),
                teamDTO.getLeague().getId(),
                id
        );

        // Очищаем старые связи с игроками
        jdbcTemplate.update("DELETE FROM team_player WHERE team_id = ?", id);

        // Привязываем новых игроков
        for (Long playerId : playerIds) {
            jdbcTemplate.update("INSERT INTO team_player (team_id, player_id) VALUES (?, ?)", id, playerId);
        }
    }

    public void deleteTeam(Long id) {
        getTeamById(id);
        jdbcTemplate.update("DELETE FROM team_player WHERE team_id = ?", id);
        jdbcTemplate.update("DELETE FROM team WHERE id = ?", id);
    }

    private List<PlayerDTO> getPlayersForTeam(Long teamId) {
        String sql = "SELECT p.* FROM player p JOIN team_player tp ON p.id = tp.player_id WHERE tp.team_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new PlayerDTO(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("position"),
                rs.getInt("age")
        ), teamId);
    }

    private TeamDTO mapToTeamDTO(SimpleTeam simpleTeam) {
        CoachDTO coach = coachService.getCoachById(simpleTeam.getCoachId());
        LeagueDTO league = leagueService.getLeagueById(simpleTeam.getLeagueId());
        List<PlayerDTO> players = getPlayersForTeam(simpleTeam.getId());

        return new TeamDTO(
                simpleTeam.getId(),
                simpleTeam.getName(),
                coach,
                league,
                players
        );
    }
}
