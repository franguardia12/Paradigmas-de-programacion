package org.tp1.model.Items;

import java.io.Serializable;

public abstract class Item implements consumible, Serializable {
    protected String nombre;
    protected Integer unidades;

    public Item(String nombre, Integer unidades) {
        setNombre(nombre);
        setUnidades(unidades);
    }

    public boolean estaUsado() {
        return unidades == 0;
    }

    public void descartarUnaUnidad() {
        setUnidades(getUnidades()-1);
    }

    public Integer getUnidades() {
        return unidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

}
