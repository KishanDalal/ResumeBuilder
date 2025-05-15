package com.resumebuilder.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class WorkExperience implements java.io.Serializable {
    private Long id;
    private String jobTitle;
    private String company;
    private DateRange dateRange;
    private String location;
    private List<String> description = new ArrayList<>();

    public WorkExperience() {
    }

    public WorkExperience(String jobTitle, String company, DateRange dateRange, String location) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.dateRange = dateRange;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDescription() {
        return Collections.unmodifiableList(description);
    }

    public void setDescription(List<String> description) {
        this.description = new ArrayList<>(description);
    }

    public void addDescription(String text) {
        Objects.requireNonNull(text, "Description text cannot be null");
        this.description.add(text);
    }
}
