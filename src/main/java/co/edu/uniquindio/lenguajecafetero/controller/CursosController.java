package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.EstadoCurso;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;
import co.edu.uniquindio.lenguajecafetero.model.NivelIdioma;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CursosController {

    @FXML private ComboBox<String> cmbTipo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<Idioma> cmbIdioma;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtValorMensual;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtSesiones;
    @FXML private ComboBox<NivelIdioma> cmbNivel;
    @FXML private TextField txtObjetivos;
    @FXML private Label lblSesiones;
    @FXML private Label lblNivel;
    @FXML private Label lblObjetivos;
    @FXML private GridPane gridPersonalizado;
    @FXML private ListView<Curso> listaCursos;
    @FXML private ComboBox<EstadoCurso> cmbEstado;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbTipo.setItems(FXCollections.observableArrayList("Regular", "Intensivo", "Personalizado"));
        cmbIdioma.setItems(FXCollections.observableArrayList(Idioma.values()));
        cmbNivel.setItems(FXCollections.observableArrayList(NivelIdioma.values()));
        cmbEstado.setItems(FXCollections.observableArrayList(EstadoCurso.values()));
        cmbTipo.valueProperty().addListener((obs, oldV, newV) -> actualizarCamposPersonalizados());
        cmbTipo.setValue("Regular");
        actualizarCamposPersonalizados();
        listaCursos.getSelectionModel().selectedItemProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) {
                cmbEstado.setValue(nuevo.getEstado());
            }
        });
        refrescarLista();
    }

    private void actualizarCamposPersonalizados() {
        boolean personalizado = "Personalizado".equals(cmbTipo.getValue());
        gridPersonalizado.setVisible(personalizado);
        gridPersonalizado.setManaged(personalizado);
        lblSesiones.setVisible(personalizado);
        lblNivel.setVisible(personalizado);
        lblObjetivos.setVisible(personalizado);
        txtSesiones.setVisible(personalizado);
        cmbNivel.setVisible(personalizado);
        txtObjetivos.setVisible(personalizado);
    }

    @FXML
    private void onRegistrar() {
        try {
            String tipo = cmbTipo.getValue();
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            Idioma idioma = cmbIdioma.getValue();
            String descripcion = txtDescripcion.getText();
            if (tipo == null || codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()
                    || idioma == null) {
                AlertHelper.mostrarError("Dato invalido", "Tipo, codigo, nombre e idioma son obligatorios.");
                return;
            }
            double valorMensual = Double.parseDouble(txtValorMensual.getText());
            int duracion = Integer.parseInt(txtDuracion.getText());
            if (valorMensual < 0 || duracion <= 0) {
                AlertHelper.mostrarError("Dato invalido", "Valor y duracion deben ser validos.");
                return;
            }

            Curso curso;
            if ("Regular".equals(tipo)) {
                curso = academia.registrarCursoRegular(codigo, nombre, idioma, descripcion,
                        valorMensual, duracion);
            } else if ("Intensivo".equals(tipo)) {
                curso = academia.registrarCursoIntensivo(codigo, nombre, idioma, descripcion,
                        valorMensual, duracion);
            } else {
                int sesiones = Integer.parseInt(txtSesiones.getText());
                NivelIdioma nivel = cmbNivel.getValue();
                String objetivos = txtObjetivos.getText();
                if (nivel == null || objetivos == null || objetivos.isBlank() || sesiones <= 0) {
                    AlertHelper.mostrarError("Dato invalido",
                            "Para personalizado: sesiones, nivel y objetivos son obligatorios.");
                    return;
                }
                curso = academia.registrarCursoPersonalizado(codigo, nombre, idioma, descripcion,
                        valorMensual, duracion, sesiones, nivel, objetivos);
            }
            limpiar();
            refrescarLista();
            AlertHelper.mostrarInfo("Exito", "Curso " + curso.getCodigo() + " registrado.");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "Revise los campos numericos.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    @FXML
    private void onCambiarEstado() {
        Curso curso = listaCursos.getSelectionModel().getSelectedItem();
        if (curso == null) {
            AlertHelper.mostrarError("Curso requerido", "Seleccione un curso de la lista.");
            return;
        }
        try {
            academia.actualizarEstadoCurso(curso.getCodigo(), cmbEstado.getValue());
            refrescarLista();
            AlertHelper.mostrarInfo("Exito",
                    "El curso " + curso.getCodigo() + " paso a estado: " + cmbEstado.getValue());
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescarLista() {
        listaCursos.setItems(FXCollections.observableArrayList(academia.getCursos()));
        listaCursos.setCellFactory(param -> new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso curso, boolean empty) {
                super.updateItem(curso, empty);
                setText(empty || curso == null ? null : TextoCurso.conBeneficios(curso));
            }
        });
    }

    private void limpiar() {
        txtCodigo.clear();
        txtNombre.clear();
        cmbIdioma.getSelectionModel().clearSelection();
        txtDescripcion.clear();
        txtValorMensual.clear();
        txtDuracion.clear();
        txtSesiones.clear();
        cmbNivel.getSelectionModel().clearSelection();
        txtObjetivos.clear();
        cmbTipo.setValue("Regular");
    }
}
