public interface Gestionable {

    public void usarHabilidad();

    public void usarItem();

    public void seleccionarItem(String nombre);

    public void seleccionarHabilidad(String nombre);

    public void verHabilidadesDisponibles();

    public void verItemsDisponibles();

    public void verPokemonsDisponibles();

    public void verCampoDeBatalla();

    public void intercambiarPokemonActual(Pokemon pokemon);

    public void rendirse();

}
