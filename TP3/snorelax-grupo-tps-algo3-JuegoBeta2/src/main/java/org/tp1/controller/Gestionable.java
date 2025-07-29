package org.tp1.controller;

import org.tp1.model.Items.Item;
import org.tp1.model.Jugabilidad.Jugador;

public interface Gestionable {

    void seleccionarHabilidadActual();

    void seleccionarPokemonActual(Jugador jugador);

    public boolean usarHabilidad();

    public boolean usarItem(Item item);

    public boolean seleccionarItem();


    public void verCampoDeBatalla();


    public void rendirse();

}
