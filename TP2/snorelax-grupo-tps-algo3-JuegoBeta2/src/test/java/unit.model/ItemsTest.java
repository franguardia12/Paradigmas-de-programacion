package unit.model;

import org.junit.Test;
import org.tp1.model.Items.*;
import org.tp1.model.PokemonsDependencias.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemsTest {

    @Test
    public void TestValidarItemModificaEstadisticaDePokemon() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemModificacionDeEstadistica("Pocion", 4, Estadistica.ATAQUE, 1.2);

        Boolean resultado = item.usar(pokemon).isSeUso();
        int ataque = pokemon.getEstadisticas().getAtaque();

        assertEquals(resultado, true);
        assertEquals(ataque, 12);
    }

    @Test
    public void TestValidarItemCuraEstadoDePokemon() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        pokemon.setEstado(Estado.DORMIDO);
        Item item = new ItemCuraTodo(4);

        Boolean resultado = item.usar(pokemon).isSeUso();
        //Estado estado = pokemon.getEstado();
        assertEquals(resultado, true);
        assertEquals(pokemon.estaNormal(), true);
        // assertEquals(estado, Estado.NORMAL);
    }

    @Test
    public void TestValidarItemNoCuraEstadoDePokemonNormal() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        pokemon.setEstado(Estado.NORMAL);
        Item item = new ItemCuraTodo(4);

        Boolean resultado = item.usar(pokemon).isSeUso();
        // Estado estado = pokemon.getEstado();

        assertEquals(resultado, false);
        assertEquals(pokemon.estaNormal(), true);
        // assertEquals(estado, Estado.NORMAL);
    }

    @Test
    public void TestValidarItemNoCuraEstadoDePokemonMuerto() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        pokemon.setEstado(Estado.MUERTO);
        Item item = new ItemCuraTodo(4);

        Boolean resultado = item.usar(pokemon).isSeUso();
        //Estado estado = pokemon.getEstado();

        assertEquals(resultado, true);
        assertEquals(pokemon.estaNormal(), true);
        //assertEquals(estado, Estado.MUERTO);
    }

    @Test
    public void TestValidarItemEstaUsado() {
        Item item = new ItemCuraTodo(0);

        Boolean resultado = item.estaUsado();

        assertEquals(resultado, true);
    }

    @Test
    public void TestValidarItemNoEstaUsado() {
        Item item = new ItemCuraTodo(5);

        Boolean resultado = item.estaUsado();

        assertEquals(resultado, false);
    }

    @Test
    public void TestValidarSeDescartaUnaUnidadDeItem() {
        Item item = new ItemCuraTodo(5);

        item.descartarUnaUnidad();

        assertEquals(4, item.getUnidades());
    }

    @Test
    public void TestValidarNoSeCuraConItemPokemonConTodaLaVida() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(200,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemRestauracionDeVida("Pocion", 4, 10);

        Boolean resultado = item.usar(pokemon).isSeUso();
        int vida = pokemon.getVida();

        assertEquals(resultado, false);
        assertEquals(vida, 200);
    }

    @Test
    public void TestValidarSeCuraConItemPokemonQueLeFaltaVida() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemRestauracionDeVida("Pocion", 4, 10);

        Boolean resultado = item.usar(pokemon).isSeUso();
        int vida = pokemon.getVida();

        assertEquals(resultado, true);
        assertEquals(vida, 100);
    }

    @Test
    public void TestValidarNoSeCuraConItemPokemonMuerto() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(0,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemRestauracionDeVida("Pocion", 4, 10);

        Boolean resultado = item.usar(pokemon).isSeUso();
        int vida = pokemon.getVida();

        assertEquals(resultado, false);
        assertEquals(vida, 0);
    }

    @Test
    public void TestValidarNoSeModificanEstadisticasConItemDePokemonMuerto() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(0,45,45);
        Pokemon pokemon= new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemModificacionDeEstadistica("Pocion", 4, Estadistica.ATAQUE, 1.5);

        Boolean resultado = item.usar(pokemon).isSeUso();
        int ataque = pokemon.getAtaque();

        assertEquals(resultado, false);
        assertEquals(ataque, 45);
    }

    @Test
    public void TestValidarSeReviveConItemAPokemonMuerto() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemRevivir("Pocion", 4);

        pokemon.restarVida(100);
        Boolean resultado = item.usar(pokemon).isSeUso();
        int vida = pokemon.getVida();

        assertEquals(resultado, true);
        assertEquals(vida, 90);

    }

    @Test
    public void TestValidarNoSeReviveConItemAPokemonVivo() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new ItemRevivir("Pocion", 4);

        Boolean resultado = item.usar(pokemon).isSeUso();

        assertEquals(resultado, false);
    }

    @Test
    public void TestValidarPocionMolestaAlumnosNoHaceNadaEnPokemonMuerto() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new pocionMolestaAlumnos( 4);

        pokemon.restarVida(90);
        Boolean resultado = item.usar(pokemon).isSeUso();

        assertEquals(resultado, false);
    }

    @Test
    public void TestValidarPocionMolestaAlumnosCuraAPokemonVivo() {
        String nombre = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,10,45);
        Pokemon pokemon = new Pokemon(nombre, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 12, 90, Elemento.ELECTRICO, estadisticas);
        Item item = new pocionMolestaAlumnos(4);

        Boolean resultado = item.usar(pokemon).isSeUso();
        int vida = pokemon.getVida();

        assertEquals(vida, 117);
        assertEquals(resultado, true);
    }

}
