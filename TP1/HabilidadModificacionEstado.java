public class HabilidadModificacionEstado extends Habilidades implements modificadoraEstado {
    private Estado nuevoEstadoRival;

    public HabilidadModificacionEstado(String nombre, Integer poder, Estado nuevoEstadoRival) {
        super(nombre,  poder);
        this.nuevoEstadoRival = nuevoEstadoRival;
    }

    @Override
    public void modificarEstado(Pokemon pokemon) {
        pokemon.estado = nuevoEstadoRival;
    }

    public Estado getNuevoEstadoRival() {
        return nuevoEstadoRival;
    }
}
