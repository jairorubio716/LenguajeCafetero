package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class EstudianteCursosController {

    @FXML private ListView<Curso> listaCursos;
    @FXML private TextField txtDuracion;
    @FXML private VBox boxServicios;

    private Academia academia;
    private final List<CheckBox> checksServicios = new ArrayList<>();

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        listaCursos.setItems(FXCollections.observableArrayList(academia.getCursosActivos()));
        listaCursos.setCellFactory(param -> new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso curso, boolean empty) {
                super.updateItem(curso, empty);
                setText(empty || curso == null ? null : TextoCurso.conBeneficios(curso));
            }
        });
        listaCursos.getSelectionModel().selectedItemProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) {
                txtDuracion.setText(String.valueOf(nuevo.getDuracionMeses()));
            }
        });
        for (ServicioAdicional servicio : academia.getServicios()) {
            CheckBox check = new CheckBox(servicio.getNombre() + " ($" + servicio.getPrecio() + ")");
            check.setUserData(servicio);
            checksServicios.add(check);
            boxServicios.getChildren().add(check);
        }
    }

    @FXML
    private void onMatricularme() {
        try {
            Curso curso = listaCursos.getSelectionModel().getSelectedItem();
            if (curso == null) {
                AlertHelper.mostrarError("Curso requerido", "Seleccione un curso disponible.");
                return;
            }
            int duracion = Integer.parseInt(txtDuracion.getText());
            if (duracion <= 0) {
                AlertHelper.mostrarError("Dato invalido", "La duracion debe ser mayor a cero.");
                return;
            }
            List<ServicioAdicional> servicios = new ArrayList<>();
            for (CheckBox check : checksServicios) {
                if (check.isSelected()) {
                    servicios.add((ServicioAdicional) check.getUserData());
                }
            }
            Matricula matricula = academia.crearMatricula(Session.getEstudiante(), curso, duracion, 0, servicios);
            EstudianteMatriculasController.refrescarVisible();
            AlertHelper.mostrarInfo("Exito",
                    "Matricula " + matricula.getNumero() + " creada. Valor final: $"
                            + String.format("%.2f", matricula.getValorFinal()));
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "La duracion debe ser un numero entero.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }
}