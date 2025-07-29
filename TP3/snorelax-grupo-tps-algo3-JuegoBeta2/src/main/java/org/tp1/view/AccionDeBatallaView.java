package org.tp1.view;

import org.tp1.model.AccionDeBatalla.AccionDeBatalla;
import org.tp1.model.Habilidades.HabilidadModificacionEstado;
import org.tp1.model.PokemonsDependencias.Afecta;
import org.tp1.model.PokemonsDependencias.Estado;
import org.tp1.model.PokemonsDependencias.Pokemon;

import java.util.List;
import java.util.stream.Stream;

public class AccionDeBatallaView {

    public AccionDeBatallaView() {
    }

    public static void mostrarAccionDeBatallaHabilidad(Pokemon atacaAnt, Pokemon atacaProx, Pokemon defiendeAnt, Pokemon defiendeProx, AccionDeBatalla accionDeBatalla) {

        if (!accionDeBatalla.isSeUso() && !accionDeBatalla.isPasaDeTurno()) {
            if (accionDeBatalla.getHabilidadUsada() != null && accionDeBatalla.getHabilidadUsada().estaUsada()) {
                System.out.println("No se puede utilizar la habilidad porque posee 0 usos disponibles");
            } else {
                HabilidadModificacionEstado habilidad = (HabilidadModificacionEstado) accionDeBatalla.getHabilidadUsada();
                System.out.println(String.format("No se puede utilizar la habilidad %s porque el pokemon %s ya se encuentra en estado %s\nCantidad de Usos Disponibles: %d\n", accionDeBatalla.getHabilidadUsada().getNombre(), defiendeProx.getNombre(), habilidad.getNuevoEstadoRival().toString(), habilidad.getUsos()));
            }
        } else if (!accionDeBatalla.isSeUso() && accionDeBatalla.isPasaDeTurno() && atacaProx.estaDormido()) {
            System.out.println(String.format("El pokemon %s esta DORMIDO, no se consume la habilidad pero pasa de turno\n", atacaProx.getNombre()));
        } else {
            if (accionDeBatalla.getAfecta() == Afecta.PROPIO) {
                if (atacaAnt.getVida() == atacaProx.getVida() && atacaProx.getEstados().contains(Estado.PARALIZADO)) {
                    System.out.printf(String.format("MISS MISS MISS el pokemon %s esta paralizado, se disminuye la cantidad de Usos en uno pero la habilidad no tiene efecto.\n", atacaProx.getNombre(), accionDeBatalla.getHabilidadUsada().getUsos()));
                } else if (atacaAnt.getVida() > atacaProx.getVida()) {
                    System.out.println(String.format("El pokemon %s se encuentra confundido, se daño %d a si mismo.\n", atacaProx.getNombre(), atacaAnt.getVida() - atacaProx.getVida()));
                } else if (atacaAnt.getVida() < atacaProx.getVida()) {
                    System.out.printf("El pokemon %s a aumentado su vida. \n", atacaProx.getNombre());
                } else if (atacaAnt.getAtaque() < atacaProx.getAtaque()) {
                    System.out.printf("El pokemon %s a aumentado su ataque. \n", atacaProx.getNombre());
                } else if (atacaAnt.getDefensa() < atacaProx.getDefensa()) {
                    System.out.printf("El pokemon %s a aumentado su defensa. \n", atacaProx.getNombre());
                } else if (atacaAnt.getNivel() < atacaProx.getNivel()) {
                    System.out.println(String.format("El pokemon %s ha Evolucionado.\n", atacaProx.getNombre(), accionDeBatalla.getHabilidadUsada().getUsos()));
                } else if (atacaAnt.getEstados() != atacaProx.getEstados()) {
                    Stream<Estado> estadoStream = atacaProx.getEstados().stream().filter(e -> (!atacaAnt.getEstados().contains(e)));
                    Estado estado = (Estado) estadoStream.toArray()[0];
                    System.out.printf("El Pokemon %s ahora esta en Estado %s\n", atacaProx.getNombre(), estado.toString());
                }
            } else if (accionDeBatalla.getAfecta() == Afecta.RIVAL) {
                if (!defiendeAnt.estaMuerto() && defiendeProx.estaMuerto()) {
                    System.out.printf("Se ha Muerto el Pokemon %s\n", defiendeProx.getNombre());
                } else if (defiendeAnt.getVida() > defiendeProx.getVida()) {
                    System.out.println(String.format("El pokemon %s ha sido dañado en %d cantidad de vida.\n", defiendeProx.getNombre(), defiendeAnt.getVida() - defiendeProx.getVida()));
                } else if (defiendeAnt.getAtaque() > defiendeProx.getAtaque()) {
                    System.out.printf("Se le ha disminuido el ataque al Pokemon %s.\n", defiendeProx.getNombre());
                } else if (defiendeAnt.getDefensa() > defiendeProx.getDefensa()) {
                    System.out.printf("Se le ha disminuido la defensa al Pokemon %s.\n", defiendeProx.getNombre());
                } else if (defiendeAnt.getNivel() > defiendeProx.getNivel()) {
                    System.out.println(String.format("El pokemon %s ha Involucionado.\n", defiendeProx.getNombre()));
                } else if (defiendeAnt.getEstados() != defiendeProx.getEstados()) {
                    List<Estado> estadosList = defiendeProx.getEstados().stream().filter(e -> (!defiendeAnt.getEstados().contains(e))).toList();
                    Estado estado;
                    if (estadosList.size() > 0) {
                        estado = estadosList.get(0);
                        System.out.printf("Se ha afectado al Pokemon %s.\n\tNuevo Estado: %s\n", defiendeProx.getNombre(), estado.toString());
                    } else {
                        estado = defiendeProx.getEstados().get(0);
                        System.out.printf("El Pokemon %s se encuentra en estado %s\n", defiendeProx.getNombre(), estado.toString());
                    }

                }
            } else {
                System.out.println(String.format("El pokemon %s ha modificado el Clima\n", atacaProx.getNombre()));
            }
        }
    }

    public static void mostrarAccionDeBatallaItem(Pokemon pokemonAnt, Pokemon pokemonProx, AccionDeBatalla accionDeBatalla) {
      if (accionDeBatalla.isSeUso()) {
            if (pokemonAnt.estaMuerto() && pokemonProx.estaNormal()) {
                System.out.println(String.format("Se ha revivido exitosamente al Pokemon %s, de vuelta a la batalla!\n", pokemonProx.getNombre()));
            } else if (!pokemonAnt.estaNormal() && pokemonProx.estaNormal()) {
                System.out.println(String.format("El pokemon: %s satisfactoriamente volvio al estado %s\n", pokemonProx.getNombre(), Estado.NORMAL));
            } else if (pokemonAnt.getVida() < pokemonProx.getVida()) {
                System.out.println(String.format("Se ha curado exitosamente al Pokemon %s", pokemonProx.getNombre()));
            } else {
                System.out.println(String.format("Se utilizo el %s sobre el Pokemon %s\n", accionDeBatalla.getItemUsado().getNombre(), pokemonProx.getNombre()));
            }
        } else {
                if (pokemonProx.estaMuerto()) {
                    System.out.println(String.format("El item %s no puede aplicarse\nEl pokemon %s esta muerto", accionDeBatalla.getItemUsado().getNombre(), pokemonProx.getNombre()));
                } else if (pokemonProx.estaNormal()) {
                    System.out.println(String.format("El item %s no puede aplicarse\nEl pokemon %s se encuentra en estado normal\n", accionDeBatalla.getItemUsado().getNombre(), pokemonProx.getNombre()));
                }
            }
        }
}