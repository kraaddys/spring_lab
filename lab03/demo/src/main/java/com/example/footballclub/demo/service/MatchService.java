package com.example.footballclub.demo.service;

import com.example.footballclub.demo.dto.MatchDTO;
import com.example.footballclub.demo.dto.TeamDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchService {

    private final JdbcTemplate jdbcTemplate;
    private final TeamService teamService;

    public MatchService(JdbcTemplate jdbcTemplate, TeamService teamService) {
        this.jdbcTemplate = jdbcTemplate;
        this.teamService = teamService;
    }

    private RowMapper<SimpleMatch> matchRowMapper = (rs, rowNum) -> new SimpleMatch(
            rs.getLong("id"),
            rs.getLong("team_home_id"),
            rs.getLong("team_away_id"),
            rs.getDate("match_date").toLocalDate(),
            rs.getInt("score_home"),
            rs.getInt("score_away")
    );

    public List<MatchDTO> getAllMatches() {
        String sql = "SELECT * FROM match";
        List<SimpleMatch> simpleMatches = jdbcTemplate.query(sql, matchRowMapper);
        return simpleMatches.stream().map(this::mapToMatchDTO).toList();
    }

    public MatchDTO getMatchById(Long id) {
        String sql = "SELECT * FROM match WHERE id = ?";
        SimpleMatch simpleMatch = jdbcTemplate.queryForObject(sql, matchRowMapper, id);
        return mapToMatchDTO(simpleMatch);
    }

    public void createMatch(MatchDTO matchDTO) {
        teamService.getTeamById(matchDTO.getHomeTeam().getId());
        teamService.getTeamById(matchDTO.getAwayTeam().getId());

        String sql = "INSERT INTO match (team_home_id, team_away_id, match_date, score_home, score_away) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                matchDTO.getHomeTeam().getId(),
                matchDTO.getAwayTeam().getId(),
                matchDTO.getMatchDate(),
                matchDTO.getScoreHome(),
                matchDTO.getScoreAway()
        );
    }

    public void updateMatch(Long id, MatchDTO matchDTO) {
        getMatchById(id);

        teamService.getTeamById(matchDTO.getHomeTeam().getId());
        teamService.getTeamById(matchDTO.getAwayTeam().getId());

        String sql = "UPDATE match SET team_home_id = ?, team_away_id = ?, match_date = ?, score_home = ?, score_away = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                matchDTO.getHomeTeam().getId(),
                matchDTO.getAwayTeam().getId(),
                matchDTO.getMatchDate(),
                matchDTO.getScoreHome(),
                matchDTO.getScoreAway(),
                id
        );
    }

    public void deleteMatch(Long id) {
        getMatchById(id);
        String sql = "DELETE FROM match WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private MatchDTO mapToMatchDTO(SimpleMatch simpleMatch) {
        TeamDTO homeTeam = teamService.getTeamById(simpleMatch.getTeamHomeId());
        TeamDTO awayTeam = teamService.getTeamById(simpleMatch.getTeamAwayId());

        return new MatchDTO(
                simpleMatch.getId(),
                homeTeam,
                awayTeam,
                simpleMatch.getMatchDate(),
                simpleMatch.getScoreHome(),
                simpleMatch.getScoreAway()
        );
    }
}
