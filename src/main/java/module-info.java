module co.edu.uniquindio.lenguajecafetero {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.lenguajecafetero to javafx.fxml;
    opens co.edu.uniquindio.lenguajecafetero.controller to javafx.fxml;
    opens co.edu.uniquindio.lenguajecafetero.model to javafx.base;

    exports co.edu.uniquindio.lenguajecafetero;
    exports co.edu.uniquindio.lenguajecafetero.controller;
    exports co.edu.uniquindio.lenguajecafetero.model;
    exports co.edu.uniquindio.lenguajecafetero.exception;
    exports co.edu.uniquindio.lenguajecafetero.model.patrones.builder;
    exports co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod;
    exports co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;
}
