package org.tp1.model.Climas;

import java.util.ArrayList;

public class TupleClima {
    protected Clima clima;
    protected int turnos;

    public TupleClima(Clima clima, int turno) {
        this.clima = clima;
        this.turnos = turno;
    }


    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public int getTurnos() {
        return turnos;
    }

    public Clima getClima() {
        return clima;
    }

    public TupleClima restarUnTurno() {
        if (getTurnos() == 0) {
            return this;
        }
        if (getTurnos()-1 == 0) {
            this.clima = new Clima(TipoClima.SIN_CLIMA, new ArrayList<>());
            setTurnos(0);
            return this;
        }
        setTurnos(getTurnos()-1);
        return this;
    }
}