package co.edu.uniquindio.lenguajecafetero.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {
    @FXML private Label lblSubtitulo;

    @FXML
    public void initialize() {
        // Pantalla principal con TabPane; las pestanas cargan sus propios controladores.
    }

    @FXML
    private void onSalir() {
        Stage stage = (Stage) lblSubtitulo.getScene().getWindow();
        LoginController.cargarVista(stage, "login-view.fxml", "Lenguajecafetero - Acceso");
    }
}
