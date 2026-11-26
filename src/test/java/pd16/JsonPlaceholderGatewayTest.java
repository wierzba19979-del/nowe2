package pd16;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import pd16.service.JsonPlaceholderGateway;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonPlaceholderGatewayTest {

    @SneakyThrows
    @Test
    void shouldReturnOnlyIncompleteTodos() {
        HttpClient httpClient = mock(HttpClient.class);
        ObjectMapper objectMapper = new ObjectMapper();
        HttpResponse<String> response = mock(HttpResponse.class);
        String json = """
                [
                    {"userId": 1, "id": 1, "title": "Task 1", "completed": false},
                    {"userId": 1, "id": 2, "title": "Task 2", "completed": true}
                ]
                """;
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn(json);
        JsonPlaceholderGateway gateway = new JsonPlaceholderGateway(httpClient, objectMapper);
        var todos = gateway.getTodos(1L);
        assertEquals(1, todos.size());
        assertFalse(todos.get(0).isCompleted());
        assertEquals("Task 1", todos.get(0).getTitle());
    }
}
