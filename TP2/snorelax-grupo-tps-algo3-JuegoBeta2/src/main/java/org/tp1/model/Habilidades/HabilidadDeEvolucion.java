package org.tp1.model.Habilidades;

import org.tp1.controller.Mensaje;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.Afecta;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class HabilidadDeEvolucion extends Habilidades {

    protected double porcentajeNivel;
    protected Afecta afectado;

    public HabilidadDeEvolucion(String nombre, double porcentajeNivel, Afecta afectado) {
        super(nombre, 0);
        this.afectado = afectado;
        this.porcentajeNivel = porcentajeNivel;
    }

    @Override
    public AccionDeBatalla usar(Pokemon ataca, Pokemon defiende, TablaElementos tabla, TupleClima tupleClima) {
        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();
        if (super.estaUsada()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        }  else if (ataca.estaConfundido() && super.posibilidadDeAutoDanio()) {
            super.consumir();
            int autoDanio = (int) (ataca.getVida() * 0.15);
            if (tupleClima.getClima().climaFavorece(ataca)) {
                autoDanio *= 1.1;
            }
            ataca.restarVida(autoDanio);
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        } else if (!super.validarParalizado(ataca)) {
            super.consumir();
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        } else if (ataca.estaDormido()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(true);
        } else {
            super.consumir();
            if (getAfectado() == Afecta.PROPIO) {
                ataca.setNivel((int) (ataca.getNivel() * getPorcentajeNivel()));
            } else {
                defiende.setNivel((int) (defiende.getNivel() * getPorcentajeNivel()));
                accionDeBatalla.setAfecta(Afecta.RIVAL);
            }
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setHabilidadUsada(this);
        return accionDeBatalla;
    }

    public double getPorcentajeNivel() {
        return porcentajeNivel;
    }

    public Afecta getAfectado() {
        return afectado;
    }
}

