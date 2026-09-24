module co.edu.uniquindio.lenguajecafetero {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.lenguajecafetero to javafx.fxml;
    exports co.edu.uniquindio.lenguajecafetero;
}