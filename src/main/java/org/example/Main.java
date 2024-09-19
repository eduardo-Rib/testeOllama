package org.example;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

//https://github.com/ollama/ollama/blob/main/docs/api.md#generate-a-completion

//prompt: Collect from the image the set of numbers present after the acronym CPF
//local: C:\Users\eduar\OneDrive\Área de Trabalho\api\documentos\rg verso.jpeg

public class Main {
    public static void main(String[] args) throws Exception {
        //Define o modelo que será usado
        String nomeModelo = "llava";

        String img = ImagesToBase64.encodeImageToBase64(new File("C:\\rg.jpeg"));
        //Define o prompt que será enviado para o modelo

        String promp = lerPromp("prompt: ");

        //Define o local da imagem a ser analisada
        //Converte a imagem para para Base64Encoded
        //String imagem =ImagesToBase64.convertImageToBase64(lerPromp("images: "));

        String prompt = "what is in this image?";

        String responseText = Ollama.GetResponse(nomeModelo, promp, img);

        GerarTXT.gerarTexto(responseText);
    }

    public static String lerPromp(String pergunta){
        Scanner scan = new Scanner(System.in);
        System.out.print(pergunta);
        return scan.nextLine();
    }
}