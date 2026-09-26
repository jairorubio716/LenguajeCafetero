package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.exception.EstudianteNoEncontradoException;
import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class EstudiantesController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private DatePicker dpFechaRegistro;
    @FXML private TextField txtBuscarDocumento;
    @FXML private Label lblResultadoBusqueda;
    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, String> colDocumento;
    @FXML private TableColumn<Estudiante, String> colTelefono;
    @FXML private TableColumn<Estudiante, String> colCorreo;
    @FXML private TableColumn<Estudiante, Integer> colEdad;
    @FXML private TableColumn<Estudiante, LocalDate> colFecha;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        dpFechaRegistro.setValue(LocalDate.now());
        refrescarTabla();
    }

    @FXML
    private void onRegistrar() {
        try {
            String nombre = txtNombre.getText();
            String documento = txtDocumento.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            if (nombre == null || nombre.isBlank() || documento == null || documento.isBlank()) {
                AlertHelper.mostrarError("Dato invalido", "Nombre y documento son obligatorios.");
                return;
            }
            int edad = Integer.parseInt(txtEdad.getText());
            if (edad <= 0) {
                AlertHelper.mostrarError("Dato invalido", "La edad debe ser mayor a cero.");
                return;
            }
            LocalDate fecha = dpFechaRegistro.getValue();
            if (fecha == null) {
                AlertHelper.mostrarError("Dato invalido", "La fecha de registro es obligatoria.");
                return;
            }
            Estudiante estudiante = new Estudiante(nombre, "", telefono, correo, documento, edad, fecha);
            academia.registrarEstudiante(estudiante);
            limpiarFormulario();
            refrescarTabla();
            AlertHelper.mostrarInfo("Exito", "Estudiante registrado.");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "La edad debe ser un numero entero.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void onBuscar() {
        try {
            Estudiante encontrado = academia.buscarEstudiante(txtBuscarDocumento.getText());
            lblResultadoBusqueda.setText(encontrado.mostrarInfo() + " | Edad: " + encontrado.getEdad()
                    + " | Correo: " + encontrado.getCorreo());
        } catch (EstudianteNoEncontradoException e) {
            lblResultadoBusqueda.setText("");
            AlertHelper.mostrarError("No encontrado", e.getMessage());
        }
    }

    private void refrescarTabla() {
        tablaEstudiantes.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(LocalDate.now());
    }
}
