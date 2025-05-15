package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.List;
import java.util.Objects;

//@Service // Uncomment if using Spring
public class EditSectionUseCase {
    private final ResumeRepository resumeRepository;

    public EditSectionUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void editSection(Long resumeId, Long sectionId, String newTitle, List<String> newContent) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null.");
        }
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Section title cannot be null or empty.");
        }
        Objects.requireNonNull(newContent, "Section content cannot be null");
        // Get the resume from the repository
        Resume resume = resumeRepository.findById(resumeId);
        if (resume == null) {
            throw new IllegalArgumentException("Resume with ID " + resumeId + " not found.");
        }
        // Find the section
        Section sectionToEdit = null;
        for (Section section : resume.getSections()) {
            if (section.getId() != null && section.getId().equals(sectionId)) {
                sectionToEdit = section;
                break;
            }
        }
        if (sectionToEdit == null) {
            throw new IllegalArgumentException("Section with ID " + sectionId + " not found in resume.");
        }
        // Update the section
        sectionToEdit.setTitle(newTitle);
        sectionToEdit.setContent(newContent);
        // Save the updated resume
        resumeRepository.save(resume);
    }
}
