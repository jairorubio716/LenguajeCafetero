package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EstudianteMainController {

    @FXML private Label lblBienvenido;

    @FXML
    public void initialize() {
        if (Session.getEstudiante() != null) {
            lblBienvenido.setText("Bienvenido, " + Session.getEstudiante().getNombre());
        }
    }

    @FXML
    private void onSalir() {
        Stage stage = (Stage) lblBienvenido.getScene().getWindow();
        LoginController.cargarVista(stage, "login-view.fxml", "Lenguajecafetero - Acceso");
    }
}