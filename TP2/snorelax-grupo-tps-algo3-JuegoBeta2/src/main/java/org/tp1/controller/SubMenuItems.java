package org.tp1.controller;

import org.tp1.model.Items.Item;
import org.tp1.model.Items.ItemRestauracionDeVida;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.view.MenuItemsView;
import org.tp1.view.SubMenuItemsView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubMenuItems {

    protected final Item VOLVER_ATRAS = new ItemRestauracionDeVida("VOLVER_ATRAS", -1, -1);
    protected final Item NO_DISPONIBLE = new ItemRestauracionDeVida("NO_DISPONIBLE", 0, 0);

    protected Item itemSeleccionado;

    public SubMenuItems(Jugador jugador, boolean mostrarItems) {

        if (mostrarItems) {
            SubMenuItemsView.mostrarItems(jugador);
        }

        try {
            Item item = seleccionItem(jugador);
            ArrayList<Item> disponibles = jugador.verItemsDisponibles();
            disponibles.add(VOLVER_ATRAS);

            while (!disponibles.contains(item)) {
                SubMenuItemsView.mostrarMensajeDelSubMenuDeItems(jugador, item);
                item = seleccionItem(jugador);
            }
            setItemSeleccionado(item);
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado un numero");
            new SubMenuItems(jugador, false);
        }

    }

    public Item seleccionItem(Jugador jugador) {

        try {
            Scanner sc = new Scanner(System.in);
            int nroItem = sc.nextInt();
            if (nroItem == -1) {
                return VOLVER_ATRAS;
            } else if (nroItem < 0 || nroItem >= jugador.getInventario().size()) {
                return NO_DISPONIBLE;
            }
            return jugador.getInventario().get(nroItem);
        } catch (InputMismatchException e) {
            throw e;
        }
    }

    public boolean esUnItemInValido() {
        return getItemSeleccionado() == null || getItemSeleccionado() == VOLVER_ATRAS;
    }

    public void setItemSeleccionado(Item itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public Item getItemSeleccionado() {
        return itemSeleccionado;
    }
}
