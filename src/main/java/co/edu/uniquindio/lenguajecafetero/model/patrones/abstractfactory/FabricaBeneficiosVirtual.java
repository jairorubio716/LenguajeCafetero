package co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;

import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;

import java.util.ArrayList;
import java.util.List;

public class FabricaBeneficiosVirtual implements FabricaBeneficios {
    @Override
    public List<Beneficio> crearBeneficios() {
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(new Beneficio("Acceso a plataforma virtual", "Material en linea disponible 24/7"));
        beneficios.add(new Beneficio("Simulacros en linea", "Practicas interactivas autocalificadas"));
        return beneficios;
    }

    @Override
    public ServicioAdicional crearServicio() {
        return new ServicioAdicional("S-VIR-01", "Simulacro de examen virtual",
                "Examen de practica en linea con resultados", 60000, true);
    }
}