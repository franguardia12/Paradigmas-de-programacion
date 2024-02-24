import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private final AdminDeJuego administrador;

    public Juego() {
        ArrayList<Jugador> jugadores = cargarDatosJuego();
        this.administrador = new AdminDeJuego(jugadores);
    }

    public ArrayList<Jugador> cargarDatosJuego() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ArrayList<String> nombresDefault = new ArrayList<>();
        ArrayList<String> nombresJugadores = new ArrayList<>();

        nombresDefault.add("Jugador1");
        nombresDefault.add("Jugador2");

        for (String nombreDefault : nombresDefault) {
            System.out.printf("%s por favor ingrese su nombre: ", nombreDefault);
            String nombreJugador = sc.nextLine();
            nombresJugadores.add(nombreJugador);
        }

        Jugador jugador1 = new Jugador(nombresJugadores.get(0));
        Jugador jugador2 = new Jugador(nombresJugadores.get(1));

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        return jugadores;
    }

    public void inicializarJuego() {

        int nroOpcion;
        do {
            nroOpcion= administrador.ingresarOpcion();
            if(Opciones.values()[nroOpcion] == Opciones.CAMPOBATALLA) {
               administrador.verCampoDeBatalla();
            }
            else if(Opciones.values()[nroOpcion] == Opciones.RENDIRSE) {
                administrador.rendirse();
            }
            else if(Opciones.values()[nroOpcion] == Opciones.POKEMONS) {
                administrador.verPokemonsDisponibles();
            }
            else if(Opciones.values()[nroOpcion] == Opciones.HABILIDADES) {
                administrador.verHabilidadesDisponibles();
            }
        } while (Opciones.values()[nroOpcion] != Opciones.RENDIRSE);
    }

    public AdminDeJuego getAdministrador() {
        return administrador;
    }
}
