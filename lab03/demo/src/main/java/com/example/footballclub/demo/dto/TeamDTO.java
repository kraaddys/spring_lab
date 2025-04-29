package com.example.footballclub.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class TeamDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private CoachDTO coach;

    @NotNull
    private LeagueDTO league;

    private List<PlayerDTO> players;

    public TeamDTO() {}

    public TeamDTO(Long id, String name, CoachDTO coach, LeagueDTO league, List<PlayerDTO> players) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.league = league;
        this.players = players;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CoachDTO getCoach() { return coach; }
    public void setCoach(CoachDTO coach) { this.coach = coach; }

    public LeagueDTO getLeague() { return league; }
    public void setLeague(LeagueDTO league) { this.league = league; }

    public List<PlayerDTO> getPlayers() { return players; }
    public void setPlayers(List<PlayerDTO> players) { this.players = players; }
}
