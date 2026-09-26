package co.edu.uniquindio.lenguajecafetero.model;

import co.edu.uniquindio.lenguajecafetero.exception.AsignacionInvalidaException;
import co.edu.uniquindio.lenguajecafetero.exception.CursoNoDisponibleException;
import co.edu.uniquindio.lenguajecafetero.exception.DatoInvalidoException;
import co.edu.uniquindio.lenguajecafetero.exception.EstudianteNoEncontradoException;
import co.edu.uniquindio.lenguajecafetero.exception.MatriculaInvalidaException;
import co.edu.uniquindio.lenguajecafetero.exception.ServicioNoDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcademiaTest {

    private Academia academia;
    private Estudiante estudiante;
    private CursoRegular cursoRegular;
    private CursoPersonalizado cursoPersonalizado;
    private Profesor profesor;

    @BeforeEach
    void setUp() {
        academia = Academia.getInstance();
        academia.getEstudiantes().clear();
        academia.getProfesores().clear();
        academia.getCursos().clear();
        academia.getServicios().clear();
        academia.getMatriculas().clear();
        academia.getAsignaciones().clear();
        academia.getServiciosUtilizados().clear();

        estudiante = new Estudiante("Ana", "Calle 1", "300", "ana@mail.com",
                "1001", 20, LocalDate.of(2024, 1, 10));
        cursoRegular = new CursoRegular("C1", "Ingles Regular", Idioma.INGLES,
                "Basico", 100000, 3);
        cursoPersonalizado = new CursoPersonalizado("C2", "Ingles Personalizado", Idioma.INGLES,
                "Uno a uno", 80000, 2, 4, NivelIdioma.B1, "Fluidez");
        profesor = new Profesor("Luis", "Calle 2", "301", "luis@mail.com",
                "P1", Idioma.INGLES, 50000);
    }

    @Test
    void getInstanceEsSingleton() {
        Academia otra = Academia.getInstance();
        assertTrue(academia == otra);
    }

    @Test
    void registrarEstudianteYBuscar() throws EstudianteNoEncontradoException {
        academia.registrarEstudiante(estudiante);
        Estudiante encontrado = academia.buscarEstudiante("1001");
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void buscarEstudianteNoEncontrado() {
        assertThrows(EstudianteNoEncontradoException.class, () -> academia.buscarEstudiante("9999"));
    }

    @Test
    void registrarProfesorCursoYServicio() {
        academia.registrarProfesor(profesor);
        academia.registrarCurso(cursoRegular);
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.MATERIAL_IMPRESO,
                "S1", "Material", "Impreso", 20000, true);
        academia.registrarServicio(servicio);
        assertEquals(1, academia.getProfesores().size());
        assertEquals(1, academia.getCursos().size());
        assertEquals(1, academia.getServicios().size());
    }

    @Test
    void crearMatriculaCorrecta() throws Exception {
        Matricula matricula = academia.crearMatricula(estudiante, cursoRegular, 3, 0, null);
        assertEquals("M-1", matricula.getNumero());
        assertEquals(300000, matricula.getValorFinal(), 0.01);
        assertEquals(1, academia.getMatriculas().size());
    }

    @Test
    void crearMatriculaCursoNoActivo() {
        cursoRegular.setEstado(EstadoCurso.SUSPENDIDO);
        assertThrows(CursoNoDisponibleException.class,
                () -> academia.crearMatricula(estudiante, cursoRegular, 3, 0, null));
    }

    @Test
    void crearMatriculaSinEstudiante() {
        assertThrows(MatriculaInvalidaException.class,
                () -> academia.crearMatricula(null, cursoRegular, 3, 0, null));
    }

    @Test
    void crearMatriculaDuracionInvalida() {
        assertThrows(DatoInvalidoException.class,
                () -> academia.crearMatricula(estudiante, cursoRegular, 0, 0, null));
    }

    @Test
    void crearMatriculaServicioNoDisponible() {
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.MATERIAL_IMPRESO,
                "S1", "Material", "Impreso", 20000, false);
        List<ServicioAdicional> lista = new ArrayList<>();
        lista.add(servicio);
        assertThrows(ServicioNoDisponibleException.class,
                () -> academia.crearMatricula(estudiante, cursoRegular, 3, 0, lista));
    }

    @Test
    void asignarProfesorACursoPersonalizado() throws AsignacionInvalidaException {
        academia.asignarProfesor(estudiante, cursoPersonalizado, profesor);
        assertEquals(1, academia.getAsignaciones().size());
    }

    @Test
    void asignarProfesorACursoNoPersonalizado() {
        assertThrows(AsignacionInvalidaException.class,
                () -> academia.asignarProfesor(estudiante, cursoRegular, profesor));
    }

    @Test
    void solicitarServicioDisponible() throws Exception {
        Matricula matricula = academia.crearMatricula(estudiante, cursoRegular, 3, 0, null);
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.TUTORIA_REFUERZO,
                "S2", "Tutoria", "Refuerzo", 50000, true);
        academia.solicitarServicio(matricula, servicio);
        assertEquals(1, academia.getServiciosUtilizados().size());
        assertEquals(350000, matricula.getValorFinal(), 0.01);
    }

    @Test
    void solicitarServicioNoDisponible() throws Exception {
        Matricula matricula = academia.crearMatricula(estudiante, cursoRegular, 3, 0, null);
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.TUTORIA_REFUERZO,
                "S2", "Tutoria", "Refuerzo", 50000, false);
        assertThrows(ServicioNoDisponibleException.class,
                () -> academia.solicitarServicio(matricula, servicio));
    }

    @Test
    void ingresosPorPeriodoDentroYFueraDeRango() throws Exception {
        Matricula m1 = academia.crearMatricula(estudiante, cursoRegular, 3, 0, null);
        m1.setFecha(LocalDate.of(2024, 3, 15));

        Estudiante e2 = new Estudiante("Beto", "Calle", "302", "b@mail.com",
                "1002", 22, LocalDate.now());
        Matricula m2 = academia.crearMatricula(e2, cursoRegular, 3, 0, null);
        m2.setFecha(LocalDate.of(2025, 1, 10));

        double ingresos = academia.ingresosPorPeriodo(
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
        assertEquals(300000, ingresos, 0.01);

        double fuera = academia.ingresosPorPeriodo(
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
        assertEquals(0, fuera, 0.01);
    }

    @Test
    void datosBasicosAcademia() {
        assertEquals("Lenguajecafetero", academia.getNombreComercial());
        assertEquals("900123456-7", academia.getNit());
        assertTrue(academia.getDireccion().contains("Armenia"));
        assertNotNullTelefono();
    }

    private void assertNotNullTelefono() {
        assertTrue(academia.getTelefono() != null);
        assertTrue(academia.getCorreo() != null);
        assertTrue(academia.getPaginaWeb() != null);
    }
}
