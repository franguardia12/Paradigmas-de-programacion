package org.tp1.view;

import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.controller.AdminDeJuego;

public class PantallaView {
    public void mostrarPantalla(Jugador jugador1, Jugador jugador2, AdminDeJuego adminjuego) {
        System.out.printf("-----------------------Situacion Actual en turno: %d de %s . <<Clima: %s>> ------------------------\n", adminjuego.getTurno(), jugador1.getNombre(), adminjuego.getClimaActual().getClima().getTipoClima().toString());
        System.out.printf("%s --> Pokemon Actual: %s\n", jugador1.getNombre(), jugador1.getPokemonActual().getNombre());
        SubMenuPokemonsView subMenuPokemonsView = new SubMenuPokemonsView();
        subMenuPokemonsView.mostrarSubMenu(jugador1, false);
        System.out.println();
        System.out.printf("%s --> Pokemon Actual: %s\n", jugador2.getNombre(), jugador2.getPokemonActual().getNombre());
        subMenuPokemonsView.mostrarSubMenu(jugador2, false);
    }
}
