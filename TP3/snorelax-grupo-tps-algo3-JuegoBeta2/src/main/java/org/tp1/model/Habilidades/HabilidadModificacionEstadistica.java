package org.tp1.model.Habilidades;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.Afecta;
import org.tp1.model.PokemonsDependencias.Estadistica;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class HabilidadModificacionEstadistica extends Habilidades {
    protected Afecta afectado;
    protected Estadistica estadistica;
    protected double porcentaje;
    public HabilidadModificacionEstadistica(String nombre, Estadistica estadistica, Afecta afectado, Double valor) {
        super(nombre,  valor.intValue());
        this.afectado = afectado;
        this.estadistica = estadistica;
        this.porcentaje = valor;
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
            Estadistica estadistica = getEstadistica();
            double porcentaje = getPorcentaje();
            if (getAfectado() == Afecta.PROPIO) {
                ataca.modificarEstadistica(estadistica, porcentaje);
            } else {
                defiende.modificarEstadistica(estadistica, porcentaje);
                accionDeBatalla.setAfecta(Afecta.RIVAL);
            }
            super.consumir();
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setHabilidadUsada(this);
        return accionDeBatalla;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public Afecta getAfectado() {
        return afectado;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }
}