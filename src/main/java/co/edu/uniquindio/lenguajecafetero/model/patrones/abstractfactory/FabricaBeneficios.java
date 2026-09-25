package co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;

import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;

import java.util.List;

public interface FabricaBeneficios {
    List<Beneficio> crearBeneficios();

    ServicioAdicional crearServicio();
}