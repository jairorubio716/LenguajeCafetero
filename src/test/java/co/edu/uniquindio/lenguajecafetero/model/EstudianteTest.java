package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstudianteTest {

    @Test
    void crearEstudianteCorrecto() {
        Estudiante estudiante = new Estudiante("Ana", "Calle 1", "300", "ana@mail.com",
                "1001", 20, LocalDate.of(2024, 1, 10));
        assertEquals("Ana", estudiante.getNombre());
        assertEquals("1001", estudiante.getDocumentoIdentidad());
        assertEquals(20, estudiante.getEdad());
        assertEquals(LocalDate.of(2024, 1, 10), estudiante.getFechaRegistro());
        assertEquals("Estudiante: Ana (CC 1001)", estudiante.mostrarInfo());
    }

    @Test
    void settersEstudiante() {
        Estudiante estudiante = new Estudiante("Ana", "Calle 1", "300", "ana@mail.com",
                "1001", 20, LocalDate.of(2024, 1, 10));
        estudiante.setDocumentoIdentidad("2002");
        estudiante.setEdad(25);
        estudiante.setFechaRegistro(LocalDate.of(2025, 2, 1));
        estudiante.setNombre("Ana Maria");
        assertEquals("2002", estudiante.getDocumentoIdentidad());
        assertEquals(25, estudiante.getEdad());
        assertEquals(LocalDate.of(2025, 2, 1), estudiante.getFechaRegistro());
        assertEquals("Ana Maria", estudiante.getNombre());
    }
}
