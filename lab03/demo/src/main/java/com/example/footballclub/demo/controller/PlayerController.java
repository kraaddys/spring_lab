package com.example.footballclub.demo.controller;

import com.example.footballclub.demo.dto.PlayerDTO;
import com.example.footballclub.demo.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public void createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO);
    }

    @PutMapping("/{id}")
    public void updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
        playerService.updatePlayer(id, playerDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
