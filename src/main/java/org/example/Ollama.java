package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ContentType;

import java.io.File;

public class Ollama {

    public static String GetResponse(String nomeModelo, String prompt, String img1Base64) throws Exception {
        // Cria o cliente HTTP
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // URL da API local
            String apiUrl = "http://localhost:11434/api/chat";

            // Converter as imagens para Base64
            //String img1Base64 = ImagesToBase64.encodeImageToBase64(image1);
            //String img2Base64 = encodeImageToBase64(image2);

            // Monta o corpo do JSON
            String body = String.format("{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\", \"images\": [\"%s\"]}], \"stream\": false}",
                    nomeModelo, prompt, img1Base64);

            // Criação da requisição POST
            HttpPost postRequest = new HttpPost(apiUrl);

            // Define o corpo da requisição como JSON
            StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            postRequest.setEntity(entity);

            // Envia a requisição
            HttpResponse response = httpClient.execute(postRequest);

            // Captura a resposta
            String responseString = EntityUtils.toString(response.getEntity());
            System.out.println("Status Code: " + response.getStatusLine().getStatusCode());

            return (responseString);
        }
    }
}
