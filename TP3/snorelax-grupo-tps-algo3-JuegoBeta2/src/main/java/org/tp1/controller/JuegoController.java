package org.tp1.controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Jugabilidad.Juego;

import org.tp1.model.Jugabilidad.Jugador;
import org.tp1.model.PokemonsDependencias.Pokemon;
import javafx.scene.text.Text;

import javafx.scene.image.ImageView;
import java.io.IOException;


public class JuegoController implements EventHandler<CambioDeTurnoEvent> {

    public Juego juego;

    @FXML
    public Pane nodoMensajes;

    @FXML
    public HBox nodoOpciones;

    @FXML
    public HBox nodoHabilidades;

    @FXML
    public Label elementoHabilidad;

    @FXML
    public Label nombrePokemonDos;

    @FXML
    public Label nivelPokemonDos;

    @FXML
    public Label pokemonsJugadorDos;

    @FXML
    public Label estadoPokemonDos;

    @FXML
    public StackPane nodoConfirmacion;

    @FXML
    public VBox nodoEstadisticas;

    @FXML
    public Label nombrePokemonUno;

    @FXML
    public Label nivelPokemonUno;

    @FXML
    public Label vidaPokemonUno;

    @FXML
    public Label vidaPokemonDos;

    @FXML
    public Label estadoPokemonUno;

    @FXML
    public Label pokemonsJugadorUno;

    protected Jugador jugadorActual;

    protected AdminDeJuego adminJuego;

    @FXML
    public Label estadoPokemonAtaca, estadoPokemonsDefiende;

    @FXML
    public Label pokemonsJugadorAtaca, pokemonsJugadorDefiende;

    @FXML
    public Button habilidadUno, habilidadDos, habilidadTres, habilidadCuatro;

    @FXML
    public Text mensajePokemonActual, textoActual;

    @FXML
    public ImageView pokemonActual, pokemonRival, imagenClimaActual;

    private Stage primaryStage;

    private Stage secondaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.secondaryStage = secondaryStage;
    }

    public JuegoController() {
    }

    public void setJuego(Juego juego) {
        this.juego = juego;

        /*
        this.jugador1.setText(juego.getJugadorActual().getNombre());
        this.jugador2.setText(juego.obtenerRival(juego.getJugadorActual()).getNombre());
         */

        Jugador jugador1 = juego.getJugadores().get(0);
        Jugador jugador2 = juego.getJugadores().get(1);

        Pokemon pokemonAtaca = juego.getJugadorActual().getPokemonActual();
        Pokemon pokemonDefiende = juego.obtenerRival(juego.getJugadorActual()).getPokemonActual();
        this.nombrePokemonUno.setText(pokemonAtaca.getNombre());
        this.nombrePokemonDos.setText(pokemonDefiende.getNombre());
        this.pokemonsJugadorAtaca.setText("POKEMONS CON VIDA: " + jugador1.getPokemons().size());
        this.pokemonsJugadorDefiende.setText("POKEMONS CON VIDA: " + jugador2.getPokemons().size());
        this.mensajePokemonActual.setText("¿Qué debería hacer "+ pokemonAtaca + "?");
        this.elementoHabilidad.setText("TIPO "+ pokemonAtaca.getHabilidades().get(0).getElemento());

        this.estadoPokemonAtaca.setText(pokemonAtaca.getEstadosAString());
        this.estadoPokemonsDefiende.setText(pokemonDefiende.getEstadosAString());

        this.nivelPokemonUno.setText(String.format("lvl %d", pokemonAtaca.getNivel()));
        this.nivelPokemonDos.setText(String.format("lvl %d", pokemonDefiende.getNivel()));

        this.habilidadUno.setText(pokemonAtaca.getHabilidad(0).getNombre());
        this.habilidadDos.setText(pokemonAtaca.getHabilidad(1).getNombre());
        this.habilidadTres.setText(pokemonAtaca.getHabilidad(2).getNombre());
        this.habilidadCuatro.setText(pokemonAtaca.getHabilidad(3).getNombre());

        this.vidaPokemonUno.setText(String.format("%d/%d", pokemonAtaca.getVida(), pokemonAtaca.getVida()));
        this.vidaPokemonDos.setText(String.format("%d/%d", pokemonDefiende.getVida(), pokemonAtaca.getVida()));

        javafx.scene.image.Image imagen1 = new javafx.scene.image.Image(getClass().getResource("/org/tp1/resources/imagenesPokemon/sinclima.png").toExternalForm());
        this.imagenClimaActual.setImage(imagen1);

        String rutaPokemonActual = String.format("/org/tp1/resources/imagenesPokemon/%satras.png", pokemonAtaca.getNombre().toLowerCase());
        javafx.scene.image.Image imagen2 = new javafx.scene.image.Image(getClass().getResource(rutaPokemonActual).toExternalForm());
        this.pokemonActual.setImage(imagen2);

        String rutaPokemonRival = String.format("/org/tp1/resources/imagenesPokemon/%sfrente.png", pokemonDefiende.getNombre().toLowerCase());
        javafx.scene.image.Image imagen3 = new javafx.scene.image.Image(getClass().getResource(rutaPokemonRival).toExternalForm());
        this.pokemonActual.setImage(imagen3);
    }

    @Override
    public void handle(CambioDeTurnoEvent cambioDeTurnoEvent) {
        this.juego.proximoTurno();
        this.setJuego(juego);
    }

    private void cambiarDePantalla(String fxmlpath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
            VBox root = loader.load();
            Scene scene = new Scene(root);
            //secondaryStage.setScene(getPrimaryScene());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modificarVisibilidad(String idNodoOcultar, String idNodoMostrar) {

        Node nodoOcultar = primaryStage.getScene().lookup("#" + idNodoOcultar);
        Node nodoMostrar = primaryStage.getScene().lookup("#" + idNodoMostrar);

        if (nodoOcultar != null) {
            nodoOcultar.setVisible(false);
        }

        if (nodoMostrar != null) {
            nodoMostrar.setVisible(true);
        }
    }

    @FXML
    public void manejarHabilidadUno() {
        AccionDeBatalla accionDeBatalla = juego.atacar(juego.getPokemonActual(), juego.getPokemonRival(), juego.getHabilidadAtacante(0));
        if (accionDeBatalla.isPasaDeTurno()) {
            if (juego.getAdminJuego().siguienteTurno()) {
                habilidadUno.fireEvent(new CambioDeTurnoEvent());
            } else {
                // LANZAR EVENTO DE SE TERMINO EL JUEGO Y AVISAR QUIEN GANO
            }
        }
    }

    @FXML
    public void manejarHabilidadDos() {

    }

    @FXML
    public void manejarHabilidadTres() {

    }

    @FXML
    public void manejarHabilidadCuatro() {

    }

    @FXML
    public void onMochilaOption() {
        cambiarDePantalla("pantallaprincipalmochila.fxml");
    }

    @FXML
    public void onRendirseOption() {
        modificarVisibilidad("nodoEstadisticas", "nodoConfirmacion");
    }

    @FXML
    public void onPokemonsOption() {
        cambiarDePantalla("pantallaprincipalpokemon.fxml");
    }

    @FXML
    public void onLuchaOption() {
        modificarVisibilidad("nodoOpciones", "nodoHabilidades");
    }

    @FXML
    public void onAceptarOption() {
        //Mostrar el usuario que ganó el juego, con alguna música de felicitación y un mensaje
    }

    @FXML
    public void onRechazarOption() {
        //Volver para atrás
    }

    public Juego getJuego() {
        return this.juego;
    }
}
