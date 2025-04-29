package com.example.footballclub.demo.service;

import com.example.footballclub.demo.dto.PlayerDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    private final JdbcTemplate jdbcTemplate;

    public PlayerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<PlayerDTO> playerRowMapper = (rs, rowNum) -> new PlayerDTO(
            rs.getLong("id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("position"),
            rs.getInt("age")
    );

    public List<PlayerDTO> getAllPlayers() {
        String sql = "SELECT * FROM player";
        return jdbcTemplate.query(sql, playerRowMapper);
    }

    public PlayerDTO getPlayerById(Long id) {
        String sql = "SELECT * FROM player WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, playerRowMapper, id);
    }

    public void createPlayer(PlayerDTO playerDTO) {
        String sql = "INSERT INTO player (first_name, last_name, position, age) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, playerDTO.getFirstName(), playerDTO.getLastName(), playerDTO.getPosition(), playerDTO.getAge());
    }

    public void updatePlayer(Long id, PlayerDTO playerDTO) {
        String sql = "UPDATE player SET first_name = ?, last_name = ?, position = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, playerDTO.getFirstName(), playerDTO.getLastName(), playerDTO.getPosition(), playerDTO.getAge(), id);
    }

    public void deletePlayer(Long id) {
        String sql = "DELETE FROM player WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
