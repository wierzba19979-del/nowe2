package pd16;

import org.junit.jupiter.api.Test;
import pd16.exception.ApiException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        String apiName = "Open-Meteo";
        int statusCode = 404;
        String responseBody = "Not Found";

        ApiException exception = new ApiException(apiName, statusCode, responseBody);

        assertEquals("Open-Meteo error: HTTP 404, response: Not Found", exception.getMessage());
    }
}
