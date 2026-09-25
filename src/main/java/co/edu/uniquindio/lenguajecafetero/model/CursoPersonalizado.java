package co.edu.uniquindio.lenguajecafetero.model;

public class CursoPersonalizado extends Curso {
    private static final double VALOR_POR_SESION = 20000.0;

    private int sesionesPorMes;
    private String nivelReferencia;
    private String objetivosEstudiante;

    public CursoPersonalizado(String codigo, String nombre, double precioBase, int duracionMeses,
                              int sesionesPorMes, String nivelReferencia, String objetivosEstudiante) {
        super(codigo, nombre, precioBase, duracionMeses);
        this.sesionesPorMes = sesionesPorMes;
        this.nivelReferencia = nivelReferencia;
        this.objetivosEstudiante = objetivosEstudiante;
    }

    public CursoPersonalizado(CursoPersonalizado curso) {
        super(curso);
        this.sesionesPorMes = curso.sesionesPorMes;
        this.nivelReferencia = curso.nivelReferencia;
        this.objetivosEstudiante = curso.objetivosEstudiante;
    }

    @Override
    public double calcularValor() {
        double valorMensual = getPrecioBase() + (sesionesPorMes * VALOR_POR_SESION);
        return valorMensual * getDuracionMeses();
    }

    @Override
    public Curso clonar() {
        return new CursoPersonalizado(this);
    }

    public int getSesionesPorMes() {
        return sesionesPorMes;
    }

    public void setSesionesPorMes(int sesionesPorMes) {
        this.sesionesPorMes = sesionesPorMes;
    }

    public String getNivelReferencia() {
        return nivelReferencia;
    }

    public void setNivelReferencia(String nivelReferencia) {
        this.nivelReferencia = nivelReferencia;
    }

    public String getObjetivosEstudiante() {
        return objetivosEstudiante;
    }

    public void setObjetivosEstudiante(String objetivosEstudiante) {
        this.objetivosEstudiante = objetivosEstudiante;
    }
}