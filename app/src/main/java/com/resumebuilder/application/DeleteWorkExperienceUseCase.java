package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
// import org.springframework.stereotype.Service; // Uncomment if using Spring

//@Service // Uncomment if using Spring
public class DeleteWorkExperienceUseCase {
    private final ResumeRepository resumeRepository;

    public DeleteWorkExperienceUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void deleteWorkExperience(Long resumeId, Long sectionId, int workExperienceIndex) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null.");
        }
        if (workExperienceIndex < 0) {
            throw new IllegalArgumentException("Work experience index cannot be negative.");
        }
        // Get the resume
        Resume resume = resumeRepository.findById(resumeId);
        if (resume == null) {
            throw new IllegalArgumentException("Resume with ID " + resumeId + " not found.");
        }
        // Find the section
        Section targetSection = null;
        for (Section section : resume.getSections()) {
            if (section.getId() != null && section.getId().equals(sectionId)) {
                targetSection = section;
                break;
            }
        }
        if (targetSection == null) {
            throw new IllegalArgumentException("Section with ID " + sectionId + " not found in resume.");
        }
        // Error: The section does not contain work experience.
        if (targetSection.getContent().isEmpty()) {
            throw new IllegalArgumentException("Section with ID " + sectionId + " does not contain work experience.");
        }
        // Delete the work experience (as text for now)
        targetSection.getContent().remove(workExperienceIndex);
        // Save
        resumeRepository.save(resume);
    }
}
