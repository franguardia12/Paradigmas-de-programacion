package org.tp1.model.Habilidades;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Climas.TupleClima;
import org.tp1.model.ManipularCsv.TablaElementos;
import org.tp1.model.PokemonsDependencias.Afecta;
import org.tp1.model.PokemonsDependencias.Elemento;
import org.tp1.model.PokemonsDependencias.Pokemon;

public class HabilidadAtaque extends Habilidades {

    protected Elemento elemento;

    public HabilidadAtaque(String nombre, Integer poder, Elemento elemento) {
        super(nombre, poder);
        this.elemento = elemento;
    }

    @Override
    public Object getElemento() {
        return elemento;
    }

    @Override
    public AccionDeBatalla usar(Pokemon ataca, Pokemon defiende, TablaElementos tabla, TupleClima tupleClima) {
        AccionDeBatalla accionDeBatalla = new AccionDeBatalla();
        if (super.estaUsada()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(false);
        } else if (ataca.estaConfundido() && super.posibilidadDeAutoDanio()) {
            super.consumir();
            int autoDanio = (int) (ataca.getVida() * 0.15);
            if (tupleClima.getClima().climaFavorece(ataca)) {
                autoDanio *= 1.1;
            }
            ataca.restarVida(autoDanio);
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        } else if (!super.validarParalizado(ataca)) {
            super.consumir();
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
        } else if (ataca.estaDormido()) {
            accionDeBatalla.setSeUso(false);
            accionDeBatalla.setPasaDeTurno(true);
        } else {
            super.consumir();
            int danio = calcularDanio(ataca, this, defiende, tabla);
            if (tupleClima.getClima().climaFavorece(ataca)) {
                danio *= 1.1;
            }
            defiende.restarVida(danio);
            accionDeBatalla.setSeUso(true);
            accionDeBatalla.setPasaDeTurno(true);
            accionDeBatalla.setAfecta(Afecta.RIVAL);
        }
        accionDeBatalla.setHabilidadUsada(this);
        return accionDeBatalla;
    }

    public int calcularDanio(Pokemon ataca, HabilidadAtaque habilidadAtaque, Pokemon defiende, TablaElementos tabla) {
        int nivel = ataca.getNivel();
        int critico = calcularCriticoRandom();
        int poder = habilidadAtaque.getPoder();
        int A = ataca.getEstadisticas().getAtaque();
        int D = defiende.getEstadisticas().getDefensa();
        double mismoTipo = mismoTipoHabilidadPokemon(habilidadAtaque, ataca);
        double tipo = tabla.comparar(ataca.getElemento(), defiende.getElemento());
        int random = generarRandomEntre217y255();

        double numerador = (double) (2 * nivel * critico * poder * A) /D;

        double resultado = (((numerador/5)+2)/50) * mismoTipo * tipo * random;

        return (int) resultado;
    }

    private int calcularCriticoRandom() {

        double random = Math.random();
        if (random < 0.9) {
            return 2;
        } else {
            return 1;
        }
    }

    private double mismoTipoHabilidadPokemon(Habilidades habilidad, Pokemon pokemon) {
        if (pokemon.getElemento().equals(habilidad.getElemento())) {
            return 1.5;
        }
        return 1;
    }

    private int generarRandomEntre217y255() {
        int randomizado;
        do randomizado = (int) (Math.random() * 1000)%256;
        while (randomizado < 217) ;
        return randomizado;
    }

}
