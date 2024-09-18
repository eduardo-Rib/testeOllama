package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class gerarTXT {
    public static void gerarTexto(String response) throws IOException {
        try {
            File arquivo = new File("C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\api\\testeOllama\\respostaOllama.txt");

            arquivo.createNewFile();
            System.out.print("Arquivo criado com sucesso!");

            FileWriter arq = new FileWriter("C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\api\\testeOllama\\respostaOllama.txt");
            PrintWriter gravarArquivo = new PrintWriter(arq);

            gravarArquivo.printf(response);

            gravarArquivo.close();

        } finally {
            System.out.print("\nResposta salva com sucesso!\nNo caminho: C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\api\\testeOllama\\respostaOllama.txt");
        }

    }
}