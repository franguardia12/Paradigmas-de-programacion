package org.tp1.model.AccionDeBatalla;

import org.tp1.model.Climas.TupleClima;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.Items.Item;
import org.tp1.model.PokemonsDependencias.Afecta;

public class AccionDeBatalla {
    protected Habilidades habilidadUsada = null;
    protected Item itemUsado = null;
    protected Afecta afecta = Afecta.PROPIO;
    protected boolean seUso;
    protected boolean pasaDeTurno;

    public AccionDeBatalla() {
    }

    public void setHabilidadUsada(Habilidades habilidadUsada) {
        this.habilidadUsada = habilidadUsada;
    }

    public void setItemUsado(Item itemUsado) {
        this.itemUsado = itemUsado;
    }

    public void setAfecta(Afecta afecta) {
        this.afecta = afecta;
    }

    public void setPasaDeTurno(boolean pasaDeTurno) {
        this.pasaDeTurno = pasaDeTurno;
    }

    public void setSeUso(boolean seUso) {
        this.seUso = seUso;
    }

    public Habilidades getHabilidadUsada() {
        return habilidadUsada;
    }

    public Afecta getAfecta() {
        return afecta;
    }

    public Item getItemUsado() {
        return itemUsado;
    }

    public boolean isSeUso() {
        return seUso;
    }

    public boolean isPasaDeTurno() {
        return pasaDeTurno;
    }
}