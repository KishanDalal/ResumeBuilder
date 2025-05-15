package com.resumebuilder.presentation;

import com.resumebuilder.application.CreateResumeUseCase;
import com.resumebuilder.application.EditResumeUseCase;
import com.resumebuilder.application.AddSectionUseCase;
import com.resumebuilder.application.EditSectionUseCase;
import com.resumebuilder.application.DeleteSectionUseCase;
import com.resumebuilder.application.AddWorkExperienceUseCase;
import com.resumebuilder.application.EditWorkExperienceUseCase;
import com.resumebuilder.application.DeleteWorkExperienceUseCase;
import com.resumebuilder.application.LoadResumeUseCase;
import com.resumebuilder.application.SaveResumeUseCase;
import com.resumebuilder.domain.Resume;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class CreateResumeController {
    private CreateResumeUseCase createResumeUseCase;
    private EditResumeUseCase editResumeUseCase;
    private AddSectionUseCase addSectionUseCase;
    private EditSectionUseCase editSectionUseCase;
    private DeleteSectionUseCase deleteSectionUseCase;
    private AddWorkExperienceUseCase addWorkExperienceUseCase;
    private EditWorkExperienceUseCase editWorkExperienceUseCase;
    private DeleteWorkExperienceUseCase deleteWorkExperienceUseCase;
    private LoadResumeUseCase loadResumeUseCase;
    private SaveResumeUseCase saveResumeUseCase;

    @FXML
    private TextField resumeNameField;

    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextArea summaryField;

    public void init(CreateResumeUseCase createResumeUseCase,
                     EditResumeUseCase editResumeUseCase,
                     AddSectionUseCase addSectionUseCase,
                     EditSectionUseCase editSectionUseCase,
                     DeleteSectionUseCase deleteSectionUseCase,
                     AddWorkExperienceUseCase addWorkExperienceUseCase,
                     EditWorkExperienceUseCase editWorkExperienceUseCase,
                     DeleteWorkExperienceUseCase deleteWorkExperienceUseCase,
                     LoadResumeUseCase loadResumeUseCase,
                     SaveResumeUseCase saveResumeUseCase) {
        this.createResumeUseCase = createResumeUseCase;
        this.editResumeUseCase = editResumeUseCase;
        this.addSectionUseCase = addSectionUseCase;
        this.editSectionUseCase = editSectionUseCase;
        this.deleteSectionUseCase = deleteSectionUseCase;
        this.addWorkExperienceUseCase = addWorkExperienceUseCase;
        this.editWorkExperienceUseCase = editWorkExperienceUseCase;
        this.deleteWorkExperienceUseCase = deleteWorkExperienceUseCase;
        this.loadResumeUseCase = loadResumeUseCase;
        this.saveResumeUseCase = saveResumeUseCase;
    }

    @FXML
    private void handleCreateResumeButton(ActionEvent event) {
        String resumeName = resumeNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String summary = summaryField.getText();
        try {
            // Create ContactInformation and pass to use case
            com.resumebuilder.domain.ContactInformation contactInfo = new com.resumebuilder.domain.ContactInformation(resumeName, email, phone, address);
            com.resumebuilder.domain.Resume resume = createResumeUseCase.createResume(resumeName, contactInfo);
            resume.setSummary(summary);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Resume '" + resume.getName() + "' created successfully!");
            alert.showAndWait();
            loadEditResumeScreen(resume.getId(), event);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void loadEditResumeScreen(Long resumeId, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit_resume.fxml"));
            Parent root = loader.load();
            EditResumeController editResumeController = loader.getController();
            editResumeController.init(
                editResumeUseCase,
                addSectionUseCase,
                editSectionUseCase,
                deleteSectionUseCase,
                addWorkExperienceUseCase,
                editWorkExperienceUseCase,
                deleteWorkExperienceUseCase,
                loadResumeUseCase,
                saveResumeUseCase,
                resumeId
            );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Edit Resume");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Edit Resume screen.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
