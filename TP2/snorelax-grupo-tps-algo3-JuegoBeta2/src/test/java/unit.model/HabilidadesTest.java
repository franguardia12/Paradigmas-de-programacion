package unit.model;

import org.junit.jupiter.api.Test;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.Clima;
import org.tp1.model.Climas.TipoClima;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.Generadores.GeneradorDeHabilidades;
import org.tp1.model.Habilidades.HabilidadModificaClima;
import org.tp1.model.Habilidades.Habilidades;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.*;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabilidadesTest {
    TablaElementos TABLA_ELEMENTOS = new TablaElementos();
    GeneradorDeHabilidades GENERADOR_HABILIDADES = new GeneradorDeHabilidades();

    @Test
    public void TestValidarHabilidadSinUsosNoPuedeUsarse() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.SIN_CLIMA, new ArrayList<>()), 0);

        habilidad.setUsos(0);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, false);
        assertEquals(resultado2, false);
    }

    @Test
    public void TestValidarPokemonDormidoNoPuedeAtacarPeroGastaTurno() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        GeneradorDeHabilidades generadorDeHabilidades = new GeneradorDeHabilidades();
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.SIN_CLIMA, new ArrayList<>()), 0);

        pokemonAtaca.setEstado(Estado.DORMIDO);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, false);
        assertEquals(resultado2, true);
    }

    @Test
    public void TestValidarPokemonParalizadoPuedeAtacar() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        GeneradorDeHabilidades generadorDeHabilidades = new GeneradorDeHabilidades();
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.SIN_CLIMA, new ArrayList<>()), 0);
        Habilidades habilidades = mock(Habilidades.class);
        when(habilidades.validarParalizado(pokemonAtaca)).thenReturn(true);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
    }

    @Test
    public void TestValidarPokemonParalizadoNoPuedeAtacar() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.SIN_CLIMA, new ArrayList<>()), 0);
        Habilidades habilidades = mock(Habilidades.class);
        when(habilidades.validarParalizado(pokemonAtaca)).thenReturn(false);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, false);
        assertEquals(resultado2, true);
    }

    @Test
    public void TestValidarPokemonConfusoSeHaceDanioASiMismo() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.SIN_CLIMA, new ArrayList<>()), 0);
        Habilidades habilidades = mock(Habilidades.class);
        when(habilidades.posibilidadDeAutoDanio()).thenReturn(true);

        pokemonAtaca.setEstado(Estado.CONFUSO);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        int vida = pokemonAtaca.getVida();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(vida, 76.5);
    }

    @Test
    public void TestValidarPokemonConfusoSeHaceDanioASiMismoFavorecidoPorClima() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);
        Habilidades habilidades = mock(Habilidades.class);
        when(habilidades.posibilidadDeAutoDanio()).thenReturn(true);

        pokemonAtaca.setEstado(Estado.CONFUSO);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        int vida = pokemonAtaca.getVida();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(vida, 75.15);
    }

    @Test
    public void TestValidarPokemonConfusoNoSeHaceDanioASiMismo() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);
        Habilidades habilidades = mock(Habilidades.class);
        when(habilidades.posibilidadDeAutoDanio()).thenReturn(false);

        pokemonAtaca.setEstado(Estado.CONFUSO);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, false);
        assertEquals(resultado2, false);
    }

    @Test
    public void TestValidarPokemonHaceDanioSatisfactoriamente() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        int vidaAnterior = pokemonDefiende.getVida();
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Rayo", "ATAQUE", "ELECTRICO","15");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        int vidaPosterior = pokemonDefiende.getVida();
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assert (vidaAnterior > vidaPosterior);
    }

    @Test
    public void TestValidarSeModificaEstadisticaDePokemonPropioConHabilidad() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("FuegoCoraje", "DEFENSA", "PROPIO","1.5");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        int defensa = pokemonAtaca.getDefensa();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(defensa, 67.5);
    }

    @Test
    public void TestValidarSeModificaEstadisticaDePokemonEnemigoConHabilidad() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Desarmar oponente", "DEFENSA", "RIVAL", "0.85");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        int defensa = pokemonAtaca.getDefensa();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(defensa, 28.05);
    }

    @Test
    public void TestValidarNoSePuedeAplicarEstadoAUnPokemonQueYaLoTiene() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Descanso", "ESTADO", "DORMIDO", "0");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        pokemonDefiende.getEstados().add(Estado.DORMIDO);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();

        assertEquals(resultado1, false);
        assertEquals(resultado2, false);
    }

    @Test
    public void TestValidarSeModificaElEstadoRivalSatisfactoriamente() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Descanso", "ESTADO", "DORMIDO", "0");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        Estado estado = pokemonDefiende.getEstados().get(0);

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(estado, Estado.DORMIDO);
    }
    
    @Test
    public void TestValidarSeAgregaUnNuevoEstadoAUnPokemonYaAfectado() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Descanso", "ESTADO", "DORMIDO", "0");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);
        
        pokemonDefiende.getEstados().add(Estado.PARALIZADO);
        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        Estado estado = pokemonDefiende.getEstados().get(1);

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(estado, Estado.DORMIDO);
    }
    
    @Test
    public void TestValidarHabilidadModificaElClimaActual() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidadGenerica = GENERADOR_HABILIDADES.crearHabilidad("Invocar Sol", "CLIMA", "SOLEADO", "0");
        HabilidadModificaClima habilidad = (HabilidadModificaClima) habilidadGenerica;
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        Clima nuevoClima = tupleClima.getClima();
        int turnos = tupleClima.getTurnos();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(turnos, 5);
        assertEquals(nuevoClima, habilidad.getNuevoClima());
    }

    @Test
    public void TestValidarHabilidadEvolucionaAPokemonPropio() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Evolucionar", "EVOLUCION", "PROPIO","1.5");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        int nivel = pokemonAtaca.getNivel();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(nivel, 13);
    }

    @Test
    public void TestValidarHabilidadDesevolucionaAPokemonRival() {
        String nombre1 = "Pikachu";
        Estadisticas estadisticas = new Estadisticas(90,45,45);
        Pokemon pokemonAtaca = new Pokemon(nombre1, "La mascota icónica de la serie, conocida por sus rayos eléctricos", 9, 90, Elemento.ELECTRICO, estadisticas);
        String nombre2 = "Bulbasaur";
        estadisticas = new Estadisticas(66,33,33);
        Pokemon pokemonDefiende = new Pokemon(nombre2, "Un pequeño Pokémon que lleva una planta en su espalda", 5, 45, Elemento.PLANTA, estadisticas);
        Habilidades habilidad = GENERADOR_HABILIDADES.crearHabilidad("Segregar Evolucion Rival", "EVOLUCION", "RIVAL", "0.75");
        TablaElementos tabla = TABLA_ELEMENTOS;
        TupleClima tupleClima = new TupleClima(new Clima(TipoClima.TORMENTA_DE_RAYOS, new ArrayList<>()), 0);

        AccionDeBatalla accion = habilidad.usar(pokemonAtaca, pokemonDefiende, tabla, tupleClima);
        Boolean resultado1 = accion.isSeUso();
        Boolean resultado2 = accion.isPasaDeTurno();
        int nivel = pokemonDefiende.getNivel();

        assertEquals(resultado1, true);
        assertEquals(resultado2, true);
        assertEquals(nivel, 3);
    }
}
