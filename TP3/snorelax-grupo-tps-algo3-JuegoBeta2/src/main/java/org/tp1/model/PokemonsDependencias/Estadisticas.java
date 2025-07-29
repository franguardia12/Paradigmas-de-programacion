package org.tp1.model.PokemonsDependencias;

import java.io.Serializable;

public class Estadisticas implements Modificable, Serializable {
    protected Integer vida, ataque, defensa;

    public Estadisticas(int vida, int ataque, int defensa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    @Override
    public void modificar(Estadistica estadistica, double porcentaje) {
        if (estadistica == Estadistica.ATAQUE) {
            setAtaque(nuevoValor(getAtaque(), porcentaje));
        } else if (estadistica == Estadistica.DEFENSA) {
            setDefensa(nuevoValor(getDefensa(), porcentaje));
        } else {
            setVida(nuevoValor(getVida(), porcentaje));
        }
    }

    public void setearACero() {
        setAtaque(0);
        setDefensa(0);
        setVida(0);
    }

    private int nuevoValor(int nro, double porcentaje) {
        double nuevoNro = nro * porcentaje;
        return (int) nuevoNro;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getDefensa() {
        return defensa;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public Integer getVida() {
        return vida;
    }
}
