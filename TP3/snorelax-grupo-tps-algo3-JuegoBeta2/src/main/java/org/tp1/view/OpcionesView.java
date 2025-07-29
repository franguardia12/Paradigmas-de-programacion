package org.tp1.view;

import org.tp1.model.Jugabilidad.Opciones;

public class OpcionesView {

    public OpcionesView() {
    }

    public void mostrarOpciones() {
        for (int i = 0; i < Opciones.values().length; i++) {
            System.out.printf("%d %s\n", i, Opciones.values()[i]);
        }
    }
}
