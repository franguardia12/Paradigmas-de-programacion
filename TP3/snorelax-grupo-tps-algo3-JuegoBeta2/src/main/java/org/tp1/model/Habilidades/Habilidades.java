package org.tp1.model.Habilidades;

import org.tp1.model.PokemonsDependencias.Elemento;
import org.tp1.model.PokemonsDependencias.Pokemon;
import org.tp1.model.PokemonsDependencias.usable;

import java.io.Serializable;

public abstract class Habilidades implements usable, Serializable {
    private String nombre;
    private Integer usos;
    private Integer poder;

    protected Elemento elemento;

    protected void consumir() {
        this.usos = usos - 1;
    }

    public boolean estaUsada() {
        return getUsos() == 0;
    }

    public boolean validarParalizado(Pokemon pokemon) {
        if (pokemon.estaParalizado() && Math.random() < 0.5) {
            return false;
        }
        return true;
    }

    public Object getElemento() {
        return this.elemento;
    }

    public Integer getPoder() {
        return poder;
    }

    public String getNombre(){return nombre;}

    public void setUsos(int usos) {
        this.usos = usos;
    }


    protected Habilidades(String nombre, Integer poder) {
        this.nombre = nombre;
        this.poder = poder;
        this.usos = 4;
    }



    public Integer getUsos() {
        return usos;
    }

    public boolean posibilidadDeAutoDanio() {
        double esPosible = Math.random();
        if (esPosible <= 1/3) {
            return true;
        }
        return false;
    }

}
