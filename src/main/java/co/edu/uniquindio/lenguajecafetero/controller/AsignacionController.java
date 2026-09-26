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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AsignacionController {

    @FXML private ComboBox<Estudiante> cmbEstudiante;
    @FXML private ComboBox<Curso> cmbCurso;
    @FXML private ComboBox<Profesor> cmbProfesor;
    @FXML private TableView<Asignacion> tablaAsignaciones;
    @FXML private TableColumn<Asignacion, LocalDate> colFecha;
    @FXML private TableColumn<Asignacion, String> colEstudiante;
    @FXML private TableColumn<Asignacion, String> colCurso;
    @FXML private TableColumn<Asignacion, String> colProfesor;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbEstudiante.setCellFactory(lv -> celdaEstudiante());
        cmbEstudiante.setButtonCell(celdaEstudiante());
        cmbCurso.setCellFactory(lv -> celdaCurso());
        cmbCurso.setButtonCell(celdaCurso());
        cmbProfesor.setCellFactory(lv -> celdaProfesor());
        cmbProfesor.setButtonCell(celdaProfesor());
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaAsignacion"));
        colEstudiante.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(
                        cd.getValue().getEstudiante() != null ? cd.getValue().getEstudiante().getNombre() : ""));
        colCurso.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(
                        cd.getValue().getCurso() != null ? cd.getValue().getCurso().getNombre() : ""));
        colProfesor.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(
                        cd.getValue().getProfesor() != null ? cd.getValue().getProfesor().getNombre() : ""));
        refrescar();
    }

    private ListCell<Estudiante> celdaEstudiante() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Estudiante item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        };
    }

    private ListCell<Curso> celdaCurso() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Curso item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCodigo() + " - " + item.getNombre());
            }
        };
    }

    private ListCell<Profesor> celdaProfesor() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Profesor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        };
    }

    @FXML
    private void onRefrescar() {
        refrescar();
    }

    @FXML
    private void onAsignar() {
        try {
            academia.asignarProfesor(cmbEstudiante.getValue(), cmbCurso.getValue(), cmbProfesor.getValue());
            refrescarTabla();
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
        refrescarTabla();
    }

    private void refrescarTabla() {
        tablaAsignaciones.setItems(FXCollections.observableArrayList(academia.getAsignaciones()));
    }
}
