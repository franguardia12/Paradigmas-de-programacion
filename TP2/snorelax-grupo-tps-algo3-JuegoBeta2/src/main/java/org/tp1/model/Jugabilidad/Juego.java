package org.tp1.model.Jugabilidad;

import org.tp1.JuegoController;
import org.tp1.view.PantallaView;

import java.util.ArrayList;

public class Juego {

    protected JuegoController juegoController;

    public Juego(JuegoController juegoController) {
        setJuegoController(juegoController);
    }

    public void inicializarJuego() {
        getJuegoController().jugar();
    }

    public void setJuegoController(JuegoController juegoController) {
        if (juegoController == null) {
            this.juegoController = new JuegoController();
        } else {
            this.juegoController = juegoController;
        }

    }

    public JuegoController getJuegoController() {
        return juegoController;
    }

}
