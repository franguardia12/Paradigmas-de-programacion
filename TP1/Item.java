public class Item {
    protected String nombre;
    protected Integer unidades;

    public Item(String nombre, Integer unidades) {
        this.nombre = nombre;
        this.unidades = unidades;
    }

    public boolean estaUsado() {
        return unidades == 0;
    }

    public void consumir(Pokemon pokemon) {
        unidades -= 1;
    }
}
