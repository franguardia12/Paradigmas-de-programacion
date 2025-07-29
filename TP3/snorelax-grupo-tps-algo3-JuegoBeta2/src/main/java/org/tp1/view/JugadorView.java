package org.tp1.view;

import org.tp1.model.Jugabilidad.Jugador;

public class JugadorView {
    public void mostrarJugador(Jugador jugador) {
        System.out.printf("Jugador: %s\n", jugador.getNombre());
    }
}
