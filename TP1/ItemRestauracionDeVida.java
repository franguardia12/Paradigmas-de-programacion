public class ItemRestauracionDeVida extends Item {
    private Integer cantidadVida;

    public ItemRestauracionDeVida(String nombre, Integer unidades, Integer cantidad) {
        super(nombre, unidades);
        this.cantidadVida = cantidad;
    }

    private void aumentarVida(Pokemon pokemon) {
        int nuevaVida = pokemon.estadisticas.getVida() + cantidadVida;
        if (nuevaVida >= pokemon.getVidaMaxima()) {
            pokemon.estadisticas.setVida(pokemon.getVidaMaxima());
        } else {
            pokemon.estadisticas.setVida(nuevaVida);
        }
    }

    @Override
    public void consumir(Pokemon pokemon) {
        super.consumir(pokemon);
        if (pokemon.estadisticas.getVida() == pokemon.getVidaMaxima()) {
            System.out.println("El Pokemon seleccionado tiene su valor máximo de vida, no puede curarse");
        } else if (pokemon.getEstado() == Estado.MUERTO) {
            System.out.println("El Pokemon seleccionado está debilitado, no puede curarse");
        } else {
            super.consumir(pokemon);
            aumentarVida(pokemon);
            System.out.println("Item de restauración de vida aplicado exitosamente!");
        }
    }

}
