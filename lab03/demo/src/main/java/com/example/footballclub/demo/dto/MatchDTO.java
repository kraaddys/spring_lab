package com.example.footballclub.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MatchDTO {
    private Long id;

    @NotNull
    private TeamDTO homeTeam;

    @NotNull
    private TeamDTO awayTeam;

    @NotNull
    private LocalDate matchDate;

    private int scoreHome;
    private int scoreAway;

    public MatchDTO() {}

    public MatchDTO(Long id, TeamDTO homeTeam, TeamDTO awayTeam, LocalDate matchDate, int scoreHome, int scoreAway) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TeamDTO getHomeTeam() { return homeTeam; }
    public void setHomeTeam(TeamDTO homeTeam) { this.homeTeam = homeTeam; }

    public TeamDTO getAwayTeam() { return awayTeam; }
    public void setAwayTeam(TeamDTO awayTeam) { this.awayTeam = awayTeam; }

    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }

    public int getScoreHome() { return scoreHome; }
    public void setScoreHome(int scoreHome) { this.scoreHome = scoreHome; }

    public int getScoreAway() { return scoreAway; }
    public void setScoreAway(int scoreAway) { this.scoreAway = scoreAway; }
}
