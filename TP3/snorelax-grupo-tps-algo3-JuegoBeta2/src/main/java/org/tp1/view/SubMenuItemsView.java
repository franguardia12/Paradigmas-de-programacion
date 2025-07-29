package org.tp1.view;

import org.tp1.model.Items.Item;
import org.tp1.model.Jugabilidad.Jugador;

public class SubMenuItemsView {

    public SubMenuItemsView() {
    }

    public static void mostrarItems(Jugador jugador) {
        for (int i = 0; i < jugador.getInventario().size(); i++) {
            ItemView itemView = new ItemView();
            Item item = jugador.getInventario().get(i);
            itemView.mostrarItem(i, item);
        }
        System.out.println("\t\t< Ingrese -1 si desea salir del subMenu de Items >");
    }

    public static void mostrarMensajeDelSubMenuDeItems(Jugador jugador, Item item) {

        if (esUnRetorno(item)) {
            System.out.printf("> Se ha salido del subMenu de Items <\n");
        } else if (esUnItemNoValido(item)) {
            System.out.printf("> La opcion de Item que desea elegir no esta disponible <\n");
        } else if (item.estaUsado()) {
            System.out.println("El Item que desea elegir se encuentra sin usos disponibles");
        } else {
            System.out.printf("%s Ha seleccionado el Item %s\n", jugador.getNombre(), item.getNombre());
        }

    }
    protected static boolean esUnRetorno(Item item) {
        return item == null || item.getNombre().equals("VOLVER_ATRAS");
    }

    protected static boolean esUnItemNoValido(Item item) {
        if (item.getNombre().equals("NO_DISPONIBLE")) {
            return true;
        }
        return false;
    }



}
