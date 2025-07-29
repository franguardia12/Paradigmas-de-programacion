package org.tp1.model.Items;

import org.tp1.controller.Mensaje;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.PokemonsDependencias.Pokemon;

import java.nio.channels.AcceptPendingException;
import java.util.Map;

public class ItemRevivir extends Item {
    public ItemRevivir(String nombre, Integer unidades) {
        super(nombre, unidades);
    }

    @Override
    public AccionDeBatalla usar(Pokemon pokemon) {

        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();
        if (!pokemon.estaMuerto()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else {
            descartarUnaUnidad();
            pokemon.revivirPokemon();
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setItemUsado(this);
        return accionDeBatalla;
    }

}
