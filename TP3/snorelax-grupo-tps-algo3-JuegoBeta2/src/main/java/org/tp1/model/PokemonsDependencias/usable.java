package org.tp1.model.PokemonsDependencias;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.ManipularCsv.TablaElementos;

public interface usable {
    public AccionDeBatalla usar(Pokemon ataca, Pokemon defiende, TablaElementos tabla, TupleClima tupleClima);
}
