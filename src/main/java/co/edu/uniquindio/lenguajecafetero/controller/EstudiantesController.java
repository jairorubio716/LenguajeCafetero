package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.exception.EstudianteNoEncontradoException;
import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EstudiantesController {

    @FXML private TextField txtBuscar;
    @FXML private ListView<Estudiante> listaEstudiantes;
    @FXML private Label lblDetalle;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        refrescarLista();
    }

    @FXML
    private void onBuscar() {
        String documento = txtBuscar.getText();
        if (documento == null || documento.isBlank()) {
            AlertHelper.mostrarError("Documento requerido", "Ingrese el documento del estudiante a buscar.");
            return;
        }
        try {
            Estudiante estudiante = academia.buscarEstudiante(documento.trim());
            listaEstudiantes.setItems(FXCollections.observableArrayList(estudiante));
            lblDetalle.setText(consultarMatriculas(estudiante));
        } catch (EstudianteNoEncontradoException e) {
            listaEstudiantes.setItems(FXCollections.observableArrayList());
            lblDetalle.setText("No se encontro estudiante con documento: " + documento.trim());
        }
    }

    @FXML
    private void onRefrescar() {
        txtBuscar.clear();
        refrescarLista();
        lblDetalle.setText("");
        AlertHelper.mostrarInfo("Datos actualizados", "La lista de estudiantes se recargo desde la academia.");
    }

    private void refrescarLista() {
        listaEstudiantes.setItems(FXCollections.observableArrayList(academia.getEstudiantes()));
    }

    private String consultarMatriculas(Estudiante estudiante) {
        java.util.List<Matricula> matriculas =
                academia.getMatriculasDeEstudiante(estudiante.getDocumentoIdentidad());
        if (matriculas.isEmpty()) {
            return "El estudiante no se ha matriculado en ningun curso.";
        }
        StringBuilder detalle = new StringBuilder("Matriculas de " + estudiante.getNombre() + ":");
        for (Matricula matricula : matriculas) {
            detalle.append("\n").append(matricula.getNumero())
                    .append(" - ").append(matricula.getCurso().getNombre())
                    .append(" (").append(matricula.getCurso().getEstado()).append(")")
                    .append(" - $").append(matricula.getValorFinal());
            String beneficios = TextoCurso.beneficiosDe(matricula.getCurso());
            if (!beneficios.isEmpty()) {
                detalle.append("\n    Beneficios: ").append(beneficios);
            }
        }
        return detalle.toString();
    }
}