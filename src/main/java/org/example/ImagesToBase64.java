package org.example;

import java.io.*;

import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class ImagesToBase64 {
    // Função para converter uma imagem em Base64
    public static String encodeImageToBase64(File imageFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(imageFile);
        byte[] bytes = new byte[(int) imageFile.length()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String convertImageToBase64(String imagePath) throws IOException {
        String base64Image = "";

        try{
            // Carrega a imagem em um array de bytes
            byte[] imageBytes = Files.readAllBytes(Path.of(imagePath));

            // Codifica os bytes em base64
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
        }catch (IOException e) {
            e.printStackTrace();
        }

        //Retorna a imagem codificada em base64
        return base64Image;
    }
}
