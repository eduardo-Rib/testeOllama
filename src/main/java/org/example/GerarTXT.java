package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class GerarTXT {
    public static void gerarTexto(String response) throws IOException {
        try {
            File arquivo = new File("C:\\Users\\aluno\\Desktop\\api\\testeOllama\\respostaOllama.txt");

            arquivo.createNewFile();
            System.out.print("Arquivo criado com sucesso!");

            FileWriter arq = new FileWriter("C:\\Users\\aluno\\Desktop\\api\\testeOllama\\respostaOllama.txt");
            PrintWriter gravarArquivo = new PrintWriter(arq);

            gravarArquivo.printf(response);

            gravarArquivo.close();

        } finally {
            System.out.print("\nResposta salva com sucesso!\nC:\\Users\\aluno\\Desktop\\api\\testeOllama\\respostaOllama.txt");
        }

    }
}