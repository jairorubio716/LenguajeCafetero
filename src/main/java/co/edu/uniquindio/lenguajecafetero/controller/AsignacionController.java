package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Asignacion;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.CursoPersonalizado;
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
        refrescar();
    }

    @FXML
    private void onRefrescar() {
        refrescar();
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
        List<Curso> personalizados = new ArrayList<>();
        for (Curso curso : academia.getCursos()) {
            if (curso instanceof CursoPersonalizado) {
                personalizados.add(curso);
            }
        }
        cmbCurso.setItems(FXCollections.observableArrayList(personalizados));
        cmbProfesor.setItems(FXCollections.observableArrayList(academia.getProfesores()));
        refrescarLista();
    }

    private void refrescarLista() {
        listaAsignaciones.setItems(FXCollections.observableArrayList(academia.getAsignaciones()));
    }
}
