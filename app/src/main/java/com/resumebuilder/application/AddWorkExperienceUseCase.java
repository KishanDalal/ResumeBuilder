package com.resumebuilder.application;

import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
import com.resumebuilder.domain.WorkExperience;
import com.resumebuilder.domain.DateRange;
// import org.springframework.stereotype.Service; // Uncomment if using Spring
import java.util.Objects;

//@Service // Uncomment if using Spring
public class AddWorkExperienceUseCase {
    private final ResumeRepository resumeRepository;

    public AddWorkExperienceUseCase(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void addWorkExperience(Long resumeId, Long sectionId, String jobTitle, String company, DateRange dateRange, String location) {
        // Validate input
        if (resumeId == null) {
            throw new IllegalArgumentException("Resume ID cannot be null.");
        }
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null.");
        }
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Job title cannot be null or empty.");
        }
        if (company == null || company.trim().isEmpty()) {
            throw new IllegalArgumentException("Company cannot be null or empty.");
        }
        Objects.requireNonNull(dateRange, "Date range cannot be null.");
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
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
        // Create work experience
        WorkExperience workExperience = new WorkExperience(jobTitle, company, dateRange, location);
        // Add to section (as text for now)
        targetSection.addContent(jobTitle + ", " + company + ", " + dateRange.toString() + ", " + location);
        // Save
        resumeRepository.save(resume);
    }
}
