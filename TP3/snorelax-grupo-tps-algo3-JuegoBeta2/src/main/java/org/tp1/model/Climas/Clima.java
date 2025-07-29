package org.tp1.model.Climas;

import org.tp1.model.PokemonsDependencias.Elemento;
import org.tp1.model.PokemonsDependencias.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Clima implements Serializable {
    protected TipoClima tipoClima;
    protected ArrayList<Elemento> favoreceElementos;

    public Clima(TipoClima tipoClima, ArrayList<Elemento> favoreceElementos) {
        this.tipoClima = tipoClima;
        this.favoreceElementos = favoreceElementos;
    }

    public boolean climaFavorece(Pokemon pokemon) {
        return getFavoreceElementos().contains(pokemon.getElemento());
    }

    public boolean esClimaDanino() {
        TipoClima tipoClima = getTipoClima();
        if (tipoClima == TipoClima.TORMENTA_DE_ARENA || tipoClima == TipoClima.HURACAN || tipoClima == TipoClima.TORMENTA_DE_RAYOS) {
            return true;
        }
        return false;
    }

    public ArrayList<Elemento> getFavoreceElementos() {
        return favoreceElementos;
    }

    public TipoClima getTipoClima() {
        return tipoClima;
    }
}
