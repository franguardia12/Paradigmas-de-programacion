import java.util.Map;

public class TablaElementos {
    public Map<Elemento, Map<Elemento, Double>> efectividades;

    public TablaElementos(String ruta) {
        ManipularCSV archivo = new ManipularCSV();
        Map<Elemento, Map<Elemento, Double>> efectividades = archivo.cargarEfectividadesElementos(ruta);
        this.efectividades = efectividades;
    }

    public Double comparar(Elemento elemento1, Elemento elemento2) {
        return efectividades.get(elemento1).get(elemento2);
    }

    public void verTabla() {
        for (Elemento atacante : efectividades.keySet()) {
            Map<Elemento, Double> defensores = efectividades.get(atacante);
            for (Elemento defensor: defensores.keySet()) {
                String mensaje = String.format("Atacante: %s, Defensor: %s, Efectividad: %.1f", atacante.name(), defensor.name(), efectividades.get(atacante).get(defensor));
                System.out.println(mensaje);
            };
        }

    }
}
