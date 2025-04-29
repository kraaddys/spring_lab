package com.example.footballclub.demo.service;

import java.time.LocalDate;

public class SimpleMatch {
    private Long id;
    private Long teamHomeId;
    private Long teamAwayId;
    private LocalDate matchDate;
    private int scoreHome;
    private int scoreAway;

    public SimpleMatch(Long id, Long teamHomeId, Long teamAwayId, LocalDate matchDate, int scoreHome, int scoreAway) {
        this.id = id;
        this.teamHomeId = teamHomeId;
        this.teamAwayId = teamAwayId;
        this.matchDate = matchDate;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }

    public Long getId() {
        return id;
    }

    public Long getTeamHomeId() {
        return teamHomeId;
    }

    public Long getTeamAwayId() {
        return teamAwayId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }
}
