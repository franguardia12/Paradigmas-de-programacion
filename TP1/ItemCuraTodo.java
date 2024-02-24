public class ItemCuraTodo extends Item implements modificadoraEstado {

    public ItemCuraTodo(String nombre, Integer unidades) {
        super(nombre, unidades);
    }

    @Override
    public void consumir(Pokemon pokemon) {
        Estado estadoAct = pokemon.getEstado();
        if (estadoAct == Estado.NORMAL || estadoAct == Estado.MUERTO) {
            System.out.println("El pokemon no se puede curar al estado normal, el item no puede aplicarse");
            return;
        }
        super.consumir(pokemon);
        modificarEstado(pokemon);
        System.out.printf("El pokemon: %s satisfactoriamente volvio al estado normal\n", pokemon.getNombre());
    }

    public void modificarEstado(Pokemon pokemon) {
        pokemon.setEstado(Estado.NORMAL);
    }
}
