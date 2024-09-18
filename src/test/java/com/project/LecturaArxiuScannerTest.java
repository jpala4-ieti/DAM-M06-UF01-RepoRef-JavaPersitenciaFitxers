package com.project;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LecturaArxiuScannerTest {

    @Test
    void testLecturaArxiu() {
        // Utilitzar directori temporal per crear fitxers i carpetes
        Path directoriTemporal = null;
        try {
            directoriTemporal = Files.createTempDirectory("testData");
            String nomFitxer = "ArxiuTestLectura.txt";
            Path camiFitxer = Paths.get(directoriTemporal.toString(), nomFitxer);

            // Escriure contingut al fitxer de prova amb codificació UTF-8
            List<String> contingutEsperat = List.of(
                "Primera línia.",
                "Segona línia.",
                "Tercera línia amb caràcters especials: àèíòù"
            );
            Files.write(camiFitxer, contingutEsperat, StandardCharsets.UTF_8);

            // Cridar al mètode que llegeix i mostra el fitxer
            LecturaArxiuScanner.llegirIMostrarFitxer(camiFitxer.toString());

            // Llegir el contingut del fitxer per assegurar que s'ha llegit correctament
            List<String> contingutLlistat = Files.readAllLines(camiFitxer, StandardCharsets.UTF_8);
            assertEquals(contingutEsperat, contingutLlistat, "El contingut llegit hauria de coincidir amb el contingut esperat");

        } catch (IOException e) {
            fail("No hauria de fallar amb una excepció: " + e.getMessage());
        } finally {
            // Neteja: Esborrar el directori i fitxers després del test
            try {
                if (directoriTemporal != null) {
                    Files.walk(directoriTemporal)
                            .map(Path::toFile)
                            .forEach(File::delete);
                }
            } catch (IOException e) {
                System.out.println("Error esborrant el directori temporal: " + e.getMessage());
            }
        }
    }
}
