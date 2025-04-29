package com.example.footballclub.demo.controller;

import com.example.footballclub.demo.dto.LeagueDTO;
import com.example.footballclub.demo.service.LeagueService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    public List<LeagueDTO> getAllLeagues() {
        return leagueService.getAllLeagues();
    }

    @GetMapping("/{id}")
    public LeagueDTO getLeagueById(@PathVariable Long id) {
        return leagueService.getLeagueById(id);
    }

    @PostMapping
    public void createLeague(@Valid @RequestBody LeagueDTO leagueDTO) {
        leagueService.createLeague(leagueDTO);
    }

    @PutMapping("/{id}")
    public void updateLeague(@PathVariable Long id, @Valid @RequestBody LeagueDTO leagueDTO) {
        leagueService.updateLeague(id, leagueDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLeague(@PathVariable Long id) {
        leagueService.deleteLeague(id);
    }
}
