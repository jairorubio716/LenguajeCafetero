package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Asignacion;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class ProfesorAsignacionesController {

    @FXML private ListView<Asignacion> listaAsignaciones;
    @FXML private ListView<Estudiante> listaEstudiantes;

    @FXML
    public void initialize() {
        Academia academia = Academia.getInstance();
        String miIdentificacion = Session.getProfesor().getIdentificacion();
        List<Asignacion> asignaciones = academia.getAsignacionesDeProfesor(miIdentificacion);
        List<Estudiante> estudiantes = academia.getEstudiantesDeProfesor(miIdentificacion);
        listaAsignaciones.setItems(FXCollections.observableArrayList(asignaciones));
        listaEstudiantes.setItems(FXCollections.observableArrayList(estudiantes));
    }
}