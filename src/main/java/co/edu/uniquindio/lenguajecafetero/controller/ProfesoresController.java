package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;
import co.edu.uniquindio.lenguajecafetero.model.Profesor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProfesoresController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private ComboBox<Idioma> cmbIdioma;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTarifa;
    @FXML private ListView<Profesor> listaProfesores;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbIdioma.setItems(FXCollections.observableArrayList(Idioma.values()));
        refrescarLista();
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
            refrescarLista();
            AlertHelper.mostrarInfo("Exito", "Profesor registrado.");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "La tarifa debe ser un numero.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescarLista() {
        listaProfesores.setItems(FXCollections.observableArrayList(academia.getProfesores()));
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
