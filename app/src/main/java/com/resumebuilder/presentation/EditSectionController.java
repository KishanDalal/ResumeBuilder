package com.resumebuilder.presentation;

import com.resumebuilder.application.EditSectionUseCase;
import com.resumebuilder.domain.Resume;
import com.resumebuilder.domain.Section;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class EditSectionController {
    private EditSectionUseCase editSectionUseCase;
    private Long resumeId;
    private Long sectionId;
    private Resume resume;

    @FXML
    private TextField sectionTitleField;
    @FXML
    private TextArea sectionContentTextArea;

    public void init(EditSectionUseCase editSectionUseCase, Long resumeId, Long sectionId, Resume resume) {
        this.editSectionUseCase = editSectionUseCase;
        this.resumeId = resumeId;
        this.sectionId = sectionId;
        this.resume = resume;
        loadSectionData();
    }

    private void loadSectionData() {
        Section section = null;
        for (Section s : resume.getSections()) {
            if (s.getId() != null && s.getId().equals(sectionId)) {
                section = s;
                break;
            }
        }
        if (section != null) {
            sectionTitleField.setText(section.getTitle());
            StringBuilder sb = new StringBuilder();
            for (String content : section.getContent()) {
                sb.append(content).append("\n");
            }
            sectionContentTextArea.setText(sb.toString());
        }
    }

    @FXML
    private void handleSaveSectionButton(ActionEvent event) {
        String newTitle = sectionTitleField.getText();
        String contentText = sectionContentTextArea.getText();
        List<String> newContent = Arrays.asList(contentText.split("\n"));
        try {
            editSectionUseCase.editSection(resumeId, sectionId, newTitle, newContent);
            Stage stage = (Stage) sectionContentTextArea.getScene().getWindow();
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
