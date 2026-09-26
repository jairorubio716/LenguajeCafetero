package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class RegistroController {

    @FXML private ScrollPane scrollPane;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private TextField txtDireccion;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        scrollPane.viewportBoundsProperty().addListener((obs, antes, ahora) -> {
            VBox contenido = (VBox) scrollPane.getContent();
            contenido.setMinHeight(ahora.getHeight());
        });
    }

    @FXML
    private void onCrearCuenta() {
        try {
            String nombre = txtNombre.getText();
            String documento = txtDocumento.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();
            if (nombre == null || nombre.isBlank() || documento == null || documento.isBlank()) {
                AlertHelper.mostrarError("Dato invalido", "Nombre y documento son obligatorios.");
                return;
            }
            int edad = Integer.parseInt(txtEdad.getText());
            if (edad <= 0) {
                AlertHelper.mostrarError("Dato invalido", "La edad debe ser mayor a cero.");
                return;
            }
            Estudiante estudiante = new Estudiante(nombre, direccion, telefono, correo,
                    documento, edad, LocalDate.now());
            academia.registrarEstudiante(estudiante);
            Session.setRol("Estudiante");
            Session.setEstudiante(estudiante);
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            LoginController.cargarVista(stage, "estudiante-main.fxml", "Lenguajecafetero - Estudiante");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "La edad debe ser un numero entero.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void onVolver() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        LoginController.cargarVista(stage, "login-view.fxml", "Lenguajecafetero - Acceso");
    }
}