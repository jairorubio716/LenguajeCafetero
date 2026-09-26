package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import co.edu.uniquindio.lenguajecafetero.model.TipoServicio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiciosController {

    @FXML private ComboBox<TipoServicio> cmbTipo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private CheckBox chkDisponible;
    @FXML private TableView<ServicioAdicional> tablaServicios;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, String> colDescripcion;
    @FXML private TableColumn<ServicioAdicional, Double> colPrecio;
    @FXML private TableColumn<ServicioAdicional, Boolean> colDisponible;
    @FXML private ComboBox<Matricula> cmbMatricula;
    @FXML private ComboBox<ServicioAdicional> cmbServicioSolicitar;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbTipo.setItems(FXCollections.observableArrayList(TipoServicio.values()));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        cmbMatricula.setCellFactory(lv -> celdaMatricula());
        cmbMatricula.setButtonCell(celdaMatricula());
        cmbServicioSolicitar.setCellFactory(lv -> celdaServicio());
        cmbServicioSolicitar.setButtonCell(celdaServicio());
        chkDisponible.setSelected(true);
        refrescar();
    }

    private ListCell<Matricula> celdaMatricula() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Matricula item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNumero() + " - " + item.getEstudiante().getNombre());
                }
            }
        };
    }

    private ListCell<ServicioAdicional> celdaServicio() {
        return new ListCell<>() {
            @Override
            protected void updateItem(ServicioAdicional item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCodigo() + " - " + item.getNombre());
            }
        };
    }

    @FXML
    private void onRegistrar() {
        try {
            TipoServicio tipo = cmbTipo.getValue();
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            if (tipo == null || codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
                AlertHelper.mostrarError("Dato invalido", "Tipo, codigo y nombre son obligatorios.");
                return;
            }
            double precio = Double.parseDouble(txtPrecio.getText());
            if (precio < 0) {
                AlertHelper.mostrarError("Dato invalido", "El precio no puede ser negativo.");
                return;
            }
            ServicioAdicional servicio = new ServicioAdicional(tipo, codigo, nombre, descripcion,
                    precio, chkDisponible.isSelected());
            academia.registrarServicio(servicio);
            limpiar();
            refrescar();
            AlertHelper.mostrarInfo("Exito", "Servicio registrado.");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "El precio debe ser numerico.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void onSolicitar() {
        try {
            academia.solicitarServicio(cmbMatricula.getValue(), cmbServicioSolicitar.getValue());
            AlertHelper.mostrarInfo("Exito", "Servicio solicitado y agregado a la matricula.");
            refrescar();
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void onRefrescar() {
        refrescar();
    }

    private void refrescar() {
        tablaServicios.setItems(FXCollections.observableArrayList(academia.getServicios()));
        cmbMatricula.setItems(FXCollections.observableArrayList(academia.getMatriculas()));
        cmbServicioSolicitar.setItems(FXCollections.observableArrayList(academia.getServicios()));
    }

    private void limpiar() {
        cmbTipo.getSelectionModel().clearSelection();
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        chkDisponible.setSelected(true);
    }
}
