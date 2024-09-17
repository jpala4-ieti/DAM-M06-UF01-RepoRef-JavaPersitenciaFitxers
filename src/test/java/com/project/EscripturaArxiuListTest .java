package com.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class EscripturaArxiuListTest {

    @Test
    void testEscripturaArxiu() {
        // Preparació
        String basePath = System.getProperty("java.io.tmpdir") + "/testData/";
        String filePath = basePath + "ArxiuEscriuTest.txt";

        List<String> linies = Arrays.asList(
            "Del xoc i la confusió apareixen les pors,",
            "perills i destruccions inapreciables per la",
            "majoria de la gent, per sectors específics",
            "de la societat i la majoria de governants.",
            "La natura, a través d'huracans, terratrèmols,",
            "fam i pandèmies genera xoc i confusió."
        );

        try {
            // Executar la funció
            EscripturaArxiuList.escriureArxiu(linies, filePath);

            // Comprovar si l'arxiu existeix
            File file = new File(filePath);
            assertTrue(file.exists(), "L'arxiu hauria d'existir");

            // Comprovar el contingut de l'arxiu
            List<String> llegides = Files.readAllLines(Path.of(filePath));
            assertEquals(linies, llegides, "Les línies haurien de coincidir");

        } catch (IOException e) {
            fail("No hauria de fallar amb excepció: " + e.getMessage());
        } finally {
            // Esborrar el fitxer després del test
            new File(filePath).delete();
        }
    }
}
