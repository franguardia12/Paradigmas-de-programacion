import java.util.*;

public class AdministradorDeEstados {

    protected List<Pokemon> pokemonsAfectados;
    protected Map<Pokemon, Integer> dormidos;

    public AdministradorDeEstados() {
        this.pokemonsAfectados = new ArrayList<>();
        this.dormidos = new HashMap<>();
    }

    protected void agregar(Pokemon pokemon) {
        this.pokemonsAfectados.add(pokemon);
        Estado estadoAct = pokemon.getEstado();
        if (estadoAct == Estado.DORMIDO) {
            this.dormidos.put(pokemon, 0);
        }
    }

    protected void aplicarTurno() {
        for (Pokemon pokemon : pokemonsAfectados) {
            Estado estadoAct = pokemon.getEstado();
            if (estadoAct == Estado.NORMAL) {
                this.pokemonsAfectados.remove(pokemon);
            } else if (estadoAct == Estado.ENVENENADO) {
                pokemon.estadisticas.modificar(Estadistica.VIDA, 0.95);
            } else if (estadoAct == Estado.DORMIDO) {
                int turnosDormido = this.dormidos.get(pokemon);
                    turnosDormido++;
                if (seDespierta(turnosDormido)) {
                    pokemon.setEstado(Estado.NORMAL);
                    this.pokemonsAfectados.remove(pokemon);
                    this.dormidos.remove(pokemon);
                }
            }
        }
    }

    protected boolean seDespierta(int turno) {
        Random random = new Random();
        int nroRandom = random.nextInt(4);
        if (0.25 + nroRandom * 0.25 >= 1) {
            return true;
        }
        return false;
    }
}
