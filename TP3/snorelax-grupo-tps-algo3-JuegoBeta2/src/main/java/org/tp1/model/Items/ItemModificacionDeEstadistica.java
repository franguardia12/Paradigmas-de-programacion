package org.tp1.model.Items;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.PokemonsDependencias.Estadistica;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class ItemModificacionDeEstadistica extends Item {
    protected Estadistica estadisticaAumentada;
    private static double porcentajeAumento;

    public ItemModificacionDeEstadistica(String nombre, Integer unidades, Estadistica estadisticaAumentada, double porcentajeAumento) {
        super(nombre, unidades);
        setEstadisticaAumentada(estadisticaAumentada);
        setPorcentajeAumento(porcentajeAumento);
    }

    @Override
    public AccionDeBatalla usar(Pokemon pokemon) {

        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();

        if (pokemon.estaMuerto()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else {
            super.descartarUnaUnidad();
            pokemon.modificarEstadistica(getEstadisticaAumentada(), getPorcentajeAumento());
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        }
        accionDeBatalla.setItemUsado(this);
        return accionDeBatalla;
    }

    public Estadistica getEstadisticaAumentada() {
        return estadisticaAumentada;
    }

    public static double getPorcentajeAumento() {
        return porcentajeAumento;
    }

    public void setEstadisticaAumentada(Estadistica estadisticaAumentada) {
        this.estadisticaAumentada = estadisticaAumentada;
    }

    public void setPorcentajeAumento(double porcentajeAumento) {
        this.porcentajeAumento = porcentajeAumento;
    }
}
