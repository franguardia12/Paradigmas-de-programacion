package org.tp1.view;

import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class SubMenuHabilidadesView {

    public SubMenuHabilidadesView() {
    }
    public void mostrarHabilidades(Pokemon pokemon) {
        for (int i = 0; i < pokemon.getHabilidades().size(); i++) {
            HabilidadView habilidadView = new HabilidadView();
            Habilidades habilidad = pokemon.getHabilidad(i);
            habilidadView.mostrarHabilidad(i, habilidad);
        }
        System.out.println("\t\t< Ingrese -1 si desea salir del subMenu de Habilidades >");
    }

    public static void mostrarMensajeDelSubMenuHabilidades(Jugador jugador, Habilidades habilidadAct) {
        if (habilidadAct.getNombre().equals("VOLVER_ATRAS")) {
            System.out.printf("> Se ha salido del subMenu de Habilidades <\n");
        } else if (habilidadAct.getNombre().equals("NO_DISPONIBLE")) {
            System.out.printf("> La opcion de Habilidad que desea elegir no esta disponible <\n");
        } else if (habilidadAct.estaUsada()) {
            System.out.println("La Habilidad que desea elegir se encuentra sin usos disponibles");
        } else {
            System.out.printf(String.format("> %s Ha Seleccionado la Habilidad %s <\n", jugador.getNombre(), habilidadAct.getNombre()));
        }
    }


}
