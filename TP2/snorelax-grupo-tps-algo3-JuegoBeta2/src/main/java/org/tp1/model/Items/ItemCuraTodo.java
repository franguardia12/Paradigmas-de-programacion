package org.tp1.model.Items;

import org.tp1.controller.Mensaje;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.PokemonsDependencias.Estado;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class ItemCuraTodo extends Item {

    public ItemCuraTodo(Integer unidades) {
        super("ItemCuraTodo", unidades);
    }
    @Override
    public AccionDeBatalla usar(Pokemon pokemon) {

        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();

        if (pokemon.estaNormal()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else if (pokemon.estaMuerto()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else {
            super.descartarUnaUnidad();
            pokemon.setEstado(Estado.NORMAL);
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setItemUsado(this);
        return accionDeBatalla;
    }

}