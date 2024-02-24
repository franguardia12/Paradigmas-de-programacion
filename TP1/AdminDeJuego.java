import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminDeJuego implements Gestionable {
    private Opciones[] opciones;
    private int turno = 0;
    private Jugador jugador1, jugador2;
    private Jugador jugadorActual;

    public String campoDeBatalla;
    protected AdministradorHabilidades adminHabilidades;

    public AdminDeJuego(ArrayList<Jugador> jugadores) {
        //setear atributos jugador1 y jugador a partir del pokemon mas veloz
        prepararJugadores(jugadores);
        this.jugador1 = jugadores.get(0);
        this.jugador2 = jugadores.get(1);
        TablaElementos tabla = new TablaElementos("efectividades.csv");
        this.adminHabilidades = new AdministradorHabilidades(tabla, jugador1, jugador2);
        this.opciones = Opciones.values();
    }

    protected void prepararJugadores(ArrayList<Jugador> jugadores) {
        GeneradorDePokemones generadorDePokemones = new GeneradorDePokemones();
        generadorDePokemones.generarPokemonesParaJugadores(jugadores);
    }

    public int ingresarOpcion() {
        System.out.println("Las Opciones son:");
        for (Opciones opcion : Opciones.values()) {
            System.out.println("Opcion: " + opcion.ordinal() + " -> " + opcion);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un numero valido de acuerdo a la opcion:");
        int opcionValida = sc.nextInt(); // se podria chequear si la opcion es valida o no
        return opcionValida;
    }

    public void siguienteTurno() {
        turno++;
       actualizar();
       System.out.println("Turno nro " + turno + ", le toca a " + jugadorActual.getNombre());
    }

    public void actualizar() {

        if ((turno % 2) == 0) {
            this.jugadorActual = jugador1;
        } else {
            this.jugadorActual = jugador2;
        }
    }

    public Jugador obtenerRivalActual() {
        if ((turno % 2) == 0) {
            return jugador2;
        }
        return jugador1;
    }

    @Override
    public void usarHabilidad() {
        adminHabilidades.usarHabilidadActual();
    }

    @Override
    public void usarItem() {
        Item item = jugadorActual.itemSeleccionado;
        Pokemon pokemon = jugadorActual.pokemonActual;
        item.consumir(pokemon);
    }

    @Override
    public void seleccionarItem(String nombre) {
        for (Item item : jugadorActual.inventario) {
            if (item.nombre == nombre && !item.estaUsado()) {
                this.jugadorActual.itemSeleccionado = item;
                return;
            }
        }
    }

    @Override
    public void seleccionarHabilidad(String nombre) {
        List<Habilidades> habilidades = jugadorActual.pokemonActual.habilidades;
        for (Habilidades habilidad : habilidades) {
            if (habilidad.getNombre() == nombre && !habilidad.estaUsada() ) {
                this.jugadorActual.pokemonActual.habilidadActual = habilidad;
                return;
            }
        }
        System.out.printf("La Habilidad %s a seleccionar no se encuentra Disponible\n", nombre);
    }

    @Override
    public void verHabilidadesDisponibles() {
        List<Habilidades> habilidades = jugadorActual.pokemonActual.habilidades;
        for (Habilidades habilidad : habilidades) {
            if (!habilidad.estaUsada()) {
                System.out.printf("Habilidad: %s, UsosDisponibles: %d\n", habilidad.getNombre(), habilidad.getUsos());
            }
        }
    }

    @Override
    public void verItemsDisponibles() {
        for (Item item : jugadorActual.inventario) {
            if (!item.estaUsado()) {
                System.out.printf("Item: %s, UsosDisponibles: %d\n", item.nombre, item.unidades);
            }
        }
    }

    @Override
    public void verCampoDeBatalla() { //TODO
        System.out.println("-----------------------Situacion Actual en turno " + turno + "------------------------");
        System.out.println("Situacion de: " + jugador1.getNombre());
        jugador1.mostrarResumenDePoquemones();
        System.out.println();
        System.out.println("Situacion de: " + jugador2.getNombre());
        jugador2.mostrarResumenDePoquemones();
    }

    @Override
    public void verPokemonsDisponibles() {
        ArrayList<Pokemon> pokemons = jugadorActual.getPokemons();
        System.out.println("Estado de los pokemones de " + jugadorActual.getNombre());
        for (Pokemon pokemon : pokemons) {
            //System.out.printf("Pokemon: %s, Estado: %s, Vida: %d, Elemento %s", pokemon.nombre, pokemon.estado.toString(), pokemon.estadisticas.vida, pokemon.elemento.toString());
            System.out.println(pokemon.getNombre());
        }
    }

    @Override
    public void intercambiarPokemonActual(Pokemon pokemon) {
        ArrayList<Pokemon> pokemons = jugadorActual.getPokemons();
        for (Pokemon pokemonPosible : pokemons) {
            if (pokemonPosible == pokemon) {
                this.jugadorActual.pokemonActual = pokemonPosible;
                return;
            }
        }
    }

    public int getTurno() {
        return turno;
    }

    @Override
    public void rendirse() {
        System.out.println(jugadorActual.getNombre() + " se rinde :(");
        cantarVictoria(obtenerRivalActual());
    }

    private void cantarVictoria(Jugador ganador) {
        System.out.println("!!!!!!!!!!!!!!!!!!El JUGADOR  " + ganador.getNombre().toUpperCase() + " ES EL GANADOR!!!!!!!!!!!!");
    }
}
