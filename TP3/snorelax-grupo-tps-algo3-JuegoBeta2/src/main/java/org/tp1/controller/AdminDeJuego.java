package org.tp1.controller;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.Clima;
import org.tp1.model.Climas.TipoClima;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.Generadores.GeneradorDeItems;
import org.tp1.model.Generadores.GeneradorDePokemones;
import org.tp1.model.Generadores.RegistroDeClimas;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.Items.Item;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.Estadistica;
import org.tp1.model.PokemonsDependencias.Pokemon;
import org.tp1.view.AccionDeTurnoView;

import java.util.ArrayList;
import java.util.Random;

public class AdminDeJuego {
    private int turno = 0;
    private Jugador jugador1, jugador2;
    protected AdministradorDeEstados administradorDeEstados;
    protected TablaElementos tablaElementos;
    protected RegistroDeClimas registroClimas;

    protected TupleClima climaActual;

    public AdminDeJuego(ArrayList<Jugador> jugadores) {
        prepararJugadores(jugadores);
        setJugador1(jugadores.get(0));
        setJugador2(jugadores.get(1));
        setAdministradorDeEstados(new AdministradorDeEstados());
        crearYsetearTablaElementos();
        setRegistroClimas(new RegistroDeClimas());
        setClimaActual(obtenerClimaInicial());
    }

    public void setJugador2(Jugador jugador) {
        this.jugador2 = jugador;
    }

    public void setJugador1(Jugador jugador) {
        this.jugador1 = jugador;
    }

    public Jugador seleccionarJugadorPorPokemonMasVeloz() {
        int velocidadActual = 0;
        Jugador jugadorInicia = getJugador1();
        for (Jugador jugador : getJugadores()) {
            for (Pokemon pokemon : jugador.getPokemons()) {
                if (pokemon.getVelocidad() > velocidadActual) {
                    jugadorInicia = jugador;
                    velocidadActual = pokemon.getVelocidad();
                }
            }
        }
        return jugadorInicia;
    }

    public ArrayList<Jugador> getJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList();
        jugadores.add(getJugador1());
        jugadores.add(getJugador2());
        return jugadores;
    }

    public void crearYsetearTablaElementos() {
        this.tablaElementos = new TablaElementos();
    }

    public AccionDeBatalla usarHabilidad(Pokemon ataca, Pokemon defiende, Habilidades habilidad) {

        if (ataca.estaMuerto()) {
            AccionDeBatalla accionDeBatalla = null;
            return accionDeBatalla;
        }
        return ataca.usarHabilidad(defiende, habilidad, getTablaElementos(), getClimaActual());
    }

    public TablaElementos getTablaElementos() {
        return tablaElementos;
    }

    public AccionDeBatalla usarItem(Pokemon pokemon, Item item) {
        return item.usar(pokemon);
    }

    protected void prepararJugadores(ArrayList<Jugador> jugadores) {
        GeneradorDePokemones generadorDePokemones = new GeneradorDePokemones();
        generadorDePokemones.generarPokemonesParaJugadores(jugadores);
        GeneradorDeItems generadorDeItems = new GeneradorDeItems();
        generadorDeItems.generarItemsParaJugadores(jugadores);
    }


    public boolean validarJugadorPierde(Jugador jugador) {
        boolean pierde = true;
        for (Pokemon pokemon : jugador.getPokemons()) {
            if (!pokemon.estaMuerto()) {
                pierde = false;
            }
        }
        return pierde;
    }

    public boolean siguienteTurno() {

        for (Jugador jugador : getJugadores()) {
            if (validarJugadorPierde(jugador)) {
                AccionDeTurnoView.mostrarAccionDeTurno(String.format("EL JUGADOR %s ES EL GANADOR!!!!. %s PIERDE.\n", obtenerRivalActual(jugador).getNombre(), jugador.getNombre()));
                return false;
            }
            if (jugador.getPokemonActual().estaMuerto()) {
                AccionDeTurnoView.mostrarAccionDeTurno(String.format("\n%s! Debe cambiar de pokemon, ya que %s se encuentra Muerto\n< Porfavor ingrese un nuevo numero de opcion valido >\n", jugador.getNombre(), jugador.getPokemonActual().getNombre()));
                SubMenuPokemons nuevoSubMenuPokemons = new SubMenuPokemons(jugador, true, false, true);
            }
            if (validarClimaDaniaPokemons(getClimaActual().getClima(), jugador.getPokemonActual())) {
                jugador.getPokemonActual().modificarEstadistica(Estadistica.VIDA, 0.97);
                AccionDeTurnoView.mostrarAccionDeTurno(String.format("El Clima %s ha dañado al Pokemon %s", getClimaActual().getClima().getTipoClima().toString(), jugador.getPokemonActual().getNombre()));
            }
        }

        setClimaActual(getClimaActual().restarUnTurno());
        String texto = getAdministradorDeEstados().aplicarTurno(getJugadores());
        if (!texto.equals("")) {
            AccionDeTurnoView.mostrarAccionDeTurno(texto);
        }
        setTurno(getTurno()+1);
        return true;
    }

    public Jugador obtenerRivalActual(Jugador jugadorActual) {
        if (jugadorActual.equals(getJugador1())){
            return getJugador2();
        }
        return getJugador1();
    }

    public TupleClima obtenerClimaInicial() {
        double random = Math.random();
        if (random > 1/3) {
            Clima clima = getRegistroClimas().getClimas().get(TipoClima.SIN_CLIMA);
            return new TupleClima(clima, 0);
        }
        Random randomizado = new Random();
        int i = randomizado.nextInt(getRegistroClimas().getClimas().size());
        TipoClima nuevoTipoClima = TipoClima.values()[i];
        Clima clima = getRegistroClimas().getClimas().get(nuevoTipoClima);
        return new TupleClima(clima, 5);
    }

    public boolean validarClimaDaniaPokemons(Clima clima, Pokemon pokemon) {
        return (clima.esClimaDanino() && !clima.getFavoreceElementos().contains(pokemon.getElemento()));
    }

    public RegistroDeClimas getRegistroClimas() {
        return registroClimas;
    }


    public TupleClima getClimaActual() {
        return climaActual;
    }

    public AdministradorDeEstados getAdministradorDeEstados() {
        return administradorDeEstados;
    }

    public int getTurno() {
        return turno;
    }

    public Jugador getJugador1() {
        return this.jugador1;
    }

    public Jugador getJugador2() {
        return this.jugador2;
    }

    public void setClimaActual(TupleClima climaActual) {
        this.climaActual = climaActual;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setRegistroClimas(RegistroDeClimas registroClimas) {
        this.registroClimas = registroClimas;
    }

    public void setAdministradorDeEstados(AdministradorDeEstados administradorDeEstados) {
        this.administradorDeEstados = administradorDeEstados;
    }




}

