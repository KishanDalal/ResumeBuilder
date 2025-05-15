package com.resumebuilder.domain;

import java.util.Objects;

public class Skill implements java.io.Serializable {
    private Long id;
    private String name;
    private String level; // e.g., "Beginner", "Intermediate", "Expert"

    public Skill() {
    }

    public Skill(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name) && Objects.equals(level, skill.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
