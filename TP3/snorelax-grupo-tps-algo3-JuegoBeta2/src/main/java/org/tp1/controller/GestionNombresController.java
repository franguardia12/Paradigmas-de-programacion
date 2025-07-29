package org.tp1.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class GestionNombresController {

    private final List<String> nombres = new ArrayList<>();

    private boolean ingresoJugadorUno = false;

    private CompletableFuture<List<String>> nombresFuturos;

    @FXML
    protected VBox root;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreTextField;

    @FXML
    private Button aceptarButton;

    @FXML
    private void handleAceptar() {
        String nombreJugador = getNombreTextField().getText();
        getNombres().add(nombreJugador);
        limpiarCampo(getNombreTextField());
        if (!isIngresoJugadorUno()) {
            getNombreLabel().setText("Jugador2 ingrese su nombre");
            setIngresoJugadorUno(true);
        } else {
            nombresFuturos.complete(getNombres());
        }
    }

    public void stop() {
        Platform.exit();
    }

    public void setIngresoJugadorUno(boolean ingresoJugadorUno) {
        this.ingresoJugadorUno = ingresoJugadorUno;
    }

    public void setNombresFuturos(CompletableFuture<List<String>> nombresFuturos) {
        this.nombresFuturos = nombresFuturos;
    }

    private void limpiarCampo(TextField textField) {
        textField.clear();
    }

    public boolean isIngresoJugadorUno() {
        return ingresoJugadorUno;
    }

    public List<String> getNombres() {
        return this.nombres;
    }

    public Label getNombreLabel() {
        return nombreLabel;
    }

    public TextField getNombreTextField() {
        return nombreTextField;
    }

    public VBox getRoot() {
        return root;
    }

    public CompletableFuture<List<String>> getNombresFuturos() {
        return nombresFuturos;
    }
}
