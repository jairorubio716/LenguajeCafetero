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
        academia.limpiar();

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
    void registrarEstudianteYBuscar() throws Exception {
        academia.registrarEstudiante(estudiante);
        Estudiante encontrado = academia.buscarEstudiante("1001");
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void buscarEstudianteNoEncontrado() {
        assertThrows(EstudianteNoEncontradoException.class, () -> academia.buscarEstudiante("9999"));
    }

    @Test
    void registrarProfesorCursoYServicio() throws Exception {
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
    void asignarProfesorACursoPersonalizado() throws Exception {
        academia.crearMatricula(estudiante, cursoPersonalizado, 2, 0, null);
        academia.asignarProfesor(estudiante, cursoPersonalizado, profesor);
        assertEquals(1, academia.getAsignaciones().size());
    }

    @Test
    void asignarProfesorACursoNoPersonalizado() {
        assertThrows(AsignacionInvalidaException.class,
                () -> academia.asignarProfesor(estudiante, cursoRegular, profesor));
    }

    @Test
    void asignarProfesorAEstudianteNoMatriculado() {
        assertThrows(AsignacionInvalidaException.class,
                () -> academia.asignarProfesor(estudiante, cursoPersonalizado, profesor));
    }

    @Test
    void asignarProfesorDebeEnsenarElIdiomaDelCurso() throws Exception {
        academia.crearMatricula(estudiante, cursoPersonalizado, 2, 0, null);
        Profesor profesoraFrances = new Profesor("Marta", "Calle 3", "303", "marta@mail.com",
                "P2", Idioma.FRANCES, 60000);
        assertThrows(AsignacionInvalidaException.class,
                () -> academia.asignarProfesor(estudiante, cursoPersonalizado, profesoraFrances));
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
    void actualizarEstadoCursoCambiaEstado() throws Exception {
        Curso curso = academia.registrarCursoRegular("C9", "Aleman Basico", Idioma.INGLES,
                "Basico", 90000, 4);
        assertEquals(EstadoCurso.ACTIVO, curso.getEstado());
        academia.actualizarEstadoCurso("C9", EstadoCurso.SUSPENDIDO);
        assertEquals(EstadoCurso.SUSPENDIDO, curso.getEstado());
        assertTrue(academia.getCursosActivos().isEmpty());
    }

    @Test
    void cursoNoActivoNoAdmiteMatricula() throws Exception {
        Curso curso = academia.registrarCursoRegular("C10", "Frances Basico", Idioma.FRANCES,
                "Basico", 90000, 4);
        academia.actualizarEstadoCurso("C10", EstadoCurso.FINALIZADO);
        assertThrows(CursoNoDisponibleException.class,
                () -> academia.crearMatricula(estudiante, curso, 3, 0, null));
    }

    @Test
    void cambiarEstadoCursoInexistente() {
        assertThrows(DatoInvalidoException.class,
                () -> academia.actualizarEstadoCurso("ZZZ", EstadoCurso.ACTIVO));
    }

    @Test
    void cursosModificablesCambianEntreEstados() throws Exception {
        Curso curso = academia.registrarCursoIntensivo("C11", "Ingles Intensivo", Idioma.INGLES,
                "Full", 150000, 2);
        academia.actualizarEstadoCurso("C11", EstadoCurso.SUSPENDIDO);
        academia.actualizarEstadoCurso("C11", EstadoCurso.ACTIVO);
        academia.actualizarEstadoCurso("C11", EstadoCurso.FINALIZADO);
        assertEquals(EstadoCurso.FINALIZADO, curso.getEstado());
    }

    @Test
    void asignarProfesorNoSeRepiteParaMismoEstudianteYCurso() throws Exception {
        academia.crearMatricula(estudiante, cursoPersonalizado, 2, 0, null);
        academia.asignarProfesor(estudiante, cursoPersonalizado, profesor);
        assertThrows(AsignacionInvalidaException.class,
                () -> academia.asignarProfesor(estudiante, cursoPersonalizado, profesor));
        assertEquals(1, academia.getAsignaciones().size());
    }

    @Test
    void estudianteNoSeMatriculaDosVecesEnElMismoCurso() throws Exception {
        academia.crearMatricula(estudiante, cursoRegular, 3, 0, null);
        assertThrows(MatriculaInvalidaException.class,
                () -> academia.crearMatricula(estudiante, cursoRegular, 3, 0, null));
        assertEquals(1, academia.getMatriculas().size());
    }

    @Test
    void servicioNoSeSolicitaDosVecesEnLaMismaMatricula() throws Exception {
        Matricula matricula = academia.crearMatricula(estudiante, cursoRegular, 3, 0, null);
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.TUTORIA_REFUERZO,
                "S2", "Tutoria", "Refuerzo", 50000, true);
        academia.solicitarServicio(matricula, servicio);
        assertThrows(DatoInvalidoException.class,
                () -> academia.solicitarServicio(matricula, servicio));
    }

    @Test
    void servicioRegistradoDuplicado() throws Exception {
        ServicioAdicional servicio = new ServicioAdicional(TipoServicio.MATERIAL_IMPRESO,
                "S1", "Material", "Impreso", 20000, true);
        academia.registrarServicio(servicio);
        assertThrows(DatoInvalidoException.class, () -> academia.registrarServicio(servicio));
        assertEquals(1, academia.getServicios().size());
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
