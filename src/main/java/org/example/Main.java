package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

//https://github.com/ollama/ollama/blob/main/docs/api.md#generate-a-completion
//local: C:\Users\eduar\OneDrive\Área de Trabalho\api\documentos

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

        //Define o cabeçalho da requisição (header)
        //Content-Type é o nome do cabeçalho que define o tipo da requisição
        //application/json; define o tipo do do arquivo que será enviado oara o servidor
        //utf-8 define o charset que esta sendo utilizado para codificar conteúdo da requisição
        conexao.setRequestProperty("Content-Type", "application/json; utf-8");

        //Accept é o nome do cabeçalho que define o tipo do retorno da requição
        //application/json define que será retornado um arquivo JSON
        conexao.setRequestProperty("Accept", "application/json");

        //setDoOutput(true) stá dizendo que pretende enviar dados na requisição HTTP
        // ou seja, que o corpo (body) da requisição terá conteúdo
        //É necessário quando você deseja fazer requisições que envolvem o envio de dados (POST)
        conexao.setDoOutput(true);

        //Criando o corpo (body) da requisição
        //Usando o String.format() para formatar a string, substintuindo os %s pelas variáveis
        String body = String.format(
                "{\"model\": \"%s\", \"prompt\":\"%s\", \"images\": \"%s\", \"stream\": false}", nomeModelo, promp, imagem
        );

        //O OutputStream permite enviar dados no corpo da requisição para o servidor
        try(OutputStream os = conexao.getOutputStream()){
            //O metodo OutputStream trabalha diretamente com bytes
            //getBytes converte o body para um array de bytes
            //StandardCharsets.UTF-8 faz os caracteres serem corretamente convertidos para bytes no formato UTF-8
            byte[] input = body.getBytes(StandardCharsets.UTF_8);

            //os.write() é usado para escrever os bytes no OutputStream
            //oO seja, para enviar o conteúdo da variável body no corpo da requisição HTTP
            //É passado o input (body), ponto inicial do array (0) e o ponto final(input.length)
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

        GerarTXT.gerarTexto(responseText);

        conexao.disconnect();
    }

    public static String lerPromp(String pergunta){
        Scanner scan = new Scanner(System.in);
        System.out.print(pergunta);
        return scan.nextLine();
    }
}