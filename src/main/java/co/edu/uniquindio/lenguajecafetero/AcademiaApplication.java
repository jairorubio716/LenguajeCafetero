package co.edu.uniquindio.lenguajecafetero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcademiaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                AcademiaApplication.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(
                AcademiaApplication.class.getResource("css/academia.css").toExternalForm());
        stage.setTitle("LenguajeCafetero - Academia de Idiomas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
