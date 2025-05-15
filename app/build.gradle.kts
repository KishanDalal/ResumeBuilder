plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0" // Not needed for basic usage
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val javafxVersion = "21.0.2"

javafx {
    version = javafxVersion
    modules = listOf("javafx.controls", "javafx.fxml")
}


dependencies {
    //implementation(libs.guava)
    implementation("org.openjfx:javafx-controls:$javafxVersion")
    implementation("org.openjfx:javafx-fxml:$javafxVersion")
}

application {
    mainClass.set("com.resumebuilder.presentation.ResumeApp")
}