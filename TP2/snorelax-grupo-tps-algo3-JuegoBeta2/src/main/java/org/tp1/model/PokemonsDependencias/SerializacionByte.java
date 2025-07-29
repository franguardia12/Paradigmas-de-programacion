package org.tp1.model.PokemonsDependencias;

import org.tp1.model.PokemonsDependencias.Pokemon;

import java.io.*;

public class SerializacionByte {

    public SerializacionByte() {
    }

    public static byte[] serializar(Pokemon pokemon) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(pokemon);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pokemon deserializar(byte[] datosSerializados) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(datosSerializados);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Pokemon) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
