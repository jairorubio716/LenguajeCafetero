package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Asignacion;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class EstudiantePerfilController {

    @FXML private Label lblNombre;
    @FXML private Label lblDocumento;
    @FXML private Label lblTelefono;
    @FXML private Label lblCorreo;
    @FXML private Label lblEdad;
    @FXML private Label lblFecha;
    @FXML private ListView<Asignacion> listaProfesorAsignado;

    @FXML
    public void initialize() {
        Estudiante estudiante = Session.getEstudiante();
        if (estudiante == null) {
            return;
        }
        lblNombre.setText(estudiante.getNombre());
        lblDocumento.setText(estudiante.getDocumentoIdentidad());
        lblTelefono.setText(estudiante.getTelefono());
        lblCorreo.setText(estudiante.getCorreo());
        lblEdad.setText(String.valueOf(estudiante.getEdad()));
        lblFecha.setText(String.valueOf(estudiante.getFechaRegistro()));

        List<Asignacion> asignaciones = Academia.getInstance()
                .getAsignacionesDeEstudiante(estudiante.getDocumentoIdentidad());
        listaProfesorAsignado.setItems(FXCollections.observableArrayList(asignaciones));
    }
}