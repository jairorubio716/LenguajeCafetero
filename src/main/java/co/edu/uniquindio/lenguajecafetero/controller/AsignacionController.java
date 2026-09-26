package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Asignacion;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Profesor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class AsignacionController {

    @FXML private ComboBox<Estudiante> cmbEstudiante;
    @FXML private ComboBox<Curso> cmbCurso;
    @FXML private ComboBox<Profesor> cmbProfesor;
    @FXML private ListView<Asignacion> listaAsignaciones;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbCurso.valueProperty().addListener((obs, viejo, nuevo) -> filtrarProfesoresPorIdioma());
        refrescar();
    }

    @FXML
    private void onRefrescar() {
        refrescar();
        AlertHelper.mostrarInfo("Datos actualizados", "Las listas de asignacion se recargaron desde la academia.");
    }

    @FXML
    private void onAsignar() {
        try {
            academia.asignarProfesor(cmbEstudiante.getValue(), cmbCurso.getValue(), cmbProfesor.getValue());
            refrescarLista();
            AlertHelper.mostrarInfo("Exito", "Profesor asignado.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescar() {
        cmbEstudiante.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
        cmbCurso.setItems(FXCollections.observableArrayList(academia.getCursosPersonalizados()));
        filtrarProfesoresPorIdioma();
        refrescarLista();
    }

    private void filtrarProfesoresPorIdioma() {
        if (cmbProfesor == null) {
            return;
        }
        Curso curso = cmbCurso.getValue();
        List<Profesor> profesores = new ArrayList<>();
        for (Profesor profesor : academia.getProfesores()) {
            if (curso == null || profesor.getIdiomaEnsenado().equals(curso.getIdioma())) {
                profesores.add(profesor);
            }
        }
        cmbProfesor.setItems(FXCollections.observableArrayList(profesores));
    }

    private void refrescarLista() {
        listaAsignaciones.setItems(FXCollections.observableArrayList(academia.getAsignaciones()));
    }
}
