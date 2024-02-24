import java.util.ArrayList;
import java.util.List;
enum Estado {
    MUERTO,
    NORMAL,
    DORMIDO,
    PARALIZADO,
    ENVENENADO,
}

public class Pokemon {
    public static int vidaMaxima = 200;

    public String nombre, info;
    public Integer nivel, velocidad;
    public Estado estado;
    public Elemento elemento;

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    protected Estadisticas estadisticas;
    public ArrayList<Habilidades> habilidades;
    public Habilidades habilidadActual;


    public Pokemon(String nombre, String info, Integer nivel, Integer velocidad, Elemento elemento) {
        this.nombre = nombre;
        this.info = info;
        this.nivel = nivel;
        this.velocidad = velocidad;
        this.elemento = elemento;
        this.estadisticas = new Estadisticas();
        habilidades = new ArrayList<Habilidades>();
    }

    public void restarVida(int danio) {
        int nuevaVida = estadisticas.getVida() - danio;
        if (nuevaVida > 0) {
            this.estadisticas.setVida(nuevaVida);
        } else {
            this.estadisticas.setVida(0);
            this.estado = Estado.MUERTO;
        }
    }

    public void revivirPokemon(Pokemon pokemon) {
        pokemon.setEstado(Estado.NORMAL);
        pokemon.estadisticas = new Estadisticas();
    }

    public String getNombre() {
        return nombre;
    }

    public static int getVidaMaxima() {
        return vidaMaxima;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getNivel() {
        return nivel;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public String getInfo() {
        return info;
    }

    public void anadirHabilidad(Habilidades habilidad) {
        habilidades.add(habilidad);
    }
}
