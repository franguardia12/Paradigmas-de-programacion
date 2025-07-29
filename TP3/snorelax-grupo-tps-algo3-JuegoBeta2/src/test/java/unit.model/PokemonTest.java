package unit.model;

import org.junit.jupiter.api.Test;
import org.tp1.model.PokemonsDependencias.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    @Test
    public void TestValidarSeRestaVidaDePokemonQueRecibeDanio() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.restarVida(10);
        int vida = pokemon.getVida();
        Boolean resultado = pokemon.estaMuerto();

        assertEquals(vida, 80);
        assertEquals(resultado, false);
    }

    @Test
    public void TestValidarSeRestaVidaDePokemonYMuere() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.restarVida(90);
        int vida = pokemon.getVida();
        Boolean resultado = pokemon.estaMuerto();

        assertEquals(vida, 0);
        assertEquals(resultado, true);
    }

    @Test
    public void TestSeModificaEstadisticaDePokemon() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.modificarEstadistica(Estadistica.VIDA, 0.1);
        pokemon.modificarEstadistica(Estadistica.ATAQUE, 0.1);
        int vida = pokemon.getVida();
        int ataque = pokemon.getAtaque();

        assertEquals(vida, 9);
        assertEquals(ataque, 1);
    }

    @Test
    public void TestSeAumentaVidaDePokemonConVidaMaxima() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(200,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.aumentarVida(10);
        int vida = pokemon.getVida();

        assertEquals(vida, 200);
    }

    @Test
    public void TestValidarAumentaVidaDePokemonQueNoTieneVidaMaxima() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.aumentarVida(10);
        int vida = pokemon.getVida();

        assertEquals(vida, 100);
    }

    @Test
    public void TestValidarAumentaVidaTieneTopeEnVidaMaxima() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.aumentarVida(10);
        int vida = pokemon.getVida();

        assertEquals(vida, 200);
    }

    @Test
    public void TestValidarSeSeteaUnNuevoEstadoCorrectamente() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.setEstado(Estado.DORMIDO);
        Boolean resultado = pokemon.estaDormido();

        assertEquals(resultado, true);
    }

    @Test
    public void TestValidarSetearEstadoNormalEliminaLosOtros() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.setEstado(Estado.DORMIDO);
        pokemon.setEstado(Estado.NORMAL);
        Boolean resultado1 = pokemon.estaNormal();
        Boolean resultado2 = pokemon.estaDormido();

        assertEquals(resultado1, true);
        assertEquals(resultado2, false);
    }

    @Test
    public void TestValidarSetearEstadoMuertoEliminaLosOtros() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.setEstado(Estado.DORMIDO);
        assertEquals(true, pokemon.estaDormido());

        pokemon.restarVida(200);
        assertEquals(true, pokemon.estaMuerto());
    }

    @Test
    public void TestValidarPokemonTieneMasDeUnEstadoAlMismoTiempo() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.setEstado(Estado.DORMIDO);
        pokemon.setEstado(Estado.CONFUSO);
        Boolean resultado1 = pokemon.estaDormido();
        Boolean resultado2 = pokemon.estaConfundido();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
    }

    @Test
    public void TestValidarSeSacaElEstadoDeUnPokemonYVuelveANormal() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.setEstado(Estado.ENVENENADO);
        pokemon.sacarEstado(Estado.ENVENENADO);
        Boolean resultado1 = pokemon.estaAfectado();
        Boolean resultado2 = pokemon.estaNormal();

        assertEquals(resultado1, false);
        assertEquals(resultado2, true);
    }

    @Test
    public void TestValidarSeSacaElEstadoDeUnPokemonPeroNoVuelveANormal() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(195,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.setEstado(Estado.PARALIZADO);
        pokemon.setEstado(Estado.CONFUSO);
        pokemon.sacarEstado(Estado.CONFUSO);
        Boolean resultado1 = pokemon.estaNormal();
        Boolean resultado2 = pokemon.estaParalizado();

        assertEquals(resultado1, false);
        assertEquals(resultado2, true);
    }

    @Test
    public void TestValidarSeReviveAPokemonMuertoSatisfactoriamente() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);

        pokemon.restarVida(90);
        pokemon.revivirPokemon();
        int vida = pokemon.getVida();
        Boolean resultado = pokemon.estaNormal();

        assertEquals(vida, 90);
        assertEquals(resultado, true);
    }
}
