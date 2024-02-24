public class HabilidadModificacionEstadistica extends Habilidades {
    protected Afecta afectado;
    protected Estadistica estadistica;
    protected double porcentaje;
    public HabilidadModificacionEstadistica(String nombre, Estadistica estadistica, Afecta afectado, Double valor) {
        super(nombre,  valor.intValue());
        this.afectado = afectado;
        this.estadistica = estadistica;
        this.porcentaje = valor;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public Afecta getAfectado() {
        return afectado;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }
}