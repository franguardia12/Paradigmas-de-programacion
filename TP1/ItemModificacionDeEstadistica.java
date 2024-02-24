public class ItemModificacionDeEstadistica extends Item {
    private Estadistica estadisticaAumentada;
    private static double porcentajeAumento = 1.1;

    public ItemModificacionDeEstadistica(String nombre, Integer unidades, Estadistica estadisticaAumentada) {
        super(nombre, unidades);
        this.estadisticaAumentada = estadisticaAumentada;
    }

    @Override
    public void consumir(Pokemon pokemon) {
        super.consumir(pokemon);
        pokemon.estadisticas.modificar(estadisticaAumentada, porcentajeAumento);
    }
}
