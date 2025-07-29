package org.tp1.view;

import org.tp1.controller.Mensaje;

public class MensajeView {

    public static void mostrar(Mensaje mensaje) {
        System.out.println(mensaje.getInfo());
    }
}
