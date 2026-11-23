package pd16.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    @FunctionalInterface
    protected interface JsonParser<T> {
        T parse(String responseBody) throws IOException;
    }

    protected <T> T sendGet(
            String url,
            String apiName,
            JsonParser<T> parser
    ) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        if (response.statusCode() != 200) {
            throw new ApiException(apiName, response.statusCode());
        }
        return parser.parse(response.body());
    }
}
