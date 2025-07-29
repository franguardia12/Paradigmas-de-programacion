package org.tp1.model.Generadores;

import org.tp1.model.Habilidades.Habilidades ;
import org.tp1.model.Jugabilidad.Jugador ;
import org.tp1.model.PokemonsDependencias.Elemento ;
import org.tp1.model.PokemonsDependencias.Estadisticas ;
import org.tp1.model.PokemonsDependencias.Pokemon ;

import java.util.*;


public class GeneradorDePokemones {
    private ArrayList<String> nombres;

    private HashMap<String, Pokemon> pokemons;

    //post: genera instancia con pokemones ya cargados;
    public GeneradorDePokemones() {
        HashMap<String, Pokemon> pokemones = new HashMap<>();
        ArrayList<String> nombres = new ArrayList<>();
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon nuevoPokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        nuevoPokemon = new Pokemon(nombre, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Charmander";
        estadisticas = new Estadisticas(66, 33, 33);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de fuego con una llama en su cola", 7, 65, Elemento.FUEGO, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Squirtle";
        estadisticas = new Estadisticas(60, 30, 30);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo agua con una concha en su espalda", 4, 43, Elemento.AGUA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Jigglypuff";
        estadisticas = new Estadisticas(40, 20, 20);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo normal con habilidades de canto", 4, 20, Elemento.NORMAL, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Snorlax";
        estadisticas = new Estadisticas(58, 29, 29);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon gigante y dormilón que bloquea caminos", 6, 30, Elemento.NORMAL, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Gyarados";
        estadisticas = new Estadisticas(50, 25, 25);
        nuevoPokemon = new Pokemon(nombre, "Un poderoso Pokémon de tipo agua", 5, 81, Elemento.AGUA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Mewtwo";
        estadisticas = new Estadisticas(45, 25, 35);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon legendario de tipo psíquico", 4, 130, Elemento.PSIQUICO, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Gengar";
        estadisticas = new Estadisticas(45,35,20);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo fantasma", 5, 110, Elemento.FANTASMA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Machamp";
        estadisticas = new Estadisticas(50, 20, 40);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo lucha", 3, 55, Elemento.LUCHA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Eevee";
        estadisticas = new Estadisticas(23,35, 20);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon normal", 3, 55, Elemento.NORMAL, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Vaporeon";
        estadisticas = new Estadisticas(34, 40, 7);
        nuevoPokemon = new Pokemon(nombre, "Una de las evoluciones de Eevee de tipo agua", 7, 65, Elemento.AGUA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Blastoise";
        estadisticas = new Estadisticas(30, 33, 7);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo agua con cañones de agua en su espalda", 7, 78, Elemento.AGUA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Alakazam";
        estadisticas = new Estadisticas(40, 25, 5);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo psíquico", 4, 120, Elemento.PSIQUICO, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Venusaur";
        estadisticas = new Estadisticas(50, 15, 5);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo planta", 6, 80, Elemento.PLANTA, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        nombre = "Dragonite";
        estadisticas = new Estadisticas(33,33, 3);
        nuevoPokemon = new Pokemon(nombre, "Un Pokémon de tipo dragón", 3, 80, Elemento.DRAGON, estadisticas);
        pokemones.put(nombre, nuevoPokemon);
        nombres.add(nombre);
        this.pokemons = pokemones;
        this.nombres = nombres;
    }

    public void generarPokemonesParaJugadores(ArrayList<Jugador> jugadores) {
        GeneradorDeHabilidades generadorDeHabilidades = new GeneradorDeHabilidades();
        generadorDeHabilidades.crearGeneradorHabilidades();
        Set<Habilidades> habilidadesSeleccionadas = new HashSet<>();
        Set<Pokemon> pokemonsSeleccionados = new HashSet<>();
        for (Jugador jugador : jugadores) {
            ArrayList<Pokemon> pokemonsJugador = new ArrayList<>();
            while (pokemonsJugador.size() != 6) {
                Random random = new Random();
                int aleatorio = random.nextInt(nombres.size());
                String nombrePokemon = nombres.get(aleatorio);
                Pokemon pokemonActual = pokemons.get(nombrePokemon);
                if (pokemonsSeleccionados.contains(pokemonActual)) {
                    continue;
                }
                pokemonsSeleccionados.add(pokemonActual);
                ArrayList<Habilidades> habilidadesPokemon = new ArrayList<>();
                while (habilidadesPokemon.size() != 4) {
                    Habilidades habilidad = generadorDeHabilidades.devolverHabilidadAleatoria();
                    if (habilidadesSeleccionadas.contains(habilidad)) {
                        continue;
                    }
                    habilidadesPokemon.add(habilidad);
                    habilidadesSeleccionadas.add(habilidad);
                }
                pokemonActual.setHabilidades(habilidadesPokemon);
                pokemonsJugador.add(pokemonActual);
            }
            jugador.setPokemons(pokemonsJugador);
        }
    }

    public HashMap<String, Pokemon> getPokemons() {
        return pokemons;
    }

    public Pokemon getPokemon(String nombre) {
        return getPokemons().get(nombre);
    }
}
