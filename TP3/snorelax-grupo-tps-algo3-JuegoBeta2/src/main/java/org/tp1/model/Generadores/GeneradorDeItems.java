package org.tp1.model.Generadores;

import org.tp1.model.Items.*;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Estadistica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class GeneradorDeItems {

    public HashMap<Integer, Item> items;
    public GeneradorDeItems() {

        HashMap items = new HashMap<Integer, Item>();

        int i = 0;
        Item itemCuraTodo = new ItemCuraTodo(2);
        items.put(i, itemCuraTodo);
        i = 1;
        Item itemRevivir = new ItemRevivir("Revivir", 2);
        items.put(i, itemRevivir);
        i = 2;
        Item itemEstadisticaAtaque = new ItemModificacionDeEstadistica("AumentaAtaque", 2, Estadistica.ATAQUE, 1.2);
        items.put(i, itemEstadisticaAtaque);
        i = 3;
        Item itemEstadisticaDefensa = new ItemModificacionDeEstadistica("AumentaDefensa", 2, Estadistica.DEFENSA, 1.2);
        items.put(i, itemEstadisticaDefensa);
        i = 4;
        Item itemRestauracionPocaVida = new ItemRestauracionDeVida("Pocion", 2, 20);
        items.put(i, itemRestauracionPocaVida);
        i = 5;
        Item itemRestauracionMediumVida = new ItemRestauracionDeVida("MegaPocion", 2, 50);
        items.put(i, itemRestauracionMediumVida);
        i = 6;
        Item itemRestauracionAltaVida = new ItemRestauracionDeVida("HiperPocion", 1, 100);
        items.put(6, itemRestauracionAltaVida);
        i = 7;
        Item itemPocionMolestaAlumnos = new pocionMolestaAlumnos(2);
        items.put(7, itemPocionMolestaAlumnos);
        setItems(items);

    }
    public void setItems(HashMap<Integer, Item> items) {
        this.items = items;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public void generarItemsParaJugadores(ArrayList<Jugador> jugadores) {

        HashSet<Item> seleccionadosJugador;

        for (Jugador jugador : jugadores) {
            ArrayList<Item> itemsJugador = new ArrayList<>();
            seleccionadosJugador = new HashSet<>();
            while (itemsJugador.size() != 4) {
                Random random = new Random();
                int aleatorio = random.nextInt(getItems().size());
                Item item = getItems().get(aleatorio);
                if (!seleccionadosJugador.contains(item)) {
                    itemsJugador.add(item);
                    seleccionadosJugador.add(item);
                }
            }
            jugador.setInventario(itemsJugador);
        }
    }
}
