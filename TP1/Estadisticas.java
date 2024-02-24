public class Estadisticas implements Modificable {
    protected Integer defensa;
    protected Integer ataque;
    protected Integer vida;

    public Estadisticas() {
    }

    public void modificar(Estadistica estadistica, double porcentaje) {
        int nro;
        if (estadistica == Estadistica.ATAQUE) {
            this.ataque = nuevoValor(ataque, porcentaje);
        } else if (estadistica == Estadistica.DEFENSA) {
            this.defensa = nuevoValor(defensa, porcentaje);
        } else {
            this.vida = nuevoValor(vida, porcentaje);
        }
    }

    private int nuevoValor(int nro, double porcentaje) {
        double nuevoNro = nro * porcentaje;
        return (int) nuevoNro;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

    public Integer getDefensa() {
        return defensa;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public Integer getVida() {
        return vida;
    }
}