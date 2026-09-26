package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioAdicionalTest {

    @Test
    void crearServicioCorrecto() {
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.EXAMEN_CERTIFICACION,
                "S1", "Simulacro", "Examen", 90000, true);
        assertEquals(TipoServicio.EXAMEN_CERTIFICACION, servicio.getTipo());
        assertEquals("S1", servicio.getCodigo());
        assertEquals("Simulacro", servicio.getNombre());
        assertEquals("Examen", servicio.getDescripcion());
        assertEquals(90000, servicio.getPrecio(), 0.01);
        assertTrue(servicio.isDisponible());
    }

    @Test
    void settersServicio() {
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.MATERIAL_IMPRESO,
                "S1", "Mat", "D", 10000, true);
        servicio.setDisponible(false);
        servicio.setPrecio(15000);
        servicio.setNombre("Nuevo");
        assertFalse(servicio.isDisponible());
        assertEquals(15000, servicio.getPrecio(), 0.01);
        assertEquals("Nuevo", servicio.getNombre());
    }
}
