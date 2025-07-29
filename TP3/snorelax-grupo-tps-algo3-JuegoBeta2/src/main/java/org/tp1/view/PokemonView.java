package org.tp1.view;

import org.tp1.model.PokemonsDependencias.Pokemon;

public class PokemonView {

    public static void mostrarPokemon(Pokemon pokemon) {
        System.out.println(formatearPokemon(pokemon));
    }

    public static String formatearPokemon(Pokemon p) {
        return String.format("%s ---> || [Vida: %d]--[Nivel: %d]--[Elemento: %s] || {{ Estadisticas ---> Ataque: %d, Defensa: %d }}\n", p.getNombre(), p.getVida(), p.getNivel(), p.getElemento().toString(), p.getAtaque(), p.getDefensa());
    }
}
