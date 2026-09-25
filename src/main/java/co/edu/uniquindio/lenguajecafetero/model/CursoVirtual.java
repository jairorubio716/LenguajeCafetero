package co.edu.uniquindio.lenguajecafetero.model;

public class CursoVirtual extends Curso {
    private boolean plataformaVirtual;

    public CursoVirtual(String codigo, String nombre, double precioBase, int duracionMeses, boolean plataformaVirtual) {
        super(codigo, nombre, precioBase, duracionMeses);
        this.plataformaVirtual = plataformaVirtual;
    }

    public CursoVirtual(CursoVirtual curso) {
        super(curso);
        this.plataformaVirtual = curso.plataformaVirtual;
    }

    @Override
    public double calcularValor() {
        double valorBase = getPrecioBase();
        if (plataformaVirtual) {
            valorBase = valorBase * 1.15;
        }
        return valorBase * getDuracionMeses();
    }

    @Override
    public Curso clonar() {
        return new CursoVirtual(this);
    }

    public boolean isPlataformaVirtual() {
        return plataformaVirtual;
    }

    public void setPlataformaVirtual(boolean plataformaVirtual) {
        this.plataformaVirtual = plataformaVirtual;
    }
}