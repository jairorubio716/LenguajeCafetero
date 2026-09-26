package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest {

    @Test
    void crearMatriculaYCalcularValorFinal() {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        CursoRegular curso = new CursoRegular("C1", "Curso", Idioma.INGLES, "D", 100000, 3);
        Matricula matricula = new Matricula("M-1", LocalDate.now(), estudiante, curso, 3, 0, null);
        assertEquals(300000, matricula.getValorFinal(), 0.01);
        assertEquals("M-1", matricula.getNumero());
        assertEquals(estudiante, matricula.getEstudiante());
        assertEquals(curso, matricula.getCurso());
    }

    @Test
    void calcularValorFinalConServiciosYDescuento() {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        CursoRegular curso = new CursoRegular("C1", "Curso", Idioma.INGLES, "D", 100000, 3);
        List<ServicioAdicional> servicios = new ArrayList<>();
        servicios.add(new ServicioAdicional(TipoServicio.MATERIAL_IMPRESO,
                "S1", "Material", "Imp", 50000, true));
        Matricula matricula = new Matricula("M-1", LocalDate.now(), estudiante, curso, 3, 0.10, servicios);
        // subtotal 350000, descuento 10% = 315000
        assertEquals(315000, matricula.getValorFinal(), 0.01);
    }

    @Test
    void agregarServicioRecalcula() {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        CursoRegular curso = new CursoRegular("C1", "Curso", Idioma.INGLES, "D", 100000, 3);
        Matricula matricula = new Matricula("M-1", LocalDate.now(), estudiante, curso, 3, 0, null);
        matricula.agregarServicioIncluido(new ServicioAdicional(TipoServicio.TALLERES_CONVERSACION,
                "S2", "Taller", "Conv", 40000, true));
        assertEquals(340000, matricula.getValorFinal(), 0.01);
        assertEquals(1, matricula.getServiciosIncluidos().size());
    }
}
