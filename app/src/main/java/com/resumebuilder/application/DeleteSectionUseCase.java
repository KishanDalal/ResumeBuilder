package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.Objects;

//@Service // Uncomment if using Spring
public class DeleteSectionUseCase {
    private final ResumeRepository resumeRepository;

    public DeleteSectionUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void deleteSection(Long resumeId, Long sectionId) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null.");
        }
        // Get the resume
        Resume resume = resumeRepository.findById(resumeId);
        if (resume == null) {
            throw new IllegalArgumentException("Resume with ID " + resumeId + " not found.");
        }
        // Find the section to delete
        Section sectionToDelete = null;
        for (Section section : resume.getSections()) {
            if (section.getId() != null && section.getId().equals(sectionId)) {
                sectionToDelete = section;
                break;
            }
        }
        if (sectionToDelete == null) {
            throw new IllegalArgumentException("Section with ID " + sectionId + " not found in resume.");
        }
        // Remove the section
        resume.removeSection(sectionToDelete);
        // Save the updated resume
        resumeRepository.save(resume);
    }
}
