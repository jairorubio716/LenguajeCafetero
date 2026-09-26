package co.edu.uniquindio.lenguajecafetero.model;

import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoRegular;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreadorCursoTest {

    @Test
    void creadorCursoRegular() {
        CreadorCursoRegular creador = new CreadorCursoRegular("R1", "Regular", Idioma.INGLES,
                "Desc", 100000, 3);
        Curso curso = creador.crearCurso();
        assertTrue(curso instanceof CursoRegular);
        assertEquals("R1", curso.getCodigo());
        assertEquals(300000, curso.calcularValor(), 0.01);
    }

    @Test
    void creadorCursoIntensivo() {
        CreadorCursoIntensivo creador = new CreadorCursoIntensivo("I1", "Intensivo", Idioma.FRANCES,
                "Desc", 100000, 3);
        Curso curso = creador.crearCurso();
        assertTrue(curso instanceof CursoIntensivo);
        assertEquals(390000, curso.calcularValor(), 0.01);
    }

    @Test
    void creadorCursoPersonalizado() {
        CreadorCursoPersonalizado creador = new CreadorCursoPersonalizado("P1", "Pers", Idioma.PORTUGUES,
                "Desc", 80000, 2, 4, NivelIdioma.C1, "Obj");
        Curso curso = creador.crearCurso();
        assertTrue(curso instanceof CursoPersonalizado);
        CursoPersonalizado personalizado = (CursoPersonalizado) curso;
        assertEquals(4, personalizado.getSesionesPorMes());
        assertEquals(NivelIdioma.C1, personalizado.getNivelReferencia());
        assertEquals(320000, personalizado.calcularValor(), 0.01);
    }
}
