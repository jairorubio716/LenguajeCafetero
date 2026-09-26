package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.AcademiaApplication;
import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Profesor;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private ScrollPane scrollPane;
    @FXML private ComboBox<String> cmbRol;
    @FXML private GridPane gridPersona;
    @FXML private Label lblEstudiante;
    @FXML private ComboBox<Estudiante> cmbEstudiante;
    @FXML private Label lblProfesor;
    @FXML private ComboBox<Profesor> cmbProfesor;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        scrollPane.viewportBoundsProperty().addListener((obs, antes, ahora) -> {
            VBox contenido = (VBox) scrollPane.getContent();
            contenido.setMinHeight(ahora.getHeight());
        });
        cmbRol.setItems(FXCollections.observableArrayList("Administrador", "Estudiante", "Profesor"));
        cmbEstudiante.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
        cmbProfesor.setItems(FXCollections.observableArrayList(academia.getProfesores()));
        cmbRol.valueProperty().addListener((obs, viejo, nuevo) -> ajustarCamposPorRol());
        cmbRol.setValue("Administrador");
    }

    @FXML
    private void onEntrar() {
        String rol = cmbRol.getValue();
        if (rol == null) {
            AlertHelper.mostrarError("Rol requerido", "Seleccione un rol para ingresar.");
            return;
        }
        if ("Estudiante".equals(rol) && cmbEstudiante.getValue() == null) {
            AlertHelper.mostrarError("Estudiante requerido", "Seleccione el estudiante que va a ingresar.");
            return;
        }
        if ("Profesor".equals(rol) && cmbProfesor.getValue() == null) {
            AlertHelper.mostrarError("Profesor requerido", "Seleccione el profesor que va a ingresar.");
            return;
        }

        Session.setRol(rol);
        Session.setEstudiante(cmbEstudiante.getValue());
        Session.setProfesor(cmbProfesor.getValue());

        String recurso;
        switch (rol) {
            case "Estudiante" -> recurso = "estudiante-main.fxml";
            case "Profesor" -> recurso = "profesor-main.fxml";
            default -> recurso = "main-view.fxml";
        }
        Stage stage = (Stage) cmbRol.getScene().getWindow();
        cargarVista(stage, recurso, "Lenguajecafetero - " + rol);
    }

    @FXML
    private void onRegistrarse() {
        Stage stage = (Stage) cmbRol.getScene().getWindow();
        cargarVista(stage, "registro-view.fxml", "Lenguajecafetero - Registro");
    }

    private void ajustarCamposPorRol() {
        String rol = cmbRol.getValue();
        boolean estudiante = "Estudiante".equals(rol);
        boolean profesor = "Profesor".equals(rol);
        gridPersona.setVisible(estudiante || profesor);
        gridPersona.setManaged(estudiante || profesor);
        lblEstudiante.setVisible(estudiante);
        lblEstudiante.setManaged(estudiante);
        cmbEstudiante.setVisible(estudiante);
        cmbEstudiante.setManaged(estudiante);
        lblProfesor.setVisible(profesor);
        lblProfesor.setManaged(profesor);
        cmbProfesor.setVisible(profesor);
        cmbProfesor.setManaged(profesor);
    }

    public static void cargarVista(Stage stage, String recurso, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    AcademiaApplication.class.getResource("view/" + recurso));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 700);
            scene.getStylesheets().add(
                    AcademiaApplication.class.getResource("css/academia.css").toExternalForm());
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            AlertHelper.mostrarError("Error", "No se pudo cargar la vista: " + e.getMessage());
        }
    }
}