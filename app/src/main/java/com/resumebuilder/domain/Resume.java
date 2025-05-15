package com.resumebuilder.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Resume implements java.io.Serializable {
    private Long id;
    private String name;
    private List<Section> sections = new ArrayList<>();
    private ContactInformation contactInformation;
    private String summary; // Or objective
    private List<Skill> skills = new ArrayList<>(); // Added skills
    private List<String> languages = new ArrayList<>(); // Added languages

    public Resume() {
    }

    public Resume(String name) {
        this.name = name;
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

    public List<Section> getSections() {
        return Collections.unmodifiableList(sections); // Return an unmodifiable list
    }

    public void setSections(List<Section> sections) {
        this.sections = new ArrayList<>(sections); // Create a copy to avoid external modification
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Skill> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public void setSkills(List<Skill> skills) {
        this.skills = new ArrayList<>(skills);
    }

    public List<String> getLanguages() {
        return Collections.unmodifiableList(languages);
    }

    public void setLanguages(List<String> languages) {
        this.languages = new ArrayList<>(languages);
    }

    public Section addSection(Section section) {
        Objects.requireNonNull(section, "Section cannot be null");
        // Assign a unique ID if not set
        if (section.getId() == null) {
            section.setId(System.currentTimeMillis() + sections.size());
        }
        this.sections.add(section);
        return section;
    }

    public void addSkill(Skill skill) {
        Objects.requireNonNull(skill, "Skill cannot be null");
        this.skills.add(skill);
    }

    public void addLanguage(String language) {
        Objects.requireNonNull(language, "Language cannot be null");
        this.languages.add(language);
    }

    public void removeSection(Section section) {
        Objects.requireNonNull(section, "Section cannot be null");
        this.sections.remove(section);
    }
}