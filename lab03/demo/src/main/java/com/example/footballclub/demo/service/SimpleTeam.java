package com.example.footballclub.demo.service;

public class SimpleTeam {
    private Long id;
    private String name;
    private Long coachId;
    private Long leagueId;

    public SimpleTeam(Long id, String name, Long coachId, Long leagueId) {
        this.id = id;
        this.name = name;
        this.coachId = coachId;
        this.leagueId = leagueId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCoachId() {
        return coachId;
    }

    public Long getLeagueId() {
        return leagueId;
    }
}
