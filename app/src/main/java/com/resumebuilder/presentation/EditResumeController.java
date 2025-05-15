package com.resumebuilder.presentation;

import com.resumebuilder.application.*;
import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.List;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class EditResumeController {
    private EditResumeUseCase editResumeUseCase;
    private AddSectionUseCase addSectionUseCase;
    private EditSectionUseCase editSectionUseCase;
    private DeleteSectionUseCase deleteSectionUseCase;
    private LoadResumeUseCase loadResumeUseCase;
    private SaveResumeUseCase saveResumeUseCase;
    private Long resumeId;

    @FXML
    private TextField resumeNameField;
    @FXML
    private VBox sectionsVBox;
    private Resume currentResume;

    public void init(EditResumeUseCase editResumeUseCase, AddSectionUseCase addSectionUseCase, EditSectionUseCase editSectionUseCase, DeleteSectionUseCase deleteSectionUseCase, AddWorkExperienceUseCase addWorkExperienceUseCase, EditWorkExperienceUseCase editWorkExperienceUseCase, DeleteWorkExperienceUseCase deleteWorkExperienceUseCase, LoadResumeUseCase loadResumeUseCase, SaveResumeUseCase saveResumeUseCase, Long resumeId) {
        this.editResumeUseCase = editResumeUseCase;
        this.addSectionUseCase = addSectionUseCase;
        this.editSectionUseCase = editSectionUseCase;
        this.deleteSectionUseCase = deleteSectionUseCase;
        this.loadResumeUseCase = loadResumeUseCase;
        this.saveResumeUseCase = saveResumeUseCase;
        this.resumeId = resumeId;
        loadResumeData();
    }

    private void loadResumeData() {
        currentResume = loadResumeUseCase.loadResume(resumeId);
        resumeNameField.setText(currentResume.getName());
        displaySections();
    }

    private void displaySections() {
        sectionsVBox.getChildren().clear();
        for (Section section : currentResume.getSections()) {
            TitledPane sectionPane = new TitledPane();
            sectionPane.setText(section.getTitle());
            VBox sectionContent = new VBox();
            for (String content : section.getContent()) {
                sectionContent.getChildren().add(new Label(content));
            }
            sectionPane.setContent(sectionContent);
            Button editSectionButton = new Button("Edit Section");
            editSectionButton.setOnAction(event -> handleEditSection(section.getId(), event));
            Button deleteSectionButton = new Button("Delete Section");
            deleteSectionButton.setOnAction(event -> handleDeleteSection(section.getId(), event));
            HBox buttonBox = new HBox(editSectionButton, deleteSectionButton);
            sectionContent.getChildren().add(buttonBox);
            sectionsVBox.getChildren().add(sectionPane);
        }
    }

    @FXML
    private void handleSaveResumeButton() {
        String newName = resumeNameField.getText();
        try {
            editResumeUseCase.editResume(resumeId, newName, currentResume.getContactInformation(), currentResume.getSummary());
            saveResumeUseCase.saveResume(currentResume);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Resume saved successfully!");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddSectionButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_section.fxml"));
            Parent root = loader.load();
            AddSectionController addSectionController = loader.getController();
            addSectionController.init(addSectionUseCase, resumeId);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Section");
            stage.showAndWait();
            loadResumeData();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Add Section screen.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void handleEditSection(Long sectionId, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit_section.fxml"));
            Parent root = loader.load();
            EditSectionController editSectionController = loader.getController();
            editSectionController.init(editSectionUseCase, resumeId, sectionId, currentResume);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Section");
            stage.showAndWait();
            loadResumeData();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Edit Section screen.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void handleDeleteSection(Long sectionId, ActionEvent event) {
        try {
            deleteSectionUseCase.deleteSection(resumeId, sectionId);
            saveResumeUseCase.saveResume(currentResume);
            loadResumeData();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
