package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.Objects;

//@Service // Uncomment if using Spring
public class AddSectionUseCase {
    private final ResumeRepository resumeRepository;

    public AddSectionUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void addSection(Long resumeId, String title) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Section title cannot be null or empty.");
        }
        // Get the resume from the repository
        Resume resume = resumeRepository.findById(resumeId);
        if (resume == null) {
            throw new IllegalArgumentException("Resume with ID " + resumeId + " not found.");
        }
        // Create and add the section
        Section newSection = new Section(title);
        resume.addSection(newSection);
        // Save the updated resume
        resumeRepository.save(resume);
    }
}
