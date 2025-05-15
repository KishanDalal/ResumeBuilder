package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;

public interface ResumeRepository {
    Resume findById(Long id);
    void save(Resume resume);
}
