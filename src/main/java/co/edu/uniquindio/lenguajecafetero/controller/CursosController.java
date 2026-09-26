package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;
import co.edu.uniquindio.lenguajecafetero.model.NivelIdioma;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficios;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosRegular;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoRegular;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorDeCursos;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.List;

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

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        cmbTipo.setItems(FXCollections.observableArrayList("Regular", "Intensivo", "Personalizado"));
        cmbIdioma.setItems(FXCollections.observableArrayList(Idioma.values()));
        cmbNivel.setItems(FXCollections.observableArrayList(NivelIdioma.values()));
        cmbTipo.valueProperty().addListener((obs, oldV, newV) -> actualizarCamposPersonalizados());
        cmbTipo.setValue("Regular");
        actualizarCamposPersonalizados();
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

            CreadorDeCursos creador;
            FabricaBeneficios fabrica;
            if ("Regular".equals(tipo)) {
                creador = new CreadorCursoRegular(codigo, nombre, idioma, descripcion, valorMensual, duracion);
                fabrica = new FabricaBeneficiosRegular();
            } else if ("Intensivo".equals(tipo)) {
                creador = new CreadorCursoIntensivo(codigo, nombre, idioma, descripcion, valorMensual, duracion);
                fabrica = new FabricaBeneficiosIntensivo();
            } else {
                int sesiones = Integer.parseInt(txtSesiones.getText());
                NivelIdioma nivel = cmbNivel.getValue();
                String objetivos = txtObjetivos.getText();
                if (nivel == null || objetivos == null || objetivos.isBlank() || sesiones <= 0) {
                    AlertHelper.mostrarError("Dato invalido",
                            "Para personalizado: sesiones, nivel y objetivos son obligatorios.");
                    return;
                }
                creador = new CreadorCursoPersonalizado(codigo, nombre, idioma, descripcion,
                        valorMensual, duracion, sesiones, nivel, objetivos);
                fabrica = new FabricaBeneficiosPersonalizado();
            }

            Curso curso = creador.crearCurso();
            List<Beneficio> beneficios = fabrica.crearBeneficios();
            for (Beneficio beneficio : beneficios) {
                curso.agregarBeneficio(beneficio);
            }
            academia.registrarCurso(curso);
            limpiar();
            refrescarLista();
            AlertHelper.mostrarInfo("Exito", "Curso registrado.");
        } catch (NumberFormatException e) {
            AlertHelper.mostrarError("Dato invalido", "Revise los campos numericos.");
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescarLista() {
        listaCursos.setItems(FXCollections.observableArrayList(academia.getCursos()));
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
