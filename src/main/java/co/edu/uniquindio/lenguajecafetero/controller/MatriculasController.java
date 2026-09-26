package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MatriculasController {

    @FXML private ComboBox<Estudiante> cmbEstudiante;
    @FXML private ComboBox<Curso> cmbCurso;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtDescuento;
    @FXML private VBox boxServicios;
    @FXML private TableView<Matricula> tablaMatriculas;
    @FXML private TableColumn<Matricula, String> colNumero;
    @FXML private TableColumn<Matricula, String> colEstudiante;
    @FXML private TableColumn<Matricula, String> colCurso;
    @FXML private TableColumn<Matricula, Integer> colDuracion;
    @FXML private TableColumn<Matricula, Double> colValorFinal;

    private Academia academia;
    private final List<CheckBox> checksServicios = new ArrayList<>();

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbEstudiante.setCellFactory(lv -> celdaEstudiante());
        cmbEstudiante.setButtonCell(celdaEstudiante());
        cmbCurso.setCellFactory(lv -> celdaCurso());
        cmbCurso.setButtonCell(celdaCurso());
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colEstudiante.setCellValueFactory(cd ->
                new SimpleStringProperty(
                        cd.getValue().getEstudiante() != null ? cd.getValue().getEstudiante().getNombre() : ""));
        colCurso.setCellValueFactory(cd ->
                new SimpleStringProperty(
                        cd.getValue().getCurso() != null ? cd.getValue().getCurso().getNombre() : ""));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionContratada"));
        colValorFinal.setCellValueFactory(new PropertyValueFactory<>("valorFinal"));
        refrescarCombos();
        refrescarTabla();
    }

    private ListCell<Estudiante> celdaEstudiante() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Estudiante item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre() + " (" + item.getDocumentoIdentidad() + ")");
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

    @FXML
    private void onRefrescar() {
        refrescarCombos();
        refrescarTabla();
    }

    @FXML
    private void onCrearMatricula() {
        try {
            Estudiante estudiante = cmbEstudiante.getValue();
            Curso curso = cmbCurso.getValue();
            int duracion = Integer.parseInt(txtDuracion.getText());
            double descuento = Double.parseDouble(txtDescuento.getText());
            List<ServicioAdicional> incluidos = new ArrayList<>();
            for (CheckBox check : checksServicios) {
                if (check.isSelected()) {
                    incluidos.add((ServicioAdicional) check.getUserData());
                }
            }
            Matricula matricula = academia.crearMatricula(estudiante, curso, duracion, descuento, incluidos);
            refrescarTabla();
            AlertHelper.mostrarInfo("Exito",
                    "Matricula " + matricula.getNumero() + " creada. Valor: " + matricula.getValorFinal());
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "Duracion y descuento deben ser numericos.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescarCombos() {
        cmbEstudiante.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
        cmbCurso.setItems(FXCollections.observableArrayList(academia.getCursos()));
        boxServicios.getChildren().clear();
        checksServicios.clear();
        for (ServicioAdicional servicio : academia.getServicios()) {
            CheckBox check = new CheckBox(servicio.getNombre() + " ($" + servicio.getPrecio() + ")");
            check.setUserData(servicio);
            checksServicios.add(check);
            boxServicios.getChildren().add(check);
        }
    }

    private void refrescarTabla() {
        tablaMatriculas.setItems(FXCollections.observableArrayList(academia.getMatriculas()));
    }
}
