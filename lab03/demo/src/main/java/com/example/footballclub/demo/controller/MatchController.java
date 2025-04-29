package com.example.footballclub.demo.controller;

import com.example.footballclub.demo.dto.MatchDTO;
import com.example.footballclub.demo.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public MatchDTO getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @PostMapping
    public void createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        matchService.createMatch(matchDTO);
    }

    @PutMapping("/{id}")
    public void updateMatch(@PathVariable Long id, @Valid @RequestBody MatchDTO matchDTO) {
        matchService.updateMatch(id, matchDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }
}
