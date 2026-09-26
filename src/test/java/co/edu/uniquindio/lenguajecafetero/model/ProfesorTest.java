package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfesorTest {

    @Test
    void crearProfesorCorrecto() {
        Profesor profesor = new Profesor("Luis", "Calle 2", "301", "luis@mail.com",
                "P1", Idioma.FRANCES, 45000);
        assertEquals("Luis", profesor.getNombre());
        assertEquals("P1", profesor.getIdentificacion());
        assertEquals(Idioma.FRANCES, profesor.getIdiomaEnsenado());
        assertEquals(45000, profesor.getTarifaPorSesion(), 0.01);
        assertEquals("Profesor: Luis (idioma: FRANCES)", profesor.mostrarInfo());
    }

    @Test
    void settersProfesor() {
        Profesor profesor = new Profesor("Luis", "Calle 2", "301", "luis@mail.com",
                "P1", Idioma.FRANCES, 45000);
        profesor.setIdentificacion("P2");
        profesor.setIdiomaEnsenado(Idioma.PORTUGUES);
        profesor.setTarifaPorSesion(60000);
        assertEquals("P2", profesor.getIdentificacion());
        assertEquals(Idioma.PORTUGUES, profesor.getIdiomaEnsenado());
        assertEquals(60000, profesor.getTarifaPorSesion(), 0.01);
    }
}
