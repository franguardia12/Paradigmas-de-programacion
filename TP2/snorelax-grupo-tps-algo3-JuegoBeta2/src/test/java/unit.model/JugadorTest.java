package unit.model;

import org.junit.jupiter.api.Test;
import org.tp1.model.Generadores.GeneradorDePokemones;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JugadorTest {

    GeneradorDePokemones GENERADOR_POKEMONS = new GeneradorDePokemones();
    @Test
    public void TestValidarSeIntercambiaPokemon() {
        Jugador jugador = new Jugador("Cuti");
        assertEquals(null, jugador.getPokemonActual());

        Pokemon pikachu = GENERADOR_POKEMONS.getPokemon("Pikachu");
        jugador.setPokemonActual(pikachu);

        assertEquals(pikachu, jugador.getPokemonActual());

        Pokemon bulbasaur = GENERADOR_POKEMONS.getPokemon("Bulbasaur");
        jugador.setPokemonActual(bulbasaur);

        assertEquals(bulbasaur, jugador.getPokemonActual());
    }
}
