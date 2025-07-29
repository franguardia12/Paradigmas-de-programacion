package org.tp1;

import org.tp1.controller.*;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.Items.Item;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.Jugabilidad.Opciones;
import org.tp1.model.PokemonsDependencias.Pokemon;
import org.tp1.model.PokemonsDependencias.SerializacionByte;
import org.tp1.view.*;

import java.util.*;

public class JuegoController {

    protected Jugador jugadorActual;
    protected AdminDeJuego adminJuego;

    public JuegoController() {

        this.adminJuego = new AdminDeJuego(cargarNombresJugadores());
        this.jugadorActual = getAdminJuego().seleccionarJugadorPorPokemonMasVeloz();
    }

    public ArrayList<Jugador> cargarNombresJugadores() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ArrayList<String> nombresDefault = new ArrayList<>();
        ArrayList<String> nombresJugadores = new ArrayList<>();

        Mensaje mensaje = new Mensaje();

        nombresDefault.add("Jugador1");
        nombresDefault.add("Jugador2");

        for (String nombreDefault : nombresDefault) {
            String linea = String.format("%s por favor ingrese su nombre: ", nombreDefault);
            mensaje.setInfo(linea);
            MensajeView.mostrar(mensaje);
            String nombreJugador = sc.nextLine();
            nombresJugadores.add(nombreJugador);
        }

        Jugador jugador1 = new Jugador(nombresJugadores.get(0));
        Jugador jugador2 = new Jugador(nombresJugadores.get(1));

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        return jugadores;
    }

    public void jugar() {

        new SubMenuPokemons(getAdminJuego().getJugador1(), false, false, true);
        new SubMenuPokemons(getAdminJuego().getJugador2(), false, false, true);

        boolean seTermino = true;
        while (seTermino) {
            seTermino = this.leerEntrada();
        }
    }

    public boolean leerEntrada() {

        OpcionesView opcionesView = new OpcionesView();
        opcionesView.mostrarOpciones();

        try {
            int nroOpcion;
            nroOpcion = leerOpcionDeEntrada();

            if (Opciones.values()[nroOpcion] == Opciones.USAR_HABILIDAD) {

                SubMenuHabilidades subMenuHabilidades = new SubMenuHabilidades(getJugadorActual(), true);

                if (subMenuHabilidades.esUnaHabilidadValida()) {
                    Pokemon ataca = getJugadorActual().getPokemonActual();
                    Pokemon defiende = getAdminJuego().obtenerRivalActual(getJugadorActual()).getPokemonActual();

                    byte[] atacaAntSerializado = SerializacionByte.serializar(ataca);
                    byte[] defiendeAntSerializado = SerializacionByte.serializar(defiende);

                    Habilidades habilidad = subMenuHabilidades.getHabilidadSeleccionada();
                    AccionDeBatalla accionDeBatalla = getAdminJuego().usarHabilidad(ataca, defiende, habilidad);

                    Pokemon atacaAnt = SerializacionByte.deserializar(atacaAntSerializado);
                    Pokemon defiendeAnt = SerializacionByte.deserializar(defiendeAntSerializado);

                    AccionDeBatallaView.mostrarAccionDeBatallaHabilidad(atacaAnt, ataca, defiendeAnt, defiende, accionDeBatalla);

                    if (accionDeBatalla.isPasaDeTurno()) {
                        if (getAdminJuego().siguienteTurno()) {
                            setJugadorActual(getAdminJuego().obtenerRivalActual(getJugadorActual()));
                            return true;
                        }
                        return false;
                    }
                }

            } else if (Opciones.values()[nroOpcion] == Opciones.SELECCIONAR_ITEM) {

                SubMenuItems subMenuItems = new SubMenuItems(getJugadorActual(), true);

                if (subMenuItems.esUnItemInValido()) {
                    SubMenuItemsView.mostrarMensajeDelSubMenuDeItems(getJugadorActual(), subMenuItems.getItemSeleccionado());
                    return true;
                }

                Item item = subMenuItems.getItemSeleccionado();
                SubMenuPokemons subMenuPokemons = new SubMenuPokemons(getJugadorActual(), false, true, true);

                if (subMenuPokemons.esUnPokemonValido(getJugadorActual().getPokemonActual())) {

                    byte[] atacaAntSerializado = SerializacionByte.serializar(getJugadorActual().getPokemonActual());

                    AccionDeBatalla accionDeBatalla = getAdminJuego().usarItem(getJugadorActual().getPokemonActual(), item);
                    Pokemon pokemonAntes = SerializacionByte.deserializar(atacaAntSerializado);
                    AccionDeBatallaView.mostrarAccionDeBatallaItem(pokemonAntes, getJugadorActual().getPokemonActual(), accionDeBatalla);

                    if (accionDeBatalla.isPasaDeTurno()) {
                        if (getAdminJuego().siguienteTurno()) {
                            setJugadorActual(getAdminJuego().obtenerRivalActual(getJugadorActual()));
                            return true;
                        }
                        return false;
                    }
                }

            } else if (Opciones.values()[nroOpcion] == Opciones.INTERCAMBIAR_POKEMON) {
                SubMenuPokemons nuevoSubMenuPokemons = new SubMenuPokemons(getJugadorActual(), true, true, true);

            } else if (Opciones.values()[nroOpcion] == Opciones.CAMPOBATALLA) {
                Jugador jugadorAtaca = getJugadorActual();
                Jugador jugadorDefiende = getAdminJuego().obtenerRivalActual(jugadorAtaca);
                PantallaView pantallaView = new PantallaView();
                pantallaView.mostrarPantalla(jugadorAtaca, jugadorDefiende, getAdminJuego());

            } else if (Opciones.values()[nroOpcion] == Opciones.RENDIRSE) {
                Jugador jugadorAtaca = getJugadorActual();
                Jugador jugadorDefiende = getAdminJuego().obtenerRivalActual(jugadorAtaca);
                SituacionBatallaView situacionBatalla = new SituacionBatallaView();
                situacionBatalla.mostrarSituacionBatalla(jugadorAtaca, jugadorDefiende);
                return false;

            }
        } catch (InputMismatchException e) {
            System.out.printf("No se ha ingresado un numero\n");
            return true;
        }
        return true;
    }

    private void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public int leerOpcionDeEntrada() {

        Scanner sc = new Scanner(System.in);
        Mensaje mensaje = new Mensaje();
        mensaje.setInfo("< Ingrese un Numero de Opcion valida >");

        int opcionValida = -1;
        do {
            MensajeView.mostrar(mensaje);
            try {
                opcionValida = sc.nextInt();
            } catch (InputMismatchException e) {
                throw e;
            }

        } while (opcionValida >= Opciones.values().length || opcionValida < 0);
        return opcionValida;
    }

    public AdminDeJuego getAdminJuego() {
        return this.adminJuego;
    }


    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }

    public void setAdminJuego(AdminDeJuego adminJuego) {
        this.adminJuego = adminJuego;
    }
}






