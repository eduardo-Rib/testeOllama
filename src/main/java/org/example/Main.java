package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Define o modelo que será usado
        String nomeModelo = "llava";
        //Define o prompt que será enviado para o modelo
        String promp = lerPromp("prompt: ");
        String imagem = lerPromp("images: ");

        //Especifica o local da api do ollama
        URL url = new URL("http://localhost:11434/api/generate -d");
        //Abre a conexão com a api do ollama
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        //Define o metodo de requisição como POST
        conexao.setRequestMethod("POST");

        //Define o cabeçalho da requisição
        //Content-Type é o nome do cabeçalho
        //application/
        conexao.setRequestProperty("Content-Type", "application/json; utf-8");

        conexao.setRequestProperty("Accept", "application/json");
        conexao.setDoOutput(true); //


        String jsonInputString = String.format(
                "{\"model\": \"%s\", \"prompt\":\"%s\", \"images\": \"%s\", \"stream\": false}", nomeModelo, promp, imagem
        );


        try(OutputStream os = conexao.getOutputStream()){
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }


        int codigio = conexao.getResponseCode();
        System.out.println("Response code = " + codigio);

        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String linha;
        while ((linha = in.readLine()) != null){
            response.append(linha);
        }
        in.close();

        System.out.println("Response body: " + response.toString());

        JSONObject jsonResponse = new JSONObject(response.toString());
        String responseText = jsonResponse.getString("response");

        gerarTXT.gerarTexto(responseText);

        conexao.disconnect();
    }

    public static String lerPromp(String pergunta){
        Scanner scan = new Scanner(System.in);
        System.out.print(pergunta);
        return scan.nextLine();
        //local: C:\Users\eduar\OneDrive\Área de Trabalho\api\documentos
    }
}