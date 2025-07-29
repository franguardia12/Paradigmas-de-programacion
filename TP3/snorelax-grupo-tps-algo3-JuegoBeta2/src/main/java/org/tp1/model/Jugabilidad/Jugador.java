package org.tp1.model.Jugabilidad;

import org.tp1.model.Items.Item;
import org.tp1.model.PokemonsDependencias.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private ArrayList<Pokemon> pokemons;
    protected List<Item> inventario;
    public Pokemon pokemonActual;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Item> verItemsDisponibles() {
        ArrayList<Item> disponibles = new ArrayList<>();
        for (Item item : getInventario()) {
            if (!item.estaUsado()) {
                disponibles.add(item);
            }
        }
        return disponibles;
    }

    public ArrayList<Pokemon> verPokemonsDisponibles(boolean sinMuertos) {
        ArrayList<Pokemon> disponibles = new ArrayList<>();
        for (Pokemon pokemon : getPokemons()) {
            if (!sinMuertos || !pokemon.estaMuerto()) {
                disponibles.add(pokemon);
            }
        }
        return disponibles;
    }

    public Pokemon getPokemonActual() {
        return pokemonActual;
    }

    public Item getItem(int nroItem) {
        return getInventario().get(nroItem);
    }

    public List<Item> getInventario() {
        return inventario;
    }


    public Pokemon getPokemon(int nroPokemon) {
        return getPokemons().get(nroPokemon);
    }

    public void setPokemonActual(Pokemon pokemonActual) {
        this.pokemonActual = pokemonActual;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
}
