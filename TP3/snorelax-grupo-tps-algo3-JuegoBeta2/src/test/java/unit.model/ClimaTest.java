package unit.model;

import org.junit.jupiter.api.Test;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.TipoClima;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.Generadores.GeneradorDeHabilidades;
import org.tp1.model.Generadores.RegistroDeClimas;
import org.tp1.model.Habilidades.HabilidadAtaque;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.Elemento;
import org.tp1.model.PokemonsDependencias.Estadisticas;
import org.tp1.model.PokemonsDependencias.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClimaTest {

    GeneradorDeHabilidades GENERADOR_HABILIDADES = new GeneradorDeHabilidades();
    TablaElementos TABLA = new TablaElementos();
    RegistroDeClimas REGISTRO_CLIMAS = new RegistroDeClimas();

    @Test
    public void TestValidarCambiaDec() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);

        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);

        TupleClima tupleClima = new TupleClima(REGISTRO_CLIMAS.getClimas().get(TipoClima.TORMENTA_DE_RAYOS), 5);
        HabilidadAtaque habilidad = (HabilidadAtaque) GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, TABLA, tupleClima);
        int vidaPosterior = pokemonDefiende.getVida();
        assert (66 > vidaPosterior);
        assertEquals(accion.isSeUso(), true);
        assertEquals(accion.isPasaDeTurno(), true);
    }
}
