import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class GeneradorDeHabilidades {

    Integer primerColumna=0, segundaColumna=1, tercerColumna=2, cuartaColumna=3;

    HashMap<Integer, Habilidades> registrosHabilidades;

    int tamaño;

    protected String linea;

    public void GeneradorDeHabilidades() {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("habilidades.csv"));
            registrosHabilidades = new HashMap<>();
            int i = 0;

            while ((linea = lector.readLine()) != null) {
                String[] datosHabilidad = linea.split(",");
                Habilidades habilidad = crearHabilidad(datosHabilidad[primerColumna], datosHabilidad[segundaColumna], datosHabilidad[tercerColumna], datosHabilidad[cuartaColumna]);
                registrosHabilidades.put(i, habilidad);
                i++;
                }
            lector.close();
            linea = null;
            tamaño = registrosHabilidades.size();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Habilidades devolverHabilidadAleatoria() {
        Random random = new Random();
        int habilidadAleatoria = random.nextInt(tamaño);
        return registrosHabilidades.get(habilidadAleatoria);
    }

    public int tamaño() {
        int tamaño = registrosHabilidades.size();
        return tamaño;
    }

    public Habilidades crearHabilidad(String primeraColumna, String segundaColumna, String tercerColumna, String cuartaColumna) {

        Habilidades habilidad = null;
        switch (segundaColumna) {
            case "ATAQUE":
                if (tercerColumna != "PROPIO" && tercerColumna != "RIVAL") {
                    Elemento elemento = Elemento.valueOf(tercerColumna);
                    int danio = Integer.parseInt(cuartaColumna);
                    habilidad = new HabilidadAtaque(primeraColumna, danio, elemento);
                } else {
                    Estadistica estadistica = Estadistica.ATAQUE;
                    double valor = Double.parseDouble(cuartaColumna);
                    Afecta afectado = Afecta.valueOf(tercerColumna);
                    habilidad = new HabilidadModificacionEstadistica(primeraColumna, estadistica, afectado, valor);
                }
            case "DEFENSA":
                Estadistica estadistica = Estadistica.DEFENSA;
                double valor = Double.parseDouble(cuartaColumna);
                Afecta afectado = Afecta.PROPIO;
                if (tercerColumna == "RIVAL") {
                    afectado = Afecta.RIVAL;
                }
                habilidad = new HabilidadModificacionEstadistica(primeraColumna, estadistica, afectado, valor);
            case "ESTADO":
                Estado nuevoEstadoRival = Estado.valueOf(tercerColumna);
                habilidad = new HabilidadModificacionEstado(primeraColumna, 0, nuevoEstadoRival);
            case "ESTADISTICAS":
                estadistica = Estadistica.valueOf(tercerColumna);
                afectado = Afecta.PROPIO;
                valor = Double.parseDouble(cuartaColumna);
                habilidad = new HabilidadModificacionEstadistica(primeraColumna, estadistica, afectado, valor);
        }
        return habilidad;
    }
 }
