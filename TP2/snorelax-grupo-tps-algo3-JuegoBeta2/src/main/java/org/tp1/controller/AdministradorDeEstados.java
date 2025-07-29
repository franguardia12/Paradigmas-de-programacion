package org.tp1.controller;

import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Estadistica ;
import org.tp1.model.PokemonsDependencias.Estado ;
import org.tp1.model.PokemonsDependencias.Pokemon ;

import java.util.*;

public class AdministradorDeEstados {

    protected Map<Pokemon, Integer> dormidos;

    protected Map<Pokemon, Integer> confundidos;

    public AdministradorDeEstados() {
        setDormidos(new HashMap<>());
        setConfundidos(new HashMap<>());
    }

    protected String aplicarTurno(ArrayList<Jugador> jugadores) {
        String texto = "";
        for (Jugador jugador : jugadores) {
            for (Pokemon pokemon : jugador.getPokemons()) {
                if (!pokemon.estaMuerto() && !pokemon.estaNormal()) {
                    for (int i = 0; i < pokemon.getEstados().size(); i++) {
                        Estado estadoAct = pokemon.getEstados().get(i);
                            if (estadoAct == Estado.ENVENENADO) {
                                pokemon.modificarEstadistica(Estadistica.VIDA, 0.95);
                                if (pokemon.estaMuerto()) {
                                    pokemon.getEstadisticas().setearACero();
                                    pokemon.setEstado(Estado.MUERTO);
                                }
                                texto += String.format("\nSe ha disminuido la vida del pokemon %s debido al Envenenamiento.", pokemon.getNombre());
                            } else if (estadoAct == Estado.DORMIDO) {
                                if (!getDormidos().containsKey(pokemon)) {
                                    getDormidos().put(pokemon, 0);
                                } else {
                                    int turnosDormido = getDormidos().get(pokemon);
                                    turnosDormido++;
                                    if (seDespierta(turnosDormido)) {
                                        pokemon.sacarEstado(Estado.DORMIDO);
                                        texto += String.format("\nEl pokemon %s se desperto, despues de estar %d turnos dormido", pokemon.getNombre(), turnosDormido);
                                    } else {
                                        getDormidos().put(pokemon, turnosDormido);
                                    }
                                }
                            } else if (estadoAct == Estado.CONFUSO) {
                                if (!getConfundidos().containsKey(pokemon)) {
                                    getConfundidos().put(pokemon, 0);
                                    continue;
                                }
                                int turnosConfundido = getConfundidos().get(pokemon);
                                getConfundidos().put(pokemon, turnosConfundido+1);
                                if (getConfundidos().get(pokemon) == 3) {
                                    texto += String.format("\nEl pokemon %s se ha despertado de la confunsion", pokemon.getNombre());
                                    }
                                    getConfundidos().remove(pokemon);
                                    pokemon.sacarEstado(Estado.CONFUSO);
                                }

                            }
                    }
                }
            }
        if (!texto.equals("")) {
            texto += "\n";
        }
        return texto;
    }

    public Map<Pokemon, Integer> getDormidos() {
        return dormidos;
    }

    public Map<Pokemon, Integer> getConfundidos() {
        return confundidos;
    }

    protected boolean seDespierta(int turno) {
        double nroRandom = Math.random();
        if (0.25 + turno * 0.25 >= nroRandom) {
            return true;
        }
        return false;
    }

    public void setConfundidos(Map<Pokemon, Integer> confundidos) {
        this.confundidos = confundidos;
    }

    public void setDormidos(Map<Pokemon, Integer> dormidos) {
        this.dormidos = dormidos;
    }
}
