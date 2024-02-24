public abstract class Habilidades {
    private String nombre;
    private Integer usos;
    private Integer poder;

    protected void usar() {
        this.usos = usos - 1;
    }

    public boolean estaUsada() {
        return usos == 0;
    }

    public Integer getPoder() {
        return poder;
    }

    public String getNombre(){return nombre;}


    protected Habilidades(String nombre, Integer poder){
        this.nombre = nombre;
        this.poder = poder;
        this.usos = 4;
    }

    public Integer getUsos() {
        return usos;
    }
}
