public class HabilidadAtaque extends Habilidades {

    protected Elemento elemento;

    public HabilidadAtaque(String nombre, Integer poder, Elemento elemento) {
        super(nombre, poder);
        this.elemento = elemento;
    }

    public Elemento getElemento() {
        return elemento;
    }
}
