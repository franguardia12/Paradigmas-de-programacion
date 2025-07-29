package org.tp1.model.Items;

import org.tp1.controller.Mensaje;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.PokemonsDependencias.Estadistica;
import org.tp1.model.PokemonsDependencias.Pokemon;

import java.nio.channels.AcceptPendingException;

public class pocionMolestaAlumnos extends Item {

    public pocionMolestaAlumnos(int unidades) {
        super("pocionMolestaAlumnos", unidades);
    }

    @Override
    public AccionDeBatalla usar(Pokemon pokemon) {

        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();

        if (pokemon.estaMuerto()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else {
            super.descartarUnaUnidad();
            double porcentajeAumento = 1.3;
            pokemon.modificarEstadistica(Estadistica.VIDA, porcentajeAumento);
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setItemUsado(this);
        return accionDeBatalla;
    }
}

