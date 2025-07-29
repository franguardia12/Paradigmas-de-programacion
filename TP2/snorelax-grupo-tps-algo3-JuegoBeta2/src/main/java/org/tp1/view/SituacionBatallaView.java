package org.tp1.view;

import org.tp1.model.Jugabilidad.Jugador;

public class SituacionBatallaView {
    public SituacionBatallaView() {
    }
    public void mostrarSituacionBatalla(Jugador jugador1, Jugador jugador2) {
        System.out.println(jugador1.getNombre() + " se rinde :(\n");
        System.out.println("!!!!!!!!!!!!!!!!!!El JUGADOR  " + jugador2.getNombre().toUpperCase() + " ES EL GANADOR!!!!!!!!!!!!");
    }
}
