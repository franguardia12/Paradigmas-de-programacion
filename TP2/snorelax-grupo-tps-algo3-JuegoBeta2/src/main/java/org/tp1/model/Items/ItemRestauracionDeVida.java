package org.tp1.model.Items;

import org.tp1.controller.Mensaje;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class ItemRestauracionDeVida extends Item {
    private Integer cantidadVida;

    public ItemRestauracionDeVida(String nombre, Integer unidades, Integer cantidad) {
        super(nombre, unidades);
        setCantidadVida(cantidad);
    }

    @Override
    public AccionDeBatalla usar(Pokemon pokemon) {

        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();
        if (pokemon.estaMuerto()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else if (pokemon.getVida() == pokemon.getVidaMaxima()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else {
            super.descartarUnaUnidad();
            pokemon.aumentarVida(getCantidadVida());
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setItemUsado(this);
        return accionDeBatalla;
    }

    public Integer getCantidadVida() {
        return cantidadVida;
    }

    public void setCantidadVida(Integer cantidadVida) {
        this.cantidadVida = cantidadVida;
    }
}
