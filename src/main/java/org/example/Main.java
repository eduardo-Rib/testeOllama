package org.example;

import java.io.*;
import java.util.Scanner;

//https://github.com/ollama/ollama/blob/main/docs/api.md#generate-a-completion

//prompt: Collect from the image the set of numbers present after the acronym CPF
//local: C:\Users\eduar\OneDrive\Área de Trabalho\api\documentos\rg verso.jpeg

public class Main {
    public static void main(String[] args) throws Exception {
        //Define o modelo que será usado
        //String nomeModelo = "llava";
        String nomeModelo = "minicpm-v";


        String img = ImagesToBase64.encodeImageToBase64(new File("C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\api\\documentos\\rg verso.jpeg"));
        //Define o prompt que será enviado para o modelo


        //String promp = lerPromp("prompt: ");

        //Define o local da imagem a ser analisada
        //Converte a imagem para para Base64Encoded
        //String imagem =ImagesToBase64.convertImageToBase64(lerPromp("images: "));

        String prompt = "what is in this image?";
        String prompt2 = "Extract the set of 11 numbers that are present after the word CPF";
        String prompt3 = "Extract the set of 11 numbers that are present in the upper left corner, these numbers are preceded by the word CPF";
        String prompt4 = "Extract the 11 CPF numbers in the upper left corner of the image";
        String prompt6 = "Extract the set of 9 numbers that are present after the word 'Registro Geral'";
        String prompt5 = "Extract all text from image"; //Não é um bom prompt
        String prompt7 = "extract the sets of numbers present after the words CPF and RG";

        System.out.print("Carregando...\n");
        String responseText = Ollama.GetResponse(nomeModelo, prompt7, img);

        GerarTXT.gerarTexto(responseText);
    }

    public static String lerPromp(String pergunta){
        Scanner scan = new Scanner(System.in);
        System.out.print(pergunta);
        return scan.nextLine();
    }
}