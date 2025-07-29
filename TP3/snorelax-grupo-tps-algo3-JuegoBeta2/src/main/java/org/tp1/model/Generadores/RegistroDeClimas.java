package org.tp1.model.Generadores;

import org.tp1.model.Climas.Clima;
import org.tp1.model.Climas.TipoClima;
import org.tp1.model.PokemonsDependencias.Elemento;

import java.util.ArrayList;
import java.util.HashMap;

public class RegistroDeClimas {

    protected HashMap<TipoClima, Clima> climas;

    public RegistroDeClimas() {
        HashMap<TipoClima, Clima> climas = new HashMap<>();
        ArrayList<Elemento> favoreceElementos = new ArrayList<>();
        Clima sinClima = new Clima(TipoClima.SIN_CLIMA, favoreceElementos);
        climas.put(TipoClima.SIN_CLIMA, sinClima);
        favoreceElementos.add(Elemento.FUEGO);
        Clima climaSoleado = new Clima(TipoClima.SOLEADO, favoreceElementos);
        climas.put(TipoClima.SOLEADO, climaSoleado);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.AGUA);
        favoreceElementos.add(Elemento.PLANTA);
        Clima climaLluvioso = new Clima(TipoClima.LLUVIA, favoreceElementos);
        climas.put(TipoClima.LLUVIA, climaLluvioso);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.ROCA);
        favoreceElementos.add(Elemento.TIERRA);
        Clima climaTormentaArena = new Clima(TipoClima.TORMENTA_DE_ARENA, favoreceElementos);
        climas.put(TipoClima.TORMENTA_DE_ARENA, climaTormentaArena);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.PSIQUICO);
        favoreceElementos.add(Elemento.FANTASMA);
        Clima climaNuboso = new Clima(TipoClima.NIEBLA, favoreceElementos);
        climas.put(TipoClima.NIEBLA, climaNuboso);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.ELECTRICO);
        Clima climaTormentaElectrica = new Clima(TipoClima.TORMENTA_DE_RAYOS, favoreceElementos);
        climas.put(TipoClima.TORMENTA_DE_RAYOS, climaTormentaElectrica);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.VOLADOR);
        Clima climaHuracan = new Clima(TipoClima.HURACAN, favoreceElementos);
        climas.put(TipoClima.HURACAN, climaHuracan);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.HIELO);
        Clima climaNieve = new Clima(TipoClima.NIEVE, favoreceElementos);
        climas.put(TipoClima.NIEVE, climaNieve);
        favoreceElementos = new ArrayList<>();
        favoreceElementos.add(Elemento.DRAGON);
        favoreceElementos.add(Elemento.FUEGO);
        favoreceElementos.add(Elemento.ROCA);
        Clima climaVolcanErupcion = new Clima(TipoClima.ERUPCION_VOLCAN, favoreceElementos);
        climas.put(TipoClima.ERUPCION_VOLCAN, climaVolcanErupcion);
        this.climas = climas;
    }

    public HashMap<TipoClima, Clima> getClimas() {
        return climas;
    }
}

