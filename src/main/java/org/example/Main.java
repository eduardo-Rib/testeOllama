package org.example;

import java.io.*;
import org.json.JSONObject;

//https://github.com/ollama/ollama/blob/main/docs/api.md#generate-a-completion

public class Main {
    public static void main(String[] args) throws Exception {
        //Define o modelo que será usado
        String modelo = "minicpm-v";

        String frente = ImagesToBase64.encodeImageToBase64(new File("C:\\Users\\Eduardo Ribeiro\\Desktop\\api\\rg frente.jpeg"));
        String verso = ImagesToBase64.encodeImageToBase64(new File("C:\\Users\\Eduardo Ribeiro\\Desktop\\api\\rg verso.jpeg"));

        System.out.print("Carregando...\n");
        String cpf = Formatacao.getContent(Ollama.GetResponse(modelo, "return the set of 11 numbers that are present after the word CPF", verso));
        String registroGeral = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the set of 9 numbers that are present after the word 'Registro Geral'", verso));
        String dataExpedicao = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the date of issue after the word 'Data de Expedição'", verso));
        String nome = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the name present after the word 'nome'", frente));
        String filiacao = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the two names present after the word 'filiação'", frente));
        String orgaoExpeditor = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the issuing authority below the word Orgão 'expeditor'", frente));
        String dataNascimento = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the date of birth below the word 'Data de nascimento'", frente));
        String naturalidade = Formatacao.getContent(Ollama.GetResponse(modelo, "return only the nationality below the word 'Naturalidade'", frente));

        String[]arrayFiliacao = filiacao.split(",");

        RG rg = new RG(cpf, registroGeral, dataExpedicao, nome, arrayFiliacao[0], arrayFiliacao[1], orgaoExpeditor, dataNascimento, naturalidade);
        BancoDados.saveToDatabase(rg);
        rg.imprimir();
    }
}