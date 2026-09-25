package co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;

import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;

import java.util.ArrayList;
import java.util.List;

public class FabricaBeneficiosRegular implements FabricaBeneficios {
    @Override
    public List<Beneficio> crearBeneficios() {
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(new Beneficio("Material didactico", "Guia de estudio y ejercicios impresos"));
        beneficios.add(new Beneficio("Clubes de conversacion", "Encuentros presenciales de practica oral"));
        return beneficios;
    }

    @Override
    public ServicioAdicional crearServicio() {
        return new ServicioAdicional("S-REG-01", "Refuerzo presencial",
                "Clase de refuerzo en la sede", 40000, true);
    }
}