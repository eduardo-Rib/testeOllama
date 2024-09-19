package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class ImagesToBase64 {

    public static void main(String[] args) throws IOException {
        String apiUrl = "http://localhost:11434/api/generate";
        String prompt = "extract text from image";
        File imageFile = new File("C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\api\\documentos\\rg-frente-perto.jpeg");

        uploadPromptAndImage(apiUrl, prompt, imageFile);
    }

    public static void uploadPromptAndImage(String apiUrl, String prompt, File imageFile) throws IOException {
        String boundary = "----Boundary" + System.currentTimeMillis();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream outputStream = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true)) {

            // Adicionando o campo do prompt (texto)
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"prompt\"\r\n\r\n");
            writer.append(prompt).append("\r\n");

            // Adicionando o campo da imagem
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"image\"; filename=\"").append(imageFile.getName()).append("\"\r\n");
            writer.append("Content-Type: ").append(Files.probeContentType(imageFile.toPath())).append("\r\n\r\n");
            writer.flush();

            Files.copy(imageFile.toPath(), outputStream);
            outputStream.flush();

            writer.append("\r\n");
            writer.append("--").append(boundary).append("--\r\n");
            writer.flush();
        }

        // Ler a resposta da API
        int responseCode = connection.getResponseCode();
        System.out.println("Código de resposta da API: " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println("Resposta da API: " + response.toString());
        }
    }
}
