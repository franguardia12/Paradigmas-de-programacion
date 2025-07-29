package org.tp1.view;

import org.tp1.model.Habilidades.Habilidades;

public class HabilidadView {

    public HabilidadView() {
    }

    public void mostrarHabilidad(int i, Habilidades habilidad) {
        System.out.printf("%d Habilidad: %s, Usos: %s\n", i, habilidad.getNombre(), habilidad.getUsos());
    }
}
