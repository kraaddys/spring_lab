package com.example.footballclub.demo.service;

import com.example.footballclub.demo.dto.LeagueDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeagueService {

    private final JdbcTemplate jdbcTemplate;

    public LeagueService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<LeagueDTO> leagueRowMapper = (rs, rowNum) -> new LeagueDTO(
            rs.getLong("id"),
            rs.getString("name")
    );

    public List<LeagueDTO> getAllLeagues() {
        String sql = "SELECT * FROM league";
        return jdbcTemplate.query(sql, leagueRowMapper);
    }

    public LeagueDTO getLeagueById(Long id) {
        String sql = "SELECT * FROM league WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, leagueRowMapper, id);
    }

    public void createLeague(LeagueDTO leagueDTO) {
        String sql = "INSERT INTO league (name) VALUES (?)";
        jdbcTemplate.update(sql, leagueDTO.getName());
    }

    public void updateLeague(Long id, LeagueDTO leagueDTO) {
        String sql = "UPDATE league SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, leagueDTO.getName(), id);
    }

    public void deleteLeague(Long id) {
        String sql = "DELETE FROM league WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
