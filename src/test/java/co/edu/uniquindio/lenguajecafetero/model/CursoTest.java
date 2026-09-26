package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CursoTest {

    @Test
    void cursoRegularCalcularValorYClonar() {
        CursoRegular original = new CursoRegular("R1", "Regular", Idioma.INGLES,
                "Desc", 100000, 3);
        original.agregarBeneficio(new Beneficio(TipoBeneficio.MATERIAL_DIDACTICO, "Mat", "Desc"));
        assertEquals(300000, original.calcularValor(), 0.01);
        assertEquals(EstadoCurso.ACTIVO, original.getEstado());

        Curso clon = original.clonar();
        assertNotSame(original, clon);
        assertEquals(original.getCodigo(), clon.getCodigo());
        assertEquals(original.calcularValor(), clon.calcularValor(), 0.01);
        clon.setNombre("Otro");
        assertEquals("Regular", original.getNombre());
        assertTrue(clon instanceof CursoRegular);
    }

    @Test
    void cursoIntensivoCalcularValorYClonar() {
        CursoIntensivo original = new CursoIntensivo("I1", "Intensivo", Idioma.FRANCES,
                "Desc", 100000, 3);
        assertEquals(390000, original.calcularValor(), 0.01);
        Curso clon = original.clonar();
        assertNotSame(original, clon);
        assertTrue(clon instanceof CursoIntensivo);
        clon.setValorMensual(200000);
        assertEquals(100000, original.getValorMensual(), 0.01);
    }

    @Test
    void cursoPersonalizadoCalcularValorYClonar() {
        CursoPersonalizado original = new CursoPersonalizado("P1", "Personalizado", Idioma.PORTUGUES,
                "Desc", 80000, 2, 4, NivelIdioma.B2, "Objetivos");
        // (80000 + 4*20000) * 2 = 320000
        assertEquals(320000, original.calcularValor(), 0.01);
        assertEquals(4, original.getSesionesPorMes());
        assertEquals(NivelIdioma.B2, original.getNivelReferencia());
        assertEquals("Objetivos", original.getObjetivosEstudiante());

        Curso clon = original.clonar();
        assertNotSame(original, clon);
        assertTrue(clon instanceof CursoPersonalizado);
        ((CursoPersonalizado) clon).setSesionesPorMes(8);
        assertEquals(4, original.getSesionesPorMes());
    }

    @Test
    void settersCurso() {
        CursoRegular curso = new CursoRegular("R1", "Regular", Idioma.INGLES, "Desc", 100000, 3);
        curso.setCodigo("R2");
        curso.setDescripcion("Nueva");
        curso.setIdioma(Idioma.FRANCES);
        curso.setDuracionMeses(4);
        curso.setEstado(EstadoCurso.FINALIZADO);
        assertEquals("R2", curso.getCodigo());
        assertEquals("Nueva", curso.getDescripcion());
        assertEquals(Idioma.FRANCES, curso.getIdioma());
        assertEquals(4, curso.getDuracionMeses());
        assertEquals(EstadoCurso.FINALIZADO, curso.getEstado());
    }
}
