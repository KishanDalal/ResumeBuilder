package com.resumebuilder.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Section implements java.io.Serializable {
    private Long id;
    private String title;
    private List<String> content = new ArrayList<>();

    public Section() {
    }

    public Section(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return Collections.unmodifiableList(content);
    }

    public void setContent(List<String> content) {
         this.content = new ArrayList<>(content);
    }

    public void addContent(String text) {
        Objects.requireNonNull(text, "Content text cannot be null");
        this.content.add(text);
    }

    public void removeContent(String text) {
        Objects.requireNonNull(text, "Content text cannot be null");
        this.content.remove(text);
    }
}

