package org.tp1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tp1.controller.GestionNombresController;
import org.tp1.controller.JuegoController;
import org.tp1.model.Jugabilidad.Juego;
import org.tp1.controller.CambioDeTurnoEvent;

import java.util.concurrent.CompletableFuture;


public class MainJavaFX extends Application {

    protected Stage ventana;

    protected Scene escena1, escena2;

    public MainJavaFX() {
    }

    @Override
    public void init() throws Exception {
        super.init();
        // aca se puede cargar archivos, por ejemplo levantar nuestra app desde algunos json, leer las efectividades, etc
    }

    @Override
    public void start(Stage stage) throws Exception {
        Platform.setImplicitExit(false);

        setVentanaCompleta(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/consultarNombresJugadores.fxml"));
        VBox root = loader.load();
        Scene escenaNombres = new Scene(root);

        stage.setScene(escenaNombres);
        stage.setTitle("Ingreso de Nombres");
        stage.show();

        GestionNombresController nombresController = loader.getController();
        nombresController.setNombresFuturos(new CompletableFuture<>());
        nombresController.getNombresFuturos().thenAccept(nombres -> {
            try {
                // iniciarJuego(nombres);
                Juego juego = new Juego(nombres);
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pantallaprincipallucha.fxml"));
                Scene escenaPrincipal = loader2.load();

                JuegoController juegoController = loader2.getController();
                juegoController.setJuego(juego);

                stage.setScene(escenaPrincipal);
                stage.setTitle("Pantalla Principal");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        /*
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pantallaprincipallucha.fxml"));
        VBox root2 = loader2.load();

        JuegoController juegoController = loader.getController();
        juegoController.setJuego(juego);
        root.addEventHandler(CambioDeTurnoEvent.CAMBIO_DE_TURNO_EVENT, juegoController);

        Stage segundaStage = new Stage();
        Scene scene = new Scene(root);
        segundaStage.setScene(scene);
        */

    }

    private void setVentanaCompleta(Stage stage) {
        this.ventana = stage;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
