package org.tp1.controller;

import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class pokemonController {
    @FXML
    public ImageView pokemonUno, pokemonDos, pokemonTres, pokemonCuatro, pokemonCinco, pokemonSeis;

    @FXML
    public Label nombrePokemonUno, nombrePokemonDos, nombrePokemonTres, nombrePokemonCuatro, nombrePokemonCinco, nombrePokemonSeis;

    @FXML
    public Label nivelPokemonUno, nivelPokemonDos, nivelPokemonTres, nivelPokemonCuatro, nivelPokemonCinco, nivelPokemonSeis;
    
    @FXML
    public Label estadoPokemonUno, estadoPokemonDos, estadoPokemonTres, estadoPokemonCuatro, estadoPokemonCinco, estadoPokemonSeis;
    
    @FXML
    public Label vidaPokemonUno, vidaPokemonDos, vidaPokemonTres, vidaPokemonCuatro, vidaPokemonCinco, vidaPokemonSeis;

    @FXML
    public Label botonVolverAtras;
}