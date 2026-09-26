package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioUtilizadoTest {

    @Test
    void crearServicioUtilizadoCorrecto() {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        CursoRegular curso = new CursoRegular("C1", "Curso", Idioma.INGLES, "D", 100000, 3);
        Matricula matricula = new Matricula("M-1", LocalDate.now(), estudiante, curso, 3, 0, null);
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.TUTORIA_REFUERZO,
                "S1", "Tutoria", "D", 50000, true);
        LocalDate fecha = LocalDate.of(2024, 7, 1);
        ServicioUtilizado utilizado = new ServicioUtilizado(fecha, matricula, servicio);
        assertEquals(fecha, utilizado.getFecha());
        assertEquals(matricula, utilizado.getMatricula());
        assertEquals(servicio, utilizado.getServicio());
    }
}
