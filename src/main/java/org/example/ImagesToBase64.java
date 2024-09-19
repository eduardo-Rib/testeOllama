package org.example;

import java.io.*;

import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class ImagesToBase64 {
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
