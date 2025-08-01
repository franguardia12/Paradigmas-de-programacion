package org.tp1.model.ManipularCsv;

import org.tp1.model.PokemonsDependencias.Elemento;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ManipularCSV {

    private BufferedReader Lector; // Lee el archivo
    private String linea; // recibe la linea de cada fila
    public Map<Elemento, Double> tipoReceptor;
    public Map<Elemento, Map<Elemento, Double>> efectividad;

    public Map<Elemento, Map<Elemento, Double>> cargarEfectividadesElementos() {

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("efectividades.csv");

            BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
            Map<Elemento, Double> receptores = new HashMap<Elemento, Double>();
            Map<Elemento, Map<Elemento, Double>> atacantes = new HashMap<Elemento, Map<Elemento, Double>>();
            Integer i = 0;

            while ((linea = lector.readLine()) != null) {
                String[] valores = linea.split(",");
                for (Elemento elem : Elemento.values()) {
                    int posicion = elem.ordinal();
                    String valor = valores[posicion];
                    Double doble = Double.parseDouble(valor);
                    receptores.put(elem, doble);
                }
                Elemento ataca = Elemento.values()[i];
                atacantes.put(ataca, receptores);
                i += 1;
                receptores = new HashMap<Elemento, Double>();
            }
            lector.close();
            linea = null;
            return atacantes;
        } catch (IOException e) {
            System.out.println("Error con el archivo de entrada");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

}
