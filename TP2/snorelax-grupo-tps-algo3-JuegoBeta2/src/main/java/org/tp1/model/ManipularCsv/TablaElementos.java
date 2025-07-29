package org.tp1.model.ManipularCsv;

import org.tp1.model.PokemonsDependencias.Elemento;

import java.util.Map;

public class TablaElementos {
    public Map<Elemento, Map<Elemento, Double>> efectividades;

    public TablaElementos() {
        ManipularCSV archivo = new ManipularCSV();
        Map<Elemento, Map<Elemento, Double>> efectividades = archivo.cargarEfectividadesElementos();
        this.efectividades = efectividades;
    }

    public Double comparar(Elemento elemento1, Elemento elemento2) {
        return efectividades.get(elemento1).get(elemento2);
    }

}
