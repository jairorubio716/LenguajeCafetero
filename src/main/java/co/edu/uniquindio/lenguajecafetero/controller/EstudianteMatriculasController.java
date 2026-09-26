package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import co.edu.uniquindio.lenguajecafetero.util.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.List;

public class EstudianteMatriculasController {

    @FXML private ListView<Matricula> listaMatriculas;
    @FXML private ComboBox<Matricula> cmbMatricula;
    @FXML private ComboBox<ServicioAdicional> cmbServicio;

    private Academia academia;
    private static EstudianteMatriculasController actual;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        actual = this;
        cmbServicio.setItems(FXCollections.observableArrayList(academia.getServicios()));
        refrescar();
    }

    public static void refrescarVisible() {
        if (actual != null) {
            actual.refrescar();
        }
    }

    @FXML
    private void onRefrescar() {
        refrescar();
        AlertHelper.mostrarInfo("Datos actualizados", "Tus matriculas se recargaron desde la academia.");
    }

    @FXML
    private void onSolicitar() {
        try {
            academia.solicitarServicio(cmbMatricula.getValue(), cmbServicio.getValue());
            AlertHelper.mostrarInfo("Exito", "Servicio solicitado y agregado a la matricula.");
            refrescar();
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }

    private void refrescar() {
        List<Matricula> misMatriculas = academia
                .getMatriculasDeEstudiante(Session.getEstudiante().getDocumentoIdentidad());
        listaMatriculas.setItems(FXCollections.observableArrayList(misMatriculas));
        listaMatriculas.setCellFactory(param -> new ListCell<Matricula>() {
            @Override
            protected void updateItem(Matricula matricula, boolean empty) {
                super.updateItem(matricula, empty);
                setText(empty || matricula == null ? null : describir(matricula));
            }
        });
        cmbMatricula.setItems(FXCollections.observableArrayList(misMatriculas));
    }

    private String describir(Matricula matricula) {
        StringBuilder sb = new StringBuilder(matricula.getNumero())
                .append(" - ").append(matricula.getEstudiante().getNombre())
                .append(" - ").append(matricula.getCurso().getNombre())
                .append(" - $").append(matricula.getValorFinal());
        String beneficios = TextoCurso.beneficiosDe(matricula.getCurso());
        if (!beneficios.isEmpty()) {
            sb.append("  |  Beneficios: ").append(beneficios);
        }
        return sb.toString();
    }
}