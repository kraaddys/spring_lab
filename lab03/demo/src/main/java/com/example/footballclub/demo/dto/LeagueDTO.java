package com.example.footballclub.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class LeagueDTO {
    private Long id;

    @NotBlank
    private String name;

    public LeagueDTO() {}

    public LeagueDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
