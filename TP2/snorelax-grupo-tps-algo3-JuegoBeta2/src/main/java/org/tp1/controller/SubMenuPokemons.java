package org.tp1.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Estadisticas;
import org.tp1.model.PokemonsDependencias.Pokemon;
import org.tp1.view.SubMenuPokemonsView;

public class SubMenuPokemons {

    protected final Pokemon VOLVER_ATRAS = new Pokemon("VOLVER_ATRAS", "", -1, -1, null, new Estadisticas(-1, -1, -1));
    protected final Pokemon NO_DISPONIBLE = new Pokemon("NO_DISPONIBLE", "", 0, 0, null, new Estadisticas(0, 0, 0));


    public SubMenuPokemons(Jugador jugador, boolean sinMuertos, boolean conRetorno, boolean mostrarPokemons) throws InputMismatchException {
        if (mostrarPokemons) {
            SubMenuPokemonsView subMenuPokemonsView = new SubMenuPokemonsView();
            subMenuPokemonsView.mostrarSubMenu(jugador, conRetorno);
        }
        try {
            Pokemon pokemon = seleccionPokemon(jugador, conRetorno);
            ArrayList<Pokemon> disponibles = jugador.verPokemonsDisponibles(sinMuertos);
            if (conRetorno) {
                disponibles.add(VOLVER_ATRAS);
            }
            while (!disponibles.contains(pokemon)) {
                SubMenuPokemonsView.mostrarMensajeDelSubMenuPokemons(jugador, pokemon);
                pokemon = seleccionPokemon(jugador, conRetorno);
            }
            SubMenuPokemonsView.mostrarMensajeDelSubMenuPokemons(jugador, pokemon);
            if (pokemon != VOLVER_ATRAS) {
                jugador.setPokemonActual(pokemon);
            }
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado un numero");
            new SubMenuPokemons(jugador, sinMuertos, conRetorno, false);
        }
    }

    public Pokemon seleccionPokemon(Jugador jugador, boolean conRetorno) throws InputMismatchException {

        try {
            Scanner sc = new Scanner(System.in);
            int nroPokemon = sc.nextInt();
            if (conRetorno && nroPokemon == -1) {
                return VOLVER_ATRAS;
            }
            if (nroPokemon < 0 || nroPokemon >= jugador.getPokemons().size()) {
                return NO_DISPONIBLE;
            }
            return jugador.getPokemon(nroPokemon);
        } catch (InputMismatchException e) {
            throw e;
        }
    }

    public boolean esUnPokemonValido(Pokemon pokemon) {
        return pokemon != VOLVER_ATRAS;
    }

}

