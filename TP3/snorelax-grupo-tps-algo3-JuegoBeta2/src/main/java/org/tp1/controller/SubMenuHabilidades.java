package org.tp1.controller;

import org.tp1.model.Habilidades.HabilidadAtaque;
import org.tp1.model.Habilidades.HabilidadModificacionEstado;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Pokemon;
import org.tp1.view.SubMenuHabilidadesView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubMenuHabilidades {

    // utilizamos HabilidadModificacionEstado porque debe ser una clase concreta para ser final. (podria haber sido otra)
    protected final Habilidades VOLVER_ATRAS = new HabilidadModificacionEstado("VOLVER_ATRAS", 0, null);
    // IDEM pero con HabilidadAtaque
    protected final Habilidades NO_DISPONIBLE =  new HabilidadAtaque("NO_DISPONIBLE", 0, null);
    protected Habilidades HabilidadSeleccionada = null;

    public SubMenuHabilidades(Jugador jugador, boolean mostrarHabilidades) {

        if (mostrarHabilidades) {
            SubMenuHabilidadesView menuHabilidades = new SubMenuHabilidadesView();
            menuHabilidades.mostrarHabilidades(jugador.getPokemonActual());
        }
        try {
            Habilidades habilidad = seleccionHabilidad(jugador.getPokemonActual());
            ArrayList<Habilidades> disponibles = jugador.getPokemonActual().verHabilidadesDisponibles();
            disponibles.add(VOLVER_ATRAS);

            while (!disponibles.contains(habilidad)) {
                SubMenuHabilidadesView.mostrarMensajeDelSubMenuHabilidades(jugador, habilidad);
                habilidad = seleccionHabilidad(jugador.getPokemonActual());
            }
            SubMenuHabilidadesView.mostrarMensajeDelSubMenuHabilidades(jugador, habilidad);
            setHabilidadSeleccionada(habilidad);
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado un numero");
            new SubMenuHabilidades(jugador, false);
        }

    }

    protected Habilidades seleccionHabilidad(Pokemon pokemon) {

        try {
            Scanner sc = new Scanner(System.in);
            int nroHabilidad = sc.nextInt();
            if (nroHabilidad == -1) {
                return VOLVER_ATRAS;
            } else if (nroHabilidad < 0 || nroHabilidad >= pokemon.getHabilidades().size()) {
                return NO_DISPONIBLE;
            }
            return pokemon.getHabilidad(nroHabilidad);
        } catch (InputMismatchException e) {
            throw e;
        }
    }

    public boolean esUnaHabilidadValida() {
        if (getHabilidadSeleccionada() != null) {
            return getHabilidadSeleccionada() != VOLVER_ATRAS;
        }
        return false;
    }

    public void setHabilidadSeleccionada(Habilidades habilidadSeleccionada) {
        HabilidadSeleccionada = habilidadSeleccionada;
    }

    public Habilidades getHabilidadSeleccionada() {
        return HabilidadSeleccionada;
    }
}
