package co.edu.uniquindio.lenguajecafetero.model;

import co.edu.uniquindio.lenguajecafetero.exception.AsignacionInvalidaException;
import co.edu.uniquindio.lenguajecafetero.exception.CursoNoDisponibleException;
import co.edu.uniquindio.lenguajecafetero.exception.DatoInvalidoException;
import co.edu.uniquindio.lenguajecafetero.exception.EstudianteNoEncontradoException;
import co.edu.uniquindio.lenguajecafetero.exception.MatriculaInvalidaException;
import co.edu.uniquindio.lenguajecafetero.exception.ServicioNoDisponibleException;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficios;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosRegular;
import co.edu.uniquindio.lenguajecafetero.model.patrones.builder.MatriculaBuilder;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorCursoRegular;
import co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod.CreadorDeCursos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Academia {
    private static Academia instancia;

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private List<Curso> cursos;
    private List<ServicioAdicional> servicios;
    private List<Matricula> matriculas;
    private List<Asignacion> asignaciones;
    private List<ServicioUtilizado> serviciosUtilizados;

    private Academia(String nombreComercial, String nit, String direccion, String telefono,
                     String correo, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.matriculas = new ArrayList<>();
        this.asignaciones = new ArrayList<>();
        this.serviciosUtilizados = new ArrayList<>();
    }

    public static Academia getInstance() {
        if (instancia == null) {
            instancia = new Academia(
                    "Lenguajecafetero",
                    "900123456-7",
                    "Cra 14 Nro 12-34, Armenia",
                    "315 000 0000",
                    "contacto@lenguajecafetero.co",
                    "www.lenguajecafetero.co");
        }
        return instancia;
    }

    public void limpiar() {
        estudiantes.clear();
        profesores.clear();
        cursos.clear();
        servicios.clear();
        matriculas.clear();
        asignaciones.clear();
        serviciosUtilizados.clear();
    }

    public void registrarEstudiante(Estudiante estudiante) throws DatoInvalidoException {
        if (estudiante == null || esVacio(estudiante.getNombre()) || esVacio(estudiante.getDocumentoIdentidad())) {
            throw new DatoInvalidoException("Nombre y documento son obligatorios.");
        }
        for (Estudiante existente : estudiantes) {
            if (existente.getDocumentoIdentidad().equals(estudiante.getDocumentoIdentidad())) {
                throw new DatoInvalidoException(
                        "Ya existe un estudiante con documento: " + estudiante.getDocumentoIdentidad());
            }
        }
        estudiantes.add(estudiante);
    }

    public void registrarProfesor(Profesor profesor) throws DatoInvalidoException {
        if (profesor == null || esVacio(profesor.getNombre()) || esVacio(profesor.getIdentificacion())) {
            throw new DatoInvalidoException("Nombre e identificacion son obligatorios.");
        }
        for (Profesor existente : profesores) {
            if (existente.getIdentificacion().equals(profesor.getIdentificacion())) {
                throw new DatoInvalidoException(
                        "Ya existe un profesor con identificacion: " + profesor.getIdentificacion());
            }
        }
        profesores.add(profesor);
    }

    public void registrarServicio(ServicioAdicional servicio) throws DatoInvalidoException {
        if (servicio == null || esVacio(servicio.getCodigo()) || esVacio(servicio.getNombre())) {
            throw new DatoInvalidoException("Codigo y nombre son obligatorios para el servicio.");
        }
        for (ServicioAdicional existente : servicios) {
            if (existente.getCodigo().equals(servicio.getCodigo())) {
                throw new DatoInvalidoException(
                        "Ya existe un servicio con codigo: " + servicio.getCodigo());
            }
        }
        servicios.add(servicio);
    }

    public Curso registrarCursoRegular(String codigo, String nombre, Idioma idioma, String descripcion,
                                       double valorMensual, int duracionMeses) throws DatoInvalidoException {
        validarDatosCurso(codigo, nombre, idioma, valorMensual, duracionMeses);
        Curso curso = crearCursoConBeneficios(
                new CreadorCursoRegular(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses),
                new FabricaBeneficiosRegular());
        registrarCurso(curso);
        return curso;
    }

    public Curso registrarCursoIntensivo(String codigo, String nombre, Idioma idioma, String descripcion,
                                         double valorMensual, int duracionMeses) throws DatoInvalidoException {
        validarDatosCurso(codigo, nombre, idioma, valorMensual, duracionMeses);
        Curso curso = crearCursoConBeneficios(
                new CreadorCursoIntensivo(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses),
                new FabricaBeneficiosIntensivo());
        registrarCurso(curso);
        return curso;
    }

    public Curso registrarCursoPersonalizado(String codigo, String nombre, Idioma idioma, String descripcion,
                                             double valorMensual, int duracionMeses, int sesionesPorMes,
                                             NivelIdioma nivelReferencia, String objetivosEstudiante)
            throws DatoInvalidoException {
        validarDatosCurso(codigo, nombre, idioma, valorMensual, duracionMeses);
        if (sesionesPorMes <= 0 || nivelReferencia == null || esVacio(objetivosEstudiante)) {
            throw new DatoInvalidoException("Sesiones, nivel y objetivos son obligatorios para el curso personalizado.");
        }
        Curso curso = crearCursoConBeneficios(
                new CreadorCursoPersonalizado(codigo, nombre, idioma, descripcion,
                        valorMensual, duracionMeses, sesionesPorMes, nivelReferencia, objetivosEstudiante),
                new FabricaBeneficiosPersonalizado());
        registrarCurso(curso);
        return curso;
    }

    public void registrarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void actualizarEstadoCurso(String codigo, EstadoCurso nuevoEstado) throws DatoInvalidoException {
        if (nuevoEstado == null) {
            throw new DatoInvalidoException("Seleccione el nuevo estado del curso.");
        }
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigo)) {
                curso.setEstado(nuevoEstado);
                return;
            }
        }
        throw new DatoInvalidoException("No se encontro curso con codigo: " + codigo);
    }

    public Matricula crearMatricula(Estudiante estudiante, Curso curso, int duracionContratada,
                                    double descuentoAplicado, List<ServicioAdicional> serviciosIncluidos)
            throws MatriculaInvalidaException, CursoNoDisponibleException, DatoInvalidoException,
            ServicioNoDisponibleException {
        if (estudiante == null || curso == null) {
            throw new MatriculaInvalidaException("La matricula requiere estudiante y curso.");
        }
        if (curso.getEstado() != EstadoCurso.ACTIVO) {
            throw new CursoNoDisponibleException(
                    "El curso " + curso.getNombre() + " no esta disponible. Estado: " + curso.getEstado());
        }
        if (duracionContratada <= 0) {
            throw new DatoInvalidoException("La duracion contratada debe ser mayor a cero.");
        }
        if (duracionContratada > curso.getDuracionMeses()) {
            throw new DatoInvalidoException(
                    "La duracion contratada no puede superar los " + curso.getDuracionMeses()
                            + " meses del curso.");
        }
        if (descuentoAplicado < 0 || descuentoAplicado > 1) {
            throw new DatoInvalidoException("El descuento debe estar entre 0 y 1.");
        }
        for (Matricula existente : matriculas) {
            if (existente.getEstudiante().getDocumentoIdentidad().equals(estudiante.getDocumentoIdentidad())
                    && existente.getCurso().getCodigo().equals(curso.getCodigo())) {
                throw new MatriculaInvalidaException(
                        "El estudiante ya se encuentra matriculado en el curso " + curso.getNombre() + ".");
            }
        }
        if (serviciosIncluidos != null) {
            for (ServicioAdicional servicio : serviciosIncluidos) {
                if (!servicio.isDisponible()) {
                    throw new ServicioNoDisponibleException(
                            "El servicio " + servicio.getNombre() + " no esta disponible.");
                }
            }
        }
        MatriculaBuilder builder = new MatriculaBuilder()
                .conEstudiante(estudiante)
                .conCurso(curso)
                .conDuracion(duracionContratada)
                .aplicarDescuento(descuentoAplicado);
        if (serviciosIncluidos != null) {
            for (ServicioAdicional servicio : serviciosIncluidos) {
                builder.agregarServicio(servicio);
            }
        }
        Matricula matricula = builder.build();
        matricula.setNumero("M-" + (matriculas.size() + 1));
        matriculas.add(matricula);
        return matricula;
    }

    public void asignarProfesor(Estudiante estudiante, Curso curso, Profesor profesor)
            throws AsignacionInvalidaException {
        if (!(curso instanceof CursoPersonalizado)) {
            throw new AsignacionInvalidaException(
                    "Los profesores se asignan a estudiantes matriculados en cursos personalizados.");
        }
        if (!estaMatriculadoEn(estudiante, curso)) {
            throw new AsignacionInvalidaException(
                    "El estudiante " + estudiante.getNombre() + " no esta matriculado en el curso "
                            + curso.getNombre() + ".");
        }
        if (!curso.getIdioma().equals(profesor.getIdiomaEnsenado())) {
            throw new AsignacionInvalidaException(
                    "El profesor " + profesor.getNombre() + " ensena "
                            + profesor.getIdiomaEnsenado() + " y el curso personalizado es de "
                            + curso.getIdioma() + ".");
        }
        for (Asignacion existente : asignaciones) {
            if (existente.getEstudiante().getDocumentoIdentidad().equals(estudiante.getDocumentoIdentidad())
                    && existente.getCurso().getCodigo().equals(curso.getCodigo())) {
                throw new AsignacionInvalidaException(
                        "El curso " + curso.getNombre() + " ya tiene el profesor "
                                + existente.getProfesor().getNombre() + " asignado para "
                                + estudiante.getNombre() + ".");
            }
        }
        Asignacion asignacion = new Asignacion(LocalDate.now(), estudiante, curso, profesor);
        asignaciones.add(asignacion);
    }

    public void solicitarServicio(Matricula matricula, ServicioAdicional servicio)
            throws ServicioNoDisponibleException, DatoInvalidoException {
        if (matricula == null || servicio == null) {
            throw new DatoInvalidoException("La matricula y el servicio son requeridos.");
        }
        if (!servicio.isDisponible()) {
            throw new ServicioNoDisponibleException(
                    "El servicio " + servicio.getNombre() + " no esta disponible.");
        }
        for (ServicioAdicional incluido : matricula.getServiciosIncluidos()) {
            if (incluido.getCodigo().equals(servicio.getCodigo())) {
                throw new DatoInvalidoException(
                        "El servicio " + servicio.getNombre() + " ya esta incluido en la matricula "
                                + matricula.getNumero() + ".");
            }
        }
        matricula.agregarServicioIncluido(servicio);
        ServicioUtilizado servicioUtilizado = new ServicioUtilizado(LocalDate.now(), matricula, servicio);
        serviciosUtilizados.add(servicioUtilizado);
    }

    public double ingresosPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        double total = 0;
        for (Matricula matricula : matriculas) {
            LocalDate fecha = matricula.getFecha();
            if ((fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio))
                    && (fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin))) {
                total += matricula.getValorFinal();
            }
        }
        return total;
    }

    public Estudiante buscarEstudiante(String documentoIdentidad) throws EstudianteNoEncontradoException {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getDocumentoIdentidad().equals(documentoIdentidad)) {
                return estudiante;
            }
        }
        throw new EstudianteNoEncontradoException(
                "No se encontro estudiante con documento: " + documentoIdentidad);
    }

    public List<Curso> getCursosPersonalizados() {
        List<Curso> resultado = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso instanceof CursoPersonalizado) {
                resultado.add(curso);
            }
        }
        return resultado;
    }

    public List<Curso> getCursosActivos() {
        List<Curso> resultado = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getEstado() == EstadoCurso.ACTIVO) {
                resultado.add(curso);
            }
        }
        return resultado;
    }

    public List<Matricula> getMatriculasDeEstudiante(String documentoIdentidad) {
        List<Matricula> resultado = new ArrayList<>();
        for (Matricula matricula : matriculas) {
            if (matricula.getEstudiante().getDocumentoIdentidad().equals(documentoIdentidad)) {
                resultado.add(matricula);
            }
        }
        return resultado;
    }

    public List<Asignacion> getAsignacionesDeEstudiante(String documentoIdentidad) {
        List<Asignacion> resultado = new ArrayList<>();
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getEstudiante().getDocumentoIdentidad().equals(documentoIdentidad)) {
                resultado.add(asignacion);
            }
        }
        return resultado;
    }

    public List<Asignacion> getAsignacionesDeProfesor(String identificacion) {
        List<Asignacion> resultado = new ArrayList<>();
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getProfesor().getIdentificacion().equals(identificacion)) {
                resultado.add(asignacion);
            }
        }
        return resultado;
    }

    public List<Estudiante> getEstudiantesDeProfesor(String identificacion) {
        List<Estudiante> resultado = new ArrayList<>();
        for (Asignacion asignacion : asignaciones) {
            if (!asignacion.getProfesor().getIdentificacion().equals(identificacion)) {
                continue;
            }
            Estudiante estudiante = asignacion.getEstudiante();
            if (!yaEstaLosEstudiantes(resultado, estudiante.getDocumentoIdentidad())) {
                resultado.add(estudiante);
            }
        }
        return resultado;
    }

    private boolean yaEstaLosEstudiantes(List<Estudiante> lista, String documentoIdentidad) {
        for (Estudiante estudiante : lista) {
            if (estudiante.getDocumentoIdentidad().equals(documentoIdentidad)) {
                return true;
            }
        }
        return false;
    }

    private boolean estaMatriculadoEn(Estudiante estudiante, Curso curso) {
        for (Matricula matricula : matriculas) {
            if (matricula.getEstudiante().getDocumentoIdentidad().equals(estudiante.getDocumentoIdentidad())
                    && matricula.getCurso().getCodigo().equals(curso.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    private void validarDatosCurso(String codigo, String nombre, Idioma idioma,
                                   double valorMensual, int duracionMeses) throws DatoInvalidoException {
        if (esVacio(codigo) || esVacio(nombre) || idioma == null) {
            throw new DatoInvalidoException("Codigo, nombre e idioma son obligatorios para el curso.");
        }
        if (valorMensual < 0 || duracionMeses <= 0) {
            throw new DatoInvalidoException("El valor mensual y la duracion deben ser validos.");
        }
        for (Curso existente : cursos) {
            if (existente.getCodigo().equals(codigo)) {
                throw new DatoInvalidoException("Ya existe un curso con codigo: " + codigo);
            }
        }
    }

    private Curso crearCursoConBeneficios(CreadorDeCursos creador, FabricaBeneficios fabrica) {
        Curso curso = creador.crearCurso();
        for (Beneficio beneficio : fabrica.crearBeneficios()) {
            curso.agregarBeneficio(beneficio);
        }
        return curso;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public List<Estudiante> getEstudiantes() {
        return Collections.unmodifiableList(estudiantes);
    }

    public List<Profesor> getProfesores() {
        return Collections.unmodifiableList(profesores);
    }

    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }

    public List<ServicioAdicional> getServicios() {
        return Collections.unmodifiableList(servicios);
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    public List<Asignacion> getAsignaciones() {
        return Collections.unmodifiableList(asignaciones);
    }

    public List<ServicioUtilizado> getServiciosUtilizados() {
        return Collections.unmodifiableList(serviciosUtilizados);
    }
}
