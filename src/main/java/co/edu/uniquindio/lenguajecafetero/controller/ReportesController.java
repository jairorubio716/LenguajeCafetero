package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class ReportesController {

    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;
    @FXML private Label lblTotal;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        dpInicio.setValue(LocalDate.now().withDayOfYear(1));
        dpFin.setValue(LocalDate.now());
    }

    @FXML
    private void onConsultar() {
        try {
            LocalDate inicio = dpInicio.getValue();
            LocalDate fin = dpFin.getValue();
            if (inicio == null || fin == null) {
                AlertHelper.mostrarError("Dato invalido", "Debe seleccionar fecha inicial y final.");
                return;
            }
            if (fin.isBefore(inicio)) {
                AlertHelper.mostrarError("Dato invalido", "La fecha final no puede ser anterior a la inicial.");
                return;
            }
            double total = academia.ingresosPorPeriodo(inicio, fin);
            lblTotal.setText("Ingresos del periodo: $" + String.format("%.2f", total));
        } catch (Exception e) {
            AlertHelper.mostrarError("Error", e.getMessage());
        }
    }
}
