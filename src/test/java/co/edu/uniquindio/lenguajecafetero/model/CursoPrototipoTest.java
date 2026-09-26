package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CursoPrototipoTest {

    @Test
    void clonarProduceCopiaIndependiente() {
        CursoRegular original = new CursoRegular("R1", "Original", Idioma.INGLES, "D", 90000, 4);
        original.agregarBeneficio(new Beneficio(TipoBeneficio.MATERIAL_DIDACTICO, "Mat", "D"));
        original.setEstado(EstadoCurso.ACTIVO);

        Curso clon = original.clonar();
        assertNotSame(original, clon);
        assertTrue(clon instanceof CursoPrototipo);
        assertEquals(original.getCodigo(), clon.getCodigo());
        assertEquals(original.getBeneficios().size(), clon.getBeneficios().size());

        clon.setCodigo("R2");
        clon.setNombre("Clon");
        clon.getBeneficios().clear();

        assertEquals("R1", original.getCodigo());
        assertEquals("Original", original.getNombre());
        assertEquals(1, original.getBeneficios().size());
    }
}
