package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsignacionTest {

    @Test
    void crearAsignacionCorrecta() {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        CursoPersonalizado curso = new CursoPersonalizado("P1", "Pers", Idioma.INGLES,
                "D", 80000, 2, 4, NivelIdioma.A2, "Obj");
        Profesor profesor = new Profesor("Luis", "C", "301", "l@m.com",
                "P1", Idioma.INGLES, 50000);
        LocalDate fecha = LocalDate.of(2024, 6, 1);
        Asignacion asignacion = new Asignacion(fecha, estudiante, curso, profesor);
        assertEquals(fecha, asignacion.getFechaAsignacion());
        assertEquals(estudiante, asignacion.getEstudiante());
        assertEquals(curso, asignacion.getCurso());
        assertEquals(profesor, asignacion.getProfesor());
    }

    @Test
    void settersAsignacion() {
        Estudiante e1 = new Estudiante("A", "C", "1", "a@m.com", "1", 20, LocalDate.now());
        Estudiante e2 = new Estudiante("B", "C", "2", "b@m.com", "2", 21, LocalDate.now());
        CursoPersonalizado curso = new CursoPersonalizado("P1", "Pers", Idioma.INGLES,
                "D", 80000, 2, 4, NivelIdioma.A1, "Obj");
        Profesor profesor = new Profesor("L", "C", "3", "l@m.com", "P", Idioma.INGLES, 1);
        Asignacion asignacion = new Asignacion(LocalDate.now(), e1, curso, profesor);
        asignacion.setEstudiante(e2);
        asignacion.setFechaAsignacion(LocalDate.of(2025, 1, 1));
        assertEquals(e2, asignacion.getEstudiante());
        assertEquals(LocalDate.of(2025, 1, 1), asignacion.getFechaAsignacion());
    }
}
