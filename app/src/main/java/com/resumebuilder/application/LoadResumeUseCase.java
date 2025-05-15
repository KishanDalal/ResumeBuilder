package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
// import org.springframework.stereotype.Service; // Uncomment if using Spring

//@Service // Uncomment if using Spring
public class LoadResumeUseCase {
    private final ResumeRepository resumeRepository;

    public LoadResumeUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume loadResume(Long resumeId) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        // Load the resume using the repository
        Resume resume = resumeRepository.findById(resumeId);
        if (resume == null) {
            throw new IllegalArgumentException("Resume not found");
        }
        return resume;
    }
}
