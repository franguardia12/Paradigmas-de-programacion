package org.tp1.view;

import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Estado;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class SubMenuPokemonsView {
    public SubMenuPokemonsView() {
    }
    public void mostrarSubMenu(Jugador jugador, boolean conRetorno) {

        String texto = "";
        String textoEstados = "";
        PokemonView pokemonView = new PokemonView();
        for (int i = 0; i < jugador.getPokemons().size(); i++) {
            Pokemon p = jugador.getPokemons().get(i);
            String linea = String.format("%d ", i);
            linea += pokemonView.formatearPokemon(p);
            texto += linea;
            if (p.estaAfectado()) {
                for (Estado estado : p.getEstados()) {
                    textoEstados += "       " + String.format("*%s*", estado.toString());
                }
                textoEstados += "\n";
            }
            if (textoEstados != "") {
                texto += textoEstados;
                textoEstados = "";
            }
        }
        JugadorView jugadorView = new JugadorView();
        jugadorView.mostrarJugador(jugador);
        texto += "\n";
        System.out.printf(texto);
        if (conRetorno) {
            System.out.printf("\t\t< Ingrese -1 si desea salir del subMenu de Pokemons >\n");
        }
    }

    public static void mostrarMensajeDelSubMenuPokemons(Jugador jugador, Pokemon pokemon) {
        if (esUnRetorno(pokemon)) {
            System.out.printf("> Se ha salido del subMenu de Pokemons <\n");
        } else if (esUnNroNoValido(pokemon) ) {
            System.out.printf("> La opcion de pokemon que desea elegir no esta disponible <\n");
        } else if (pokemon.estaMuerto()) {
            System.out.println("El Pokemon que desea elegir esta Muerto");
        } else {
            System.out.printf(String.format("> %s Ha Seleccionado al Pokemon %s <\n", jugador.getNombre(), pokemon.getNombre()));
        }
    }

    protected static boolean esUnRetorno(Pokemon pokemon) {
        if (pokemon.getNivel() == -1) {
            return true;
        }
        return false;
    }

    protected static boolean esUnNroNoValido(Pokemon pokemon) {
        if (pokemon.getNivel() == 0) {
            return true;
        }
        return false;
    }
}
