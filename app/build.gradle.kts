plugins {
    application
    id("java")
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
}

group = "org.wordlefx"
version = "1.0-SNAPSHOT"
description = "WordleFX"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.org.openjfx.javafx.controls)
    implementation(libs.org.openjfx.javafx.fxml)
    implementation(libs.org.openjfx.javafx.graphics)
    implementation(libs.org.controlsfx.controlsfx)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainModule = "com.wordlefx"
    mainClass = "com.wordlefx.Launcher"
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

jlink {
    imageZip = project.file("${buildDir}/distributions/app-${javafx.platform.classifier}.zip")
    options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    launcher {
        name = "app"
    }
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "com.wordlefx.Launcher"
    }
}