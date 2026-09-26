package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;
import co.edu.uniquindio.lenguajecafetero.model.Profesor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfesoresController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private ComboBox<Idioma> cmbIdioma;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTarifa;
    @FXML private TableView<Profesor> tablaProfesores;
    @FXML private TableColumn<Profesor, String> colNombre;
    @FXML private TableColumn<Profesor, String> colIdentificacion;
    @FXML private TableColumn<Profesor, Idioma> colIdioma;
    @FXML private TableColumn<Profesor, String> colTelefono;
    @FXML private TableColumn<Profesor, String> colCorreo;
    @FXML private TableColumn<Profesor, Double> colTarifa;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbIdioma.setItems(FXCollections.observableArrayList(Idioma.values()));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("idiomaEnsenado"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaPorSesion"));
        refrescarTabla();
    }

    @FXML
    private void onRegistrar() {
        try {
            String nombre = txtNombre.getText();
            String identificacion = txtIdentificacion.getText();
            Idioma idioma = cmbIdioma.getValue();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            if (nombre == null || nombre.isBlank() || identificacion == null || identificacion.isBlank()
                    || idioma == null) {
                AlertHelper.mostrarError("Dato invalido", "Nombre, identificacion e idioma son obligatorios.");
                return;
            }
            double tarifa = Double.parseDouble(txtTarifa.getText());
            if (tarifa < 0) {
                AlertHelper.mostrarError("Dato invalido", "La tarifa no puede ser negativa.");
                return;
            }
            Profesor profesor = new Profesor(nombre, "", telefono, correo, identificacion, idioma, tarifa);
            academia.registrarProfesor(profesor);
            limpiar();
            refrescarTabla();
            AlertHelper.mostrarInfo("Exito", "Profesor registrado.");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "La tarifa debe ser un numero.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescarTabla() {
        tablaProfesores.setItems(FXCollections.observableArrayList(academia.getProfesores()));
    }

    private void limpiar() {
        txtNombre.clear();
        txtIdentificacion.clear();
        cmbIdioma.getSelectionModel().clearSelection();
        txtTelefono.clear();
        txtCorreo.clear();
        txtTarifa.clear();
    }
}
