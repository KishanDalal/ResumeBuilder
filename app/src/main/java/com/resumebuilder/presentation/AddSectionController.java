package com.resumebuilder.presentation;

import com.resumebuilder.application.AddSectionUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class AddSectionController {
    private AddSectionUseCase addSectionUseCase;
    private Long resumeId;

    @FXML
    private TextField sectionTitleField;

    public void init(AddSectionUseCase addSectionUseCase, Long resumeId) {
        this.addSectionUseCase = addSectionUseCase;
        this.resumeId = resumeId;
    }

    @FXML
    private void handleAddSectionButton(ActionEvent event) {
        String title = sectionTitleField.getText();
        try {
            addSectionUseCase.addSection(resumeId, title);
            Stage stage = (Stage) sectionTitleField.getScene().getWindow();
            stage.close();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
