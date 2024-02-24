public class AdministradorHabilidades {

    public Pokemon atacante, defensor;
    public TablaElementos tabla;
    public AdministradorDeEstados adminEstados;

    public AdministradorHabilidades(TablaElementos tabla, Jugador ataca, Jugador defiende) {
        this.atacante = ataca.pokemonActual;
        this.defensor = defiende.pokemonActual;
        this.tabla = tabla;
        this.adminEstados = new AdministradorDeEstados();
    }

    public void usarHabilidadActual() {
        Habilidades habilidad = atacante.habilidadActual;
        if (habilidad instanceof HabilidadAtaque) {
            int danio = calcularDanio();
            this.defensor.restarVida(danio);
        } else if (habilidad instanceof HabilidadModificacionEstado) {
            if (defensor.getEstado() == Estado.NORMAL) {
                ((HabilidadModificacionEstado) habilidad).modificarEstado(defensor);
                adminEstados.agregar(defensor);
            } else {
                return;
            }
        } else {
            Afecta afectado = ((HabilidadModificacionEstadistica) habilidad).getAfectado();
            Estadistica estadistica = ((HabilidadModificacionEstadistica) habilidad).getEstadistica();
            double porcentaje = ((HabilidadModificacionEstadistica) habilidad).getPorcentaje();
            if (afectado == Afecta.PROPIO) {
                atacante.estadisticas.modificar(estadistica, porcentaje);
            } else {
                defensor.estadisticas.modificar(estadistica, porcentaje);
            }
        }
        habilidad.usar();
    }

    protected int calcularDanio() {
        int nivel = atacante.getNivel();
        int critico = calcularCriticoRandom();
        int poder = atacante.habilidadActual.getPoder();
        int A = atacante.estadisticas.getAtaque();
        int D = defensor.estadisticas.getDefensa();
        double mismoTipo = mismoTipoHabilidadPokemon((HabilidadAtaque) atacante.habilidadActual, atacante);
        double tipo = tabla.comparar(atacante.getElemento(), defensor.getElemento());
        int random = generarRandomEntre217y255();

        double resultado = (((2 * nivel * critico * poder * (A/D))/5+2)/50) * mismoTipo * tipo * random;

        return (int) resultado;
    }

    private int calcularCriticoRandom() {

        double random = Math.random();
        if (random < 0.9) {
            return 2;
        } else {
            return 1;
        }
    }

    private double mismoTipoHabilidadPokemon(HabilidadAtaque habilidad, Pokemon pokemon) {
        if (pokemon.getElemento() == habilidad.getElemento()) {
            return 1.5;
        }
        return 1;
    }

    private int generarRandomEntre217y255() {
        int randomizado;
        do randomizado = (int) (Math.random() * 1000)%256;
        while (randomizado < 217) ;
        return randomizado;
    }
}
