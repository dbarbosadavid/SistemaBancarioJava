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
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.fasterxml.jackson.core.type.TypeReference;

public class ApiClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private final String backendUrl = "https://localhost:8443/api/contas";

    public ApiClient() {
        this.objectMapper = new ObjectMapper();
        
        this.objectMapper.registerModule(new JavaTimeModule());

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

    public Usuario login(LoginRequest loginRequest) throws Exception {
        String requestBody = objectMapper.writeValueAsString(loginRequest);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(backendUrl + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Falha na requisição de login: " + response.statusCode());
        }
        return objectMapper.readValue(response.body(), Usuario.class);
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
            throw new RuntimeException(response.body());
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
            throw new RuntimeException(response.body());
        }
        return objectMapper.readValue(response.body(), OperacaoResponse.class);
    }

public void transferir(TransferenciaRequest requestData) throws Exception {
    String requestBody = objectMapper.writeValueAsString(requestData);
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(backendUrl + "/transferir"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != 200) {
        throw new RuntimeException(response.body());
    }
}

    public List<Movimento> getExtrato(Long contaId) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(backendUrl + "/" + contaId + "/extrato"))
            .header("Content-Type", "application/json")
            .GET() 
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != 200) {
        throw new RuntimeException("Falha ao buscar extrato: " + response.statusCode());
    }

    return objectMapper.readValue(response.body(), new TypeReference<List<Movimento>>() {});
}
}
