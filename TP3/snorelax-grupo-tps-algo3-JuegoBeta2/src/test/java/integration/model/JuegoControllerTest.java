package integration.model;

import org.junit.jupiter.api.Test;
import org.tp1.JuegoController;
import org.tp1.controller.AdminDeJuego;
import org.tp1.model.Generadores.GeneradorDeHabilidades;
import org.tp1.model.Generadores.GeneradorDePokemones;
import org.tp1.model.Habilidades.HabilidadAtaque;
import org.tp1.model.Jugabilidad.Juego;
import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Pokemon;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/*
public class JuegoControllerTest {

    GeneradorDePokemones GENERADOR_POKEMONS = new GeneradorDePokemones();

    GeneradorDeHabilidades GENERADOR_HABILIDADES = new GeneradorDeHabilidades();

    @Test
    public void TestJugarPartidaUnPokemonCadaJugador() {

        GeneradorDeHabilidades generadorDeHabilidades = new GeneradorDeHabilidades();

        HabilidadAtaque habilidadAtaque = (HabilidadAtaque) generadorDeHabilidades.getRegistrosHabilidades().get(0);
        Pokemon pikachu = GENERADOR_POKEMONS.getPokemon("Pikachu");

        Jugador jugador1 = new Jugador("Cuti Romero");
        jugador1.setPokemonActual(pikachu);

        Jugador jugador2 = new Jugador("Otamendi");
        Pokemon bulbasaur = GENERADOR_POKEMONS.getPokemon("Bulbasaur");
        final int vidaAnt = bulbasaur.getVida();
        jugador2.setPokemonActual(bulbasaur);

        AdminDeJuego adminDeJuego = mock(AdminDeJuego.class);
        adminDeJuego.setJugador1(jugador1);
        adminDeJuego.setJugador2(jugador2);

        JuegoController juegoController = mock(JuegoController.class);
        when(juegoController.leerOpcionDeEntrada()).thenReturn(0);
        juegoController.setAdminJuego(adminDeJuego);

        Juego juego = mock(Juego.class);
        juego.setJuegoController(juegoController);

        juego.inicializarJuego();

        juego.getJuegoController().getAdminJuego().usarHabilidad(jugador1.pokemonActual, jugador2.pokemonActual, habilidadAtaque);
        assert (vidaAnt > jugador2.getPokemonActual().getVida());

        /*
        when(juego.inicializarJuego());
        when(juego.getJuegoController().leerOpcionDeEntrada()).thenReturn(0);
        for (Jugador jugador : juego.getJuegoController().getAdminJuego().getJugadores()) {
            assert (jugador.getPokemons().size() == 6);
            for (Pokemon pokemon : jugador.getPokemons()) {
                assert (pokemon.getHabilidades().size() == 4);
            }
        }


        */
        //JuegoController controlador = juego.getJuegoController();



/*
        public class PruebaMiClase {
            @Test
            public void pruebaMetodoPrincipal() {
                InterfazUsuario interfazMock = Mockito.mock(InterfazUsuario.class);
                Mockito.when(interfazMock.leerEntrada()).thenReturn("Hola");

                MiClase miClase = new MiClase(interfazMock);
                miClase.metodoPrincipal();

                Mockito.verify(interfazMock).mostrarMensaje("Ingrese algo: ");
                Mockito.verify(interfazMock).mostrarMensaje("Usted ingresó: Hola");
            }
        }
        */