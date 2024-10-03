package org.example;

import java.io.*;
import org.json.JSONObject;

//https://github.com/ollama/ollama/blob/main/docs/api.md#generate-a-completion

public class Main {
    public static void main(String[] args) throws Exception {
        //Define o modelo que será usado
        String modelo = "minicpm-v";

        String frente = ImagesToBase64.encodeImageToBase64(new File("C:\\Users\\aluno\\Desktop\\api\\rg frente.jpeg"));
        String verso = ImagesToBase64.encodeImageToBase64(new File("C:\\Users\\aluno\\Desktop\\api\\rg verso.jpeg"));

        System.out.print("Carregando...\n");
        String cpf = getContent(Ollama.GetResponse(modelo, "return the set of 11 numbers that are present after the word CPF", verso));
        String registroGeral = getContent(Ollama.GetResponse(modelo, "return only the set of 9 numbers that are present after the word 'Registro Geral'", verso));
        String dataExpedicao = getContent(Ollama.GetResponse(modelo, "return only the date of issue after the word 'Data de Expedição'", verso));
        String nome = getContent(Ollama.GetResponse(modelo, "return only the name present after the word 'nome'", frente));
        String filiacao = getContent(Ollama.GetResponse(modelo, "return only the two names present after the word 'filiação'", frente));
        String orgaoExpeditor = getContent(Ollama.GetResponse(modelo, "return only the issuing authority below the word Orgão 'expeditor'", frente));
        String dataNascimento = getContent(Ollama.GetResponse(modelo, "return only the date of birth below the word 'Data de nascimento'", frente));
        String naturalidade = getContent(Ollama.GetResponse(modelo, "return only the nationality below the word 'Naturalidade'", frente));

        String[]arrayFiliacao = filiacao.split(",");

        RG rg = new RG(cpf, registroGeral, dataExpedicao, nome, arrayFiliacao[0], arrayFiliacao[1], orgaoExpeditor, dataNascimento, naturalidade);
        rg.imprimir();

    }

    public static String getContent(String response) {

        JSONObject jsonObject = new JSONObject(response);
        String content = jsonObject.getJSONObject("message").getString("content");

        return content;
    }

}