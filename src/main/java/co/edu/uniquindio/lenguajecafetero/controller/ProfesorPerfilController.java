package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Profesor;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfesorPerfilController {

    @FXML private Label lblNombre;
    @FXML private Label lblIdentificacion;
    @FXML private Label lblIdioma;
    @FXML private Label lblTelefono;
    @FXML private Label lblCorreo;
    @FXML private Label lblTarifa;

    @FXML
    public void initialize() {
        Profesor profesor = Session.getProfesor();
        if (profesor == null) {
            return;
        }
        lblNombre.setText(profesor.getNombre());
        lblIdentificacion.setText(profesor.getIdentificacion());
        lblIdioma.setText(String.valueOf(profesor.getIdiomaEnsenado()));
        lblTelefono.setText(profesor.getTelefono());
        lblCorreo.setText(profesor.getCorreo());
        lblTarifa.setText("$" + String.format("%.2f", profesor.getTarifaPorSesion()));
    }
}