package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final String ARQUIVO = "log.txt";

    public static void registrar(String mensagem) {
        try (FileWriter writer = new FileWriter(ARQUIVO, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            writer.write("[" + timestamp + "] " + mensagem + "\n");
        } catch (IOException e) {
            System.out.println(" Erro ao registrar log: " + e.getMessage());
        }
    }
}