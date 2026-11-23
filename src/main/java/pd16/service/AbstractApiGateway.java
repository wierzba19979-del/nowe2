package pd16.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public abstract class AbstractApiGateway {
    protected final HttpClient httpClient;
    protected final ObjectMapper objectMapper;

    protected AbstractApiGateway(
            HttpClient httpClient,
            ObjectMapper objectMapper
    ) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    private String executeGet(String url, String apiName)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        if (response.statusCode() != 200) {
            throw new ApiException(apiName, response.statusCode(), response.body());
        }
        return response.body();
    }

    protected <T> T sendGet(
            String url,
            String apiName,
            Class<T> clazz
    ) throws IOException, InterruptedException {
        String responseBody = executeGet(url, apiName);
        return objectMapper.readValue(responseBody, clazz);
    }

    protected <T> T sendGet(
            String url,
            String apiName,
            TypeReference<T> typeReference
    ) throws IOException, InterruptedException {
        String responseBody = executeGet(url, apiName);
        return objectMapper.readValue(responseBody, typeReference);
    }

    protected String buildQueryParamsFrom(String... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Query parameters must be provided in key-value pairs");
        }
        StringJoiner query = new StringJoiner("&");
        for (int i = 0; i < params.length; i += 2) {
            String key = URLEncoder.encode(params[i], StandardCharsets.UTF_8);
            String value = URLEncoder.encode(params[i + 1], StandardCharsets.UTF_8);
            query.add(key + "=" + value);
        }
        return query.toString();
    }
}
