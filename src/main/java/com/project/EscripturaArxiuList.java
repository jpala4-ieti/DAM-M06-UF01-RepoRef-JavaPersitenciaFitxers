package com.project;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class EscripturaArxiuList {

    public static void escriureArxiu(List<String> linies, String filePath) throws IOException {
        // Crear la carpeta si no existeix
        File dir = new File(filePath).getParentFile();
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Error en la creació de la carpeta " + dir.getPath());
        }

        // Escriure en l'arxiu
        Path out = Paths.get(filePath);
        Files.write(out, linies, Charset.defaultCharset());
    }

    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "ArxiuEscriu.txt";

        // Crear la carpeta 'data' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
                return;
            }
        }

        try {
            List<String> linies = new ArrayList<>();
            linies.add("Del xoc i la confusió apareixen les pors,");
            linies.add("perills i destruccions inapreciables per la");
            linies.add("majoria de la gent, per sectors específics");
            linies.add("de la societat i la majoria de governants.");
            linies.add("La natura, a través d'huracans, terratrèmols,");
            linies.add("fam i pandèmies genera xoc i confusió.");

            // Utilitzar el mètode escriureArxiu per escriure el contingut al fitxer
            escriureArxiu(linies, filePath);

            System.out.println("Llest");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
