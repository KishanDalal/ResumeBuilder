package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.ContactInformation;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.Objects;

//@Service // Uncomment if using Spring
public class EditResumeUseCase {
    private final ResumeRepository resumeRepository;

    public EditResumeUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void editResume(Long resumeId, String newName, ContactInformation newContactInfo, String newSummary) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Resume name cannot be null or empty.");
        }
        Objects.requireNonNull(newContactInfo, "Contact information cannot be null");
        // Get the resume from the repository
        Resume resume = resumeRepository.findById(resumeId);
        if (resume == null) {
            throw new IllegalArgumentException("Resume with ID " + resumeId + " not found.");
        }
        // Update the resume
        resume.setName(newName);
        resume.setContactInformation(newContactInfo);
        resume.setSummary(newSummary);
        // Save the updated resume
        resumeRepository.save(resume);
    }
}
