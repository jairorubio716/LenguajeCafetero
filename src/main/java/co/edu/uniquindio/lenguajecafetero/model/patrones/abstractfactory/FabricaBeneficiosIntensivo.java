package co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;

import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import co.edu.uniquindio.lenguajecafetero.model.TipoBeneficio;
import co.edu.uniquindio.lenguajecafetero.model.TipoServicio;

import java.util.ArrayList;
import java.util.List;

public class FabricaBeneficiosIntensivo implements FabricaBeneficios {
    @Override
    public List<Beneficio> crearBeneficios() {
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(new Beneficio(TipoBeneficio.PLATAFORMA_VIRTUAL,
                "Acceso a plataforma virtual", "Material en linea disponible 24/7"));
        beneficios.add(new Beneficio(TipoBeneficio.MATERIAL_DIDACTICO,
                "Material didactico intensivo", "Ejercicios y guias de profundizacion"));
        return beneficios;
    }

    @Override
    public ServicioAdicional crearServicio() {
        return new ServicioAdicional(TipoServicio.TUTORIA_REFUERZO, "S-INT-01",
                "Tutoria de refuerzo", "Clases de refuerzo con el profesor", 50000, true);
    }
}