package com.example.footballclub.demo.service;

import com.example.footballclub.demo.dto.CoachDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoachService {

    private final JdbcTemplate jdbcTemplate;

    public CoachService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<CoachDTO> coachRowMapper = (rs, rowNum) -> new CoachDTO(
            rs.getLong("id"),
            rs.getString("first_name"),
            rs.getString("last_name")
    );

    public List<CoachDTO> getAllCoaches() {
        String sql = "SELECT * FROM coach";
        return jdbcTemplate.query(sql, coachRowMapper);
    }

    public CoachDTO getCoachById(Long id) {
        String sql = "SELECT * FROM coach WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, coachRowMapper, id);
    }

    public void createCoach(CoachDTO coachDTO) {
        String sql = "INSERT INTO coach (first_name, last_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, coachDTO.getFirstName(), coachDTO.getLastName());
    }

    public void updateCoach(Long id, CoachDTO coachDTO) {
        String sql = "UPDATE coach SET first_name = ?, last_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, coachDTO.getFirstName(), coachDTO.getLastName(), id);
    }

    public void deleteCoach(Long id) {
        String sql = "DELETE FROM coach WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
