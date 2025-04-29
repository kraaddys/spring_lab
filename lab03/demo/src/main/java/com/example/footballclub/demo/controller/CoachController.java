package com.example.footballclub.demo.controller;

import com.example.footballclub.demo.dto.CoachDTO;
import com.example.footballclub.demo.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public List<CoachDTO> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @GetMapping("/{id}")
    public CoachDTO getCoachById(@PathVariable Long id) {
        return coachService.getCoachById(id);
    }

    @PostMapping
    public void createCoach(@Valid @RequestBody CoachDTO coachDTO) {
        coachService.createCoach(coachDTO);
    }

    @PutMapping("/{id}")
    public void updateCoach(@PathVariable Long id, @Valid @RequestBody CoachDTO coachDTO) {
        coachService.updateCoach(id, coachDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
    }
}
