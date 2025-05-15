package com.resumebuilder.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Education implements java.io.Serializable {
    private Long id;
    private String institution;
    private String degree;
    private DateRange dateRange;
    private String location; // Added location
    private List<String> courses = new ArrayList<>(); // Added courses

    public Education() {
    }

    public Education(String institution, String degree, DateRange dateRange) {
        this.institution = institution;
        this.degree = degree;
        this.dateRange = dateRange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public List<String> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void setCourses(List<String> courses) {
        this.courses = new ArrayList<>(courses);
    }

    public void addCourse(String course) {
         Objects.requireNonNull(course, "Course name cannot be null");
        this.courses.add(course);
    }
}
