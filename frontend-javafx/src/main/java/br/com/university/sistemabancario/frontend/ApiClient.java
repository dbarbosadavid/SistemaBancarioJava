package br.com.university.sistemabancario.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.cert.X509Certificate;

public class ApiClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private final String backendUrl = "https://localhost:8443/api/contas";

    public ApiClient() {

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
            };
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            

            
            this.client = HttpClient.newBuilder().sslContext(sslContext).build();

        } catch (Exception e) {

            throw new RuntimeException("Não foi possível criar o contexto SSL", e);
        }
    }


    public OperacaoResponse depositar(OperacaoRequest requestData) throws Exception {
        String requestBody = objectMapper.writeValueAsString(requestData);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(backendUrl + "/depositar"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Falha na requisição: " + response.statusCode());
        }
        return objectMapper.readValue(response.body(), OperacaoResponse.class);
    }

    public OperacaoResponse sacar(OperacaoRequest requestData) throws Exception {
        String requestBody = objectMapper.writeValueAsString(requestData);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(backendUrl + "/sacar"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Falha na requisição: " + response.statusCode());
        }
        return objectMapper.readValue(response.body(), OperacaoResponse.class);
    }
}