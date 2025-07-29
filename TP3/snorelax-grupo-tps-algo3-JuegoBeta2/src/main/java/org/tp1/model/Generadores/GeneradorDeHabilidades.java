package org.tp1.model.Generadores;

import org.tp1.model.Climas.Clima;
import org.tp1.model.Climas.TipoClima;
import org.tp1.model.Habilidades.*;
import org.tp1.model.PokemonsDependencias.Afecta;
import org.tp1.model.PokemonsDependencias.Elemento;
import org.tp1.model.PokemonsDependencias.Estadistica;
import org.tp1.model.PokemonsDependencias.Estado;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class GeneradorDeHabilidades {

    Integer primerColumna = 0, segundaColumna = 1, tercerColumna = 2, cuartaColumna = 3;

    HashMap<Integer, Habilidades> registrosHabilidades;

    protected RegistroDeClimas registroDeClimas;

    protected String linea;


    public void crearGeneradorHabilidades() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("habilidades.csv");

            BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
            setRegistrosHabilidades(new HashMap<>());
            setRegistroDeClimas(new RegistroDeClimas());
            int i = 0;

            while (( linea = lector.readLine()) != null) {
                String[] datosHabilidad = linea.split(",");
                Habilidades habilidad = crearHabilidad(datosHabilidad[primerColumna], datosHabilidad[segundaColumna], datosHabilidad[tercerColumna], datosHabilidad[cuartaColumna]);
                getRegistrosHabilidades().put(i, habilidad);
                i++;
            }
            lector.close();
            linea = null;
        } catch (IOException e) {
            System.out.println("Hubo un error con la lectura del archivo de habilidades.");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Habilidades devolverHabilidadAleatoria() {
        Random random = new Random();
        int aleatoria = random.nextInt(getRegistrosHabilidades().size());
        return getRegistrosHabilidades().get(aleatoria);
    }

    public Habilidades crearHabilidad(String primeraColumna, String segundaColumna, String tercerColumna, String cuartaColumna) {

        Habilidades habilidad = null;

        if (segundaColumna.equals("ATAQUE")) {
            if (tercerColumna.equals("PROPIO") || tercerColumna.equals("RIVAL")) {
                Estadistica estadistica = Estadistica.ATAQUE;
                double valor = Double.parseDouble(cuartaColumna);
                Afecta afectado = Afecta.valueOf(tercerColumna);
                return new HabilidadModificacionEstadistica(primeraColumna, estadistica, afectado, valor);
            }
            Elemento elemento = Elemento.valueOf(tercerColumna);
            int danio = Integer.parseInt(cuartaColumna);
            return new HabilidadAtaque(primeraColumna, danio, elemento);
        } else if (segundaColumna.equals("DEFENSA")) {
            Estadistica estadistica = Estadistica.DEFENSA;
            double valor = Double.parseDouble(cuartaColumna);
            Afecta afectado = Afecta.PROPIO;
            if (tercerColumna.equals("RIVAL")) {
                afectado = Afecta.RIVAL;
            }
            return new HabilidadModificacionEstadistica(primeraColumna, estadistica, afectado, valor);
        } else if (segundaColumna.equals("ESTADO")) { // ASUMIMOS QUE ESTADOS Y ESTADISTICAS NO TIENEN ELEMENTOS
            Estado nuevoEstadoRival = Estado.valueOf(tercerColumna);
            return new HabilidadModificacionEstado(primeraColumna, 0, nuevoEstadoRival);
        } else if (segundaColumna.equals("ESTADISTICAS")) {
            Estadistica estadistica = Estadistica.valueOf(tercerColumna);
            Afecta afectado = Afecta.PROPIO;
            double valor = Double.parseDouble(cuartaColumna);
            return new HabilidadModificacionEstadistica(primeraColumna, estadistica, afectado, valor);
        } else if (segundaColumna.equals("CLIMA")) {
            int poder = Integer.parseInt(cuartaColumna);
            TipoClima tipoClima = TipoClima.valueOf(tercerColumna);
            Clima clima = getRegistroDeClimas().getClimas().get(tipoClima);
            return new HabilidadModificaClima(primeraColumna, poder, clima);
        } else if (segundaColumna.equals("EVOLUCION")) {
            double porcentajeNivel = Double.parseDouble(cuartaColumna);
            Afecta afecta = Afecta.valueOf(tercerColumna);
            return new HabilidadDeEvolucion(primeraColumna, porcentajeNivel, afecta);
        }
        return habilidad;
    }

    public void setRegistroDeClimas(RegistroDeClimas registroDeClimas) {
        this.registroDeClimas = registroDeClimas;
    }

    public HashMap<Integer, Habilidades> getRegistrosHabilidades() {
        return this.registrosHabilidades;
    }


    public void setRegistrosHabilidades(HashMap<Integer, Habilidades> registrosHabilidades) {
        this.registrosHabilidades = registrosHabilidades;
    }

    public RegistroDeClimas getRegistroDeClimas() {
        return registroDeClimas;
    }
}
