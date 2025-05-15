package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
import com.resumebuilder.domain.WorkExperience;
import com.resumebuilder.domain.DateRange;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.Objects;

//@Service // Uncomment if using Spring
public class EditWorkExperienceUseCase {
    private final ResumeRepository resumeRepository;

    public EditWorkExperienceUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void editWorkExperience(Long resumeId, Long sectionId, int workExperienceIndex, String newJobTitle, String newCompany, DateRange newDateRange, String newLocation) {
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
        if (newJobTitle == null || newJobTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Job title cannot be null or empty.");
        }
        if (newCompany == null || newCompany.trim().isEmpty()) {
            throw new IllegalArgumentException("Company cannot be null or empty.");
        }
        Objects.requireNonNull(newDateRange, "Date range cannot be null.");
        if (newLocation == null || newLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty.");
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
        // Update the work experience (as text for now)
        String updatedWorkExperience = newJobTitle + ", " + newCompany + ", " + newDateRange.toString() + ", " + newLocation;
        targetSection.getContent().set(workExperienceIndex, updatedWorkExperience);
        // Save
        resumeRepository.save(resume);
    }
}
