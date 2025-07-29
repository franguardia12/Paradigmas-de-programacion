package org.tp1.model.Jugabilidad;

import org.tp1.JuegoController;
import org.tp1.controller.AdminDeJuego;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.PokemonsDependencias.Pokemon;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class Juego {

    protected List<Jugador> jugadores;

    protected AdminDeJuego adminJuego;

    protected Jugador jugadorActual;

    public Juego(List<String> nombresJugadores) {
        Jugador jugador1 = new Jugador(nombresJugadores.get(0));
        Jugador jugador2 = new Jugador(nombresJugadores.get(1));

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        this.jugadores = jugadores;
        this.adminJuego = new AdminDeJuego(jugadores);
        this.jugadorActual = getAdminJuego().seleccionarJugadorPorPokemonMasVeloz();
    }

    public AdminDeJuego getAdminJuego() {
        return this.adminJuego;
    }

   // public void inicializarJuego() {
        // getJuegoController().jugar();
    // }

    public void proximoTurno() {
        boolean pasaDeTurno = this.adminJuego.siguienteTurno();
        if (pasaDeTurno) {
            this.jugadorActual = obtenerRival(getJugadorActual());
        } else {
            // manejar que se gano el Juego por alguien, lanzando algun evento quizas
        }

    }

    public AccionDeBatalla atacar(Pokemon ataca, Pokemon defiende, Habilidades habilidad) {
        return getAdminJuego().usarHabilidad(ataca, defiende, habilidad);
    }


    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }

    public Jugador obtenerRival(Jugador jugadorActual) {
        return  getAdminJuego().obtenerRivalActual(jugadorActual);
    }

    public Pokemon getPokemonActual() {
        return getJugadorActual().getPokemonActual();
    }

    public Pokemon getPokemonRival() {
        return obtenerRival(getJugadorActual()).getPokemonActual();
    }

    public Habilidades getHabilidadAtacante(int n) {
        return getJugadorActual().getPokemonActual().getHabilidad(n);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
