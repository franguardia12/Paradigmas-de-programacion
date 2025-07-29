package org.tp1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.tp1.model.Items.Item;
import org.w3c.dom.Text;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class itemController {
    @FXML
    public Label nombreItemUno, nombreItemDos, nombreItemTres, nombreItemCuatro;

    @FXML
    public Label usosItemUno, usosItemDos, usosItemTres, usosItemCuatro;

    @FXML
    public Text infoItemActual;

    @FXML
    public ImageView imagenItemActual;

    /*
    private void cambiarDePantalla(String fxmlpath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
            VBox root = loader.load();
            Scene scene = new Scene(root);
            secondaryStage.setScene(getPrimaryScene());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    @FXML
    private void onItemOption() {
        //Chequear si el item tiene usos o no
        //En caso de que sí cambiar la pantalla, en caso de que no mostrar mensaje de error
        //Luego aplicar lógica de uso de item
    }

    private void tieneUsosDisponibles(Item item) {

    }

}
