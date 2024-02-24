import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private ArrayList<Pokemon> pokemons;

    protected List<Item> inventario;

    protected Item itemSeleccionado;
    public Pokemon pokemonActual;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String nombre, ArrayList<Pokemon> pokemones) {
        this.nombre = nombre;
        this.pokemons = pokemones;
        this.pokemonActual = pokemones.get(0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void mostrarResumenDePoquemones() {
        for(Pokemon p: pokemons){
            System.out.println(p.getNombre() + " con " + p.getEstadisticas().getVida() + " de vidad.");
        }
        System.out.println();
    }

    public void setPokemonActual(Pokemon pokemonActual) {
        this.pokemonActual = pokemonActual;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
}
