package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.Objects;

//@Service // Uncomment if using Spring
public class SaveResumeUseCase {
    private final ResumeRepository resumeRepository;

    public SaveResumeUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void saveResume(Resume resume) {
        // Validate input
        Objects.requireNonNull(resume, "Resume cannot be null.");
        // Save the resume using the repository
        resumeRepository.save(resume);
    }
}
