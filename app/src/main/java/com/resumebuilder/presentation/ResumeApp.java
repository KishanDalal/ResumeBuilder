package com.resumebuilder.presentation;

import com.resumebuilder.application.*;
import com.resumebuilder.infrastructure.FileResumeRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ResumeApp extends Application {
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

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Manual dependency injection
        FileResumeRepository resumeRepository = new FileResumeRepository();
        createResumeUseCase = new CreateResumeUseCase(resumeRepository);
        editResumeUseCase = new EditResumeUseCase(resumeRepository);
        addSectionUseCase = new AddSectionUseCase(resumeRepository);
        editSectionUseCase = new EditSectionUseCase(resumeRepository);
        deleteSectionUseCase = new DeleteSectionUseCase(resumeRepository);
        addWorkExperienceUseCase = new AddWorkExperienceUseCase(resumeRepository);
        editWorkExperienceUseCase = new EditWorkExperienceUseCase(resumeRepository);
        deleteWorkExperienceUseCase = new DeleteWorkExperienceUseCase(resumeRepository);
        loadResumeUseCase = new LoadResumeUseCase(resumeRepository);
        saveResumeUseCase = new SaveResumeUseCase(resumeRepository);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/create_resume.fxml"));
        Parent root = loader.load();
        CreateResumeController createResumeController = loader.getController();
        createResumeController.init(
            createResumeUseCase,
            editResumeUseCase,
            addSectionUseCase,
            editSectionUseCase,
            deleteSectionUseCase,
            addWorkExperienceUseCase,
            editWorkExperienceUseCase,
            deleteWorkExperienceUseCase,
            loadResumeUseCase,
            saveResumeUseCase
        );

        Scene scene = new Scene(root);
        primaryStage.setTitle("Create Resume");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
