package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MatriculasController {

    @FXML private ListView<Matricula> listaMatriculas;

    private Academia academia;

    @FXML
    public void initialize() {
        academia = Academia.getInstance();
        refrescarLista();
    }

    @FXML
    private void onRefrescar() {
        refrescarLista();
        AlertHelper.mostrarInfo("Datos actualizados", "Las matriculas se recargaron desde la academia.");
    }

    private void refrescarLista() {
        listaMatriculas.setItems(FXCollections.observableArrayList(academia.getMatriculas()));
    }
}