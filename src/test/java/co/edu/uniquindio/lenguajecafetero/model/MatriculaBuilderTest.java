package co.edu.uniquindio.lenguajecafetero.model;

import co.edu.uniquindio.lenguajecafetero.exception.MatriculaInvalidaException;
import co.edu.uniquindio.lenguajecafetero.model.patrones.builder.MatriculaBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatriculaBuilderTest {

    @Test
    void builderEncadenado() throws MatriculaInvalidaException {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        CursoRegular curso = new CursoRegular("C1", "Curso", Idioma.INGLES, "D", 100000, 3);
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.MATERIAL_IMPRESO,
                "S1", "Material", "Imp", 20000, true);

        Matricula matricula = new MatriculaBuilder()
                .conEstudiante(estudiante)
                .conCurso(curso)
                .conFecha(LocalDate.of(2024, 5, 1))
                .conDuracion(2)
                .agregarServicio(servicio)
                .aplicarDescuento(0.05)
                .build();

        assertEquals(estudiante, matricula.getEstudiante());
        assertNotSame(curso, matricula.getCurso());
        assertEquals(curso.getCodigo(), matricula.getCurso().getCodigo());
        assertEquals(curso.calcularValor(), matricula.getCurso().calcularValor(), 0.01);
        assertEquals(LocalDate.of(2024, 5, 1), matricula.getFecha());
        assertEquals(2, matricula.getDuracionContratada());
        assertEquals(0.05, matricula.getDescuentoAplicado(), 0.001);
        assertEquals(1, matricula.getServiciosIncluidos().size());
        // valorCurso = 300000/3*2 = 200000 + 20000 = 220000 - 5% = 209000
        assertEquals(209000, matricula.getValorFinal(), 0.01);
    }

    @Test
    void buildSinEstudianteLanzaExcepcion() {
        CursoRegular curso = new CursoRegular("C1", "Curso", Idioma.INGLES, "D", 100000, 3);
        assertThrows(MatriculaInvalidaException.class,
                () -> new MatriculaBuilder().conCurso(curso).build());
    }

    @Test
    void buildSinCursoLanzaExcepcion() {
        Estudiante estudiante = new Estudiante("Ana", "C", "300", "a@m.com",
                "1", 20, LocalDate.now());
        assertThrows(MatriculaInvalidaException.class,
                () -> new MatriculaBuilder().conEstudiante(estudiante).build());
    }
}
