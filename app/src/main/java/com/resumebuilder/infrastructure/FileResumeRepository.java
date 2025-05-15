package com.resumebuilder.infrastructure;

import com.resumebuilder.application.ResumeRepository;
import com.resumebuilder.domain.Resume;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileResumeRepository implements ResumeRepository {
    private static final String DATA_FILE = "resumes.dat";

    @Override
    public Resume findById(Long id) {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Resume resume;
            while ((resume = (Resume) ois.readObject()) != null) {
                if (resume.getId() != null && resume.getId().equals(id)) {
                    return resume;
                }
            }
        } catch (EOFException e) {
            // Expected end of file, not an error
            return null;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading resume: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void save(Resume resume) {
        List<Resume> allResumes = new ArrayList<>();
        File file = new File(DATA_FILE);
        // Read existing resumes
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Resume existingResume;
                while ((existingResume = (Resume) ois.readObject()) != null) {
                    allResumes.add(existingResume);
                }
            } catch (EOFException e) {
                // Expected end of file
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Error loading existing resumes: " + e.getMessage(), e);
            }
        }
        // Update or add the resume
        boolean found = false;
        for (int i = 0; i < allResumes.size(); i++) {
            if (allResumes.get(i).getId() != null && allResumes.get(i).getId().equals(resume.getId())) {
                allResumes.set(i, resume); // Update existing
                found = true;
                break;
            }
        }
        if (!found) {
            if (resume.getId() == null) {
                resume.setId(System.currentTimeMillis()); // Simple ID generation
            }
            allResumes.add(resume); // Add new
        }
        // Write all resumes back to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Resume r : allResumes) {
                oos.writeObject(r);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving resumes: " + e.getMessage(), e);
        }
    }
}
