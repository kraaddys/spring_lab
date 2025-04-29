package com.example.footballclub.demo.controller;

import com.example.footballclub.demo.dto.TeamDTO;
import com.example.footballclub.demo.dto.TeamCreateRequest;
import com.example.footballclub.demo.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamDTO getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public void createTeam(@Valid @RequestBody TeamCreateRequest request) {
        teamService.createTeam(request.getTeamDTO(), request.getPlayerIds());
    }

    @PutMapping("/{id}")
    public void updateTeam(@PathVariable Long id, @Valid @RequestBody TeamCreateRequest request) {
        teamService.updateTeam(id, request.getTeamDTO(), request.getPlayerIds());
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
