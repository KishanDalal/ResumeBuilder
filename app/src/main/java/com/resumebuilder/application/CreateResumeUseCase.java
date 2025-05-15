package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.ContactInformation;
import com.resumebuilder.domain.Section;
// import org.springframework.stereotype.Service; // Uncomment if using Spring

//@Service // Uncomment if using Spring
public class CreateResumeUseCase {
    private final ResumeRepository resumeRepository;

    public CreateResumeUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume createResume(String name, ContactInformation contactInfo) {
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Resume name cannot be null or empty.");
        }
        if (contactInfo == null) {
            throw new IllegalArgumentException("Contact information cannot be null");
        }
        // Create the domain object
        Resume resume = new Resume(name);
        resume.setContactInformation(contactInfo);
        // Add an initial section (optional)
        Section objectiveSection = new Section("Objective");
        resume.addSection(objectiveSection);
        // Save the new resume
        resumeRepository.save(resume);
        return resume;
    }
}
