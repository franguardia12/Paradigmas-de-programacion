package org.tp1.model.PokemonsDependencias;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.ManipularCsv.TablaElementos;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {
    public static int vidaMaxima = 200;

    public String nombre, info;
    public Integer nivel, velocidad;
    public ArrayList<Estado> estados;
    public Elemento elemento;

    public String stringEstadisticas;
    protected Estadisticas estadisticas;
    public ArrayList<Habilidades> habilidades;


    public Pokemon(String nombre, String info, Integer nivel, Integer velocidad, Elemento elemento, Estadisticas estadisticas) {
        this.nombre = nombre;
        this.info = info;
        this.nivel = nivel;
        this.velocidad = velocidad;
        this.estados = new ArrayList<>();
        getEstados().add(Estado.NORMAL);
        this.elemento = elemento;
        String stringEstadisticas = String.format("%d %d %d", estadisticas.getVida(), estadisticas.getAtaque(), estadisticas.getDefensa());
        setStringEstadisticas(stringEstadisticas);
        setEstadisticas(estadisticas);
    }


    public void restarVida(int danio) {
        getEstadisticas().setVida(getEstadisticas().getVida() - danio);
        if (estaMuerto()) {
            getEstadisticas().setearACero();
            setEstado(Estado.MUERTO);
        }
    }

    public void modificarEstadistica(Estadistica estadistica, double porcentaje) {
        getEstadisticas().modificar(estadistica, porcentaje);
    }

    public void aumentarVida(int vidaExtra) {
        int nuevaVida = getVida() + vidaExtra;
        if (nuevaVida >= getVidaMaxima()) {
            getEstadisticas().setVida(getVidaMaxima());
        } else {
            getEstadisticas().setVida(nuevaVida);
        }
    }
    public AccionDeBatalla usarHabilidad(Pokemon pokemon, Habilidades habilidad, TablaElementos tabla, TupleClima climaActual) {
        return habilidad.usar(this, pokemon, tabla, climaActual);
    }

    public ArrayList<Habilidades> verHabilidadesDisponibles() {
        ArrayList<Habilidades> disponibles = new ArrayList<>();
        for (Habilidades habilidad : getHabilidades()) {
            if (!habilidad.estaUsada()) {
                disponibles.add(habilidad);
            }
        }
        return disponibles;
    }

    public boolean estaMuerto() {
        return getEstadisticas().getVida() <= 0;
    }

    public boolean estaParalizado() {
        return getEstados().contains(Estado.PARALIZADO);
    }

    public boolean estaAfectado() {
        return !estaNormal();
    }

    public boolean estaConfundido() {
        return getEstados().contains(Estado.CONFUSO);
    }

    public boolean estaNormal() {
        return getEstados().contains(Estado.NORMAL);
    }

    public boolean estaDormido() {
        return getEstados().contains(Estado.DORMIDO);
    }

    public void revivirPokemon() {

        String[] data = getStringEstadisticas().split(" ");
        int vida = Integer.parseInt(data[0]);
        int ataque = Integer.parseInt(data[1]);
        int defensa = Integer.parseInt(data[2]);

        Estadisticas estadisticas = new Estadisticas(vida, ataque, defensa);
        setEstadisticas(estadisticas);
        setEstado(Estado.NORMAL);
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    public Habilidades getHabilidad(int i) {
        return getHabilidades().get(i);
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public void setStringEstadisticas(String stringEstadisticas) {
        this.stringEstadisticas = stringEstadisticas;
    }

    public void setHabilidades(ArrayList<Habilidades> habilidades) {
        this.habilidades = habilidades;
    }

    public Estadisticas getEstadisticas() {
        return this.estadisticas;
    }

    public String getNombre() {
        return nombre;
    }

    public static int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVida() {
        return getEstadisticas().getVida();
    }

    public int getAtaque() {
        return getEstadisticas().getAtaque();
    }

    public int getDefensa() {
        return getEstadisticas().getDefensa();
    }

    public ArrayList<Estado> getEstados() {
        return this.estados;
    }

    public String getEstadosAString() {
        String estados = String.join(" ", getEstados().stream()
                .map(Enum::toString)
                .toArray(String[]::new));
        return estados;
    }

    public void sacarEstado(Estado viejoEstado) {
        getEstados().remove(viejoEstado);
        if (getEstados().size() == 0) {
            setEstado(Estado.NORMAL);
        }
    }

    public void setEstado(Estado nuevoEstado) {
        if (nuevoEstado == Estado.MUERTO || nuevoEstado == Estado.NORMAL) {
            this.estados = new ArrayList<>();
            this.estados.add(nuevoEstado);
            return;
        }
        if (getEstados().contains(Estado.NORMAL)) {
            this.estados.remove(Estado.NORMAL);
        }
        if (!getEstados().contains(nuevoEstado)) {
            this.estados.add(nuevoEstado);
        }
    }


    public Integer getNivel() {
        return nivel;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public String getStringEstadisticas() {
        return stringEstadisticas;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public ArrayList<Habilidades> getHabilidades() {
        return habilidades;
    }
}
