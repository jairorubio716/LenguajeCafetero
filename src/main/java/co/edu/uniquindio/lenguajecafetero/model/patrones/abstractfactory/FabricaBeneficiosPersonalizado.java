package co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;

import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;

import java.util.ArrayList;
import java.util.List;

public class FabricaBeneficiosPersonalizado implements FabricaBeneficios {
    @Override
    public List<Beneficio> crearBeneficios() {
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(new Beneficio("Seguimiento personalizado", "Evaluacion periodica del avance del estudiante"));
        beneficios.add(new Beneficio("Club de conversacion premium", "Sesiones adicionales con nativos"));
        return beneficios;
    }

    @Override
    public ServicioAdicional crearServicio() {
        return new ServicioAdicional("S-PER-01", "Simulacro de examen internacional",
                "Simulacro IELTS/TOEFL con retroalimentacion", 90000, true);
    }
}