public class ItemRevivir extends Item implements modificadoraEstado {
    public ItemRevivir(String nombre, Integer unidades) {
        super(nombre, unidades);
    }

    @Override
    public void consumir(Pokemon pokemon) {
        if (pokemon.getEstado() != Estado.MUERTO) {
            System.out.println("El Pokemon seleccionado no se encuentra debilitado, el item no puede aplicarse");
            return;
        }
        super.consumir(pokemon);
        modificarEstado(pokemon);
        System.out.printf("Se ha revivido exitosamente al Pokemon %s, de vuelta a la batalla!", pokemon.getNombre());
    }

    public void modificarEstado(Pokemon pokemon) {
        pokemon.revivirPokemon(pokemon);
    }
}
