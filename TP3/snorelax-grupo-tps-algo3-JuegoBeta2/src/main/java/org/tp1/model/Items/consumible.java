package org.tp1.model.Items;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.PokemonsDependencias.Pokemon;

public interface consumible {
    public AccionDeBatalla usar(Pokemon pokemon);
}
