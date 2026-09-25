package co.edu.uniquindio.lenguajecafetero.model;

import co.edu.uniquindio.lenguajecafetero.model.patrones.builder.MatriculaBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void registrarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public void registrarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void registrarServicio(ServicioAdicional servicio) {
        servicios.add(servicio);
    }

    public Matricula crearMatricula(Estudiante estudiante, Curso curso, int duracionContratada,
                                    double descuentoAplicado, List<ServicioAdicional> serviciosIncluidos) {
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

    public void asignarProfesor(Estudiante estudiante, Curso curso, Profesor profesor) {
        if (!(curso instanceof CursoPersonalizado)) {
            throw new IllegalArgumentException("Los profesores se asignan a estudiantes matriculados en cursos personalizados.");
        }
        Asignacion asignacion = new Asignacion(LocalDate.now(), estudiante, curso, profesor);
        asignaciones.add(asignacion);
    }

    public void solicitarServicio(Matricula matricula, ServicioAdicional servicio) {
        if (!servicio.isDisponible()) {
            throw new IllegalStateException("El servicio " + servicio.getNombre() + " no esta disponible.");
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

    public Estudiante buscarEstudiante(String documentoIdentidad) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getDocumentoIdentidad().equals(documentoIdentidad)) {
                return estudiante;
            }
        }
        return null;
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
        return estudiantes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<ServicioAdicional> getServicios() {
        return servicios;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public List<ServicioUtilizado> getServiciosUtilizados() {
        return serviciosUtilizados;
    }
}