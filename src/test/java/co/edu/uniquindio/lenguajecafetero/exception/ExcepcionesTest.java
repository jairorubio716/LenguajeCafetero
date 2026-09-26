package co.edu.uniquindio.lenguajecafetero.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExcepcionesTest {

    @Test
    void estudianteNoEncontradoConMensaje() {
        EstudianteNoEncontradoException ex = new EstudianteNoEncontradoException("no encontrado");
        assertEquals("no encontrado", ex.getMessage());
    }

    @Test
    void estudianteNoEncontradoConCausa() {
        RuntimeException causa = new RuntimeException("causa");
        EstudianteNoEncontradoException ex = new EstudianteNoEncontradoException("msg", causa);
        assertEquals("msg", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }

    @Test
    void cursoNoDisponibleConMensajeYCausa() {
        CursoNoDisponibleException ex = new CursoNoDisponibleException("curso", new Exception("c"));
        assertEquals("curso", ex.getMessage());
        assertNotNull(ex.getCause());
    }

    @Test
    void servicioNoDisponibleConMensajeYCausa() {
        ServicioNoDisponibleException ex = new ServicioNoDisponibleException("serv", new Exception("c"));
        assertEquals("serv", ex.getMessage());
        assertNotNull(ex.getCause());
    }

    @Test
    void asignacionInvalidaConMensajeYCausa() {
        AsignacionInvalidaException ex = new AsignacionInvalidaException("asig", new Exception("c"));
        assertEquals("asig", ex.getMessage());
        assertNotNull(ex.getCause());
    }

    @Test
    void matriculaInvalidaConMensajeYCausa() {
        MatriculaInvalidaException ex = new MatriculaInvalidaException("mat", new Exception("c"));
        assertEquals("mat", ex.getMessage());
        assertNotNull(ex.getCause());
    }

    @Test
    void datoInvalidoConMensajeYCausa() {
        DatoInvalidoException ex = new DatoInvalidoException("dato", new Exception("c"));
        assertEquals("dato", ex.getMessage());
        assertNotNull(ex.getCause());
    }
}
