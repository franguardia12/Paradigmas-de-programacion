package org.tp1.model.Habilidades;

import org.tp1.controller.Mensaje;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.Clima;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.Afecta;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class HabilidadModificaClima extends Habilidades {

    protected Clima nuevoClima;

    public HabilidadModificaClima(String nombre, Integer poder, Clima clima) {
        super(nombre, poder);
        this.nuevoClima = clima;
    }

    @Override
    public AccionDeBatalla usar(Pokemon ataca, Pokemon defiende, TablaElementos tabla, TupleClima tupleClima) {

        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();
        if (super.estaUsada()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else if (ataca.estaConfundido() && super.posibilidadDeAutoDanio()) {
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
            tupleClima.setClima(getNuevoClima());
            tupleClima.setTurnos(5);
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
            accionDeBatalla.setAfecta(Afecta.CLIMA);
        }
        accionDeBatalla.setHabilidadUsada(this);
        return accionDeBatalla;
    }

    public Clima getNuevoClima() {
        return nuevoClima;
    }


}
