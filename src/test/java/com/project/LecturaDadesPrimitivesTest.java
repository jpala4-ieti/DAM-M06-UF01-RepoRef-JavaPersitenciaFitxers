package com.project;

import com.project.estructuresdades.Objecte;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LecturaDadesPrimitivesTest {

    @Test
    void testLecturaDadesPrimitives() {
        try {
            // Crear un directori temporal
            Path directoriTemporal = Files.createTempDirectory("testData");
            Path camiFitxer = directoriTemporal.resolve("ArxiuEscriuPrimitivesTest.dat");

            // Escriure dades primitives i un objecte serialitzable en un fitxer binari
            try (FileOutputStream fos = new FileOutputStream(camiFitxer.toFile());
                 DataOutputStream dos = new DataOutputStream(fos)) {

                dos.writeUTF("Hola món");
                dos.writeInt(123);
                dos.writeBoolean(true);
                dos.writeChar('A');
                dos.writeDouble(3.14159);

                // Crear un objecte d'exemple i serialitzar-lo
                Objecte obj = new Objecte("Exemple", "Prova");
                byte[] serializedObject;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                    oos.writeObject(obj);
                    serializedObject = baos.toByteArray();
                }

                // Escriure l'objecte serialitzat al fitxer
                dos.writeInt(serializedObject.length);
                dos.write(serializedObject);
            }

            // Llegir el fitxer creat i validar les dades
            LecturaDadesPrimitives.llegirIFerMostra(camiFitxer.toString());

        } catch (IOException e) {
            fail("El test no hauria de fallar amb excepció: " + e.getMessage());
        }
    }
}
