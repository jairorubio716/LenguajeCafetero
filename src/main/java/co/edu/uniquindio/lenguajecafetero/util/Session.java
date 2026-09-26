package co.edu.uniquindio.lenguajecafetero.util;

import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Profesor;

public class Session {
    private static String rol;
    private static Estudiante estudiante;
    private static Profesor profesor;

    private Session() {
    }

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        Session.rol = rol;
    }

    public static Estudiante getEstudiante() {
        return estudiante;
    }

    public static void setEstudiante(Estudiante estudiante) {
        Session.estudiante = estudiante;
    }

    public static Profesor getProfesor() {
        return profesor;
    }

    public static void setProfesor(Profesor profesor) {
        Session.profesor = profesor;
    }
}