import java.util.*;


public class GeneradorDePokemones {

    private ArrayList<Pokemon> pokemones;

    //post: genera instancia con pokemones ya cargados;
    public GeneradorDePokemones() {
        pokemones = new ArrayList<Pokemon>();
        pokemones.add(new Pokemon("Pikachu", "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO));
        pokemones.add(new Pokemon("Bulbasaur", "Un pequeño Pokémon que lleva una planta en su espalda", 10, 45, Elemento.PLANTA));
        pokemones.add(new Pokemon("Charmander", "Un Pokémon de fuego con una llama en su cola", 8, 65, Elemento.FUEGO));
        pokemones.add(new Pokemon("Squirtle", "Un Pokémon de tipo agua con una concha en su espalda", 9, 43, Elemento.AGUA));
        pokemones.add(new Pokemon("Jigglypuff", "Un Pokémon de tipo normal con habilidades de canto", 7, 20, Elemento.NORMAL));
        pokemones.add(new Pokemon("Snorlax", "Un Pokémon gigante y dormilón que bloquea caminos", 15, 30, Elemento.NORMAL));
        pokemones.add(new Pokemon("Gyarados", "Un poderoso Pokémon de tipo agua", 18, 81, Elemento.AGUA));
        pokemones.add(new Pokemon("Mewtwo", "Un Pokémon legendario de tipo psíquico", 30, 130, Elemento.PSIQUICO));
        pokemones.add(new Pokemon("Gengar", "Un Pokémon de tipo fantasma", 25, 110, Elemento.FANTASMA));
        pokemones.add(new Pokemon("Machamp", "Un Pokémon de tipo lucha", 22, 55, Elemento.LUCHA));
        pokemones.add(new Pokemon("Eevee", "Un Pokémon normal", 12, 55, Elemento.NORMAL));
        pokemones.add(new Pokemon("Vaporeon", "Una de las evoluciones de Eevee de tipo agua", 12, 65, Elemento.AGUA));
        pokemones.add(new Pokemon("Blastoise", "Un Pokémon de tipo agua con cañones de agua en su espalda", 11, 78, Elemento.AGUA));
        pokemones.add(new Pokemon("Alakazam", "Un Pokémon de tipo psíquico", 19, 120, Elemento.PSIQUICO));
        pokemones.add(new Pokemon("Venusaur", "Un Pokémon de tipo planta", 14, 80, Elemento.PLANTA));
        pokemones.add(new Pokemon("Dragonite", "Un Pokémon de tipo dragón", 20, 80, Elemento.DRAGON));
    }

    //pokemones debe tener como minimo 6 pokemones.
    // devuelve una lista con los primeros  6 pokemons de pokemones
    public void generarPokemonesParaJugadores(List<Jugador> jugadores) {

        GeneradorDeHabilidades generadorDeHabilidades = new GeneradorDeHabilidades();
        for (Jugador jugador : jugadores) {
            ArrayList<Pokemon> pokemonsJugador = new ArrayList<>();
            Set<Habilidades> habilidadesSeleccionas = new HashSet<>();
            for (int i = 0; i < 6; i++) {
                Pokemon pokemonActual = pokemones.get(i);
                while (habilidadesSeleccionas.size() != 3) {
                    Habilidades habilidad = generadorDeHabilidades.devolverHabilidadAleatoria();
                    if (habilidadesSeleccionas.contains(habilidad)) {
                        continue;
                    }
                    pokemonActual.anadirHabilidad(habilidad);
                }
                pokemonsJugador.add(pokemonActual);
            }
            jugador.setPokemons(pokemonsJugador);
        }
    }
}
