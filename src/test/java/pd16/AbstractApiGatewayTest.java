package pd16;

import org.junit.jupiter.api.Test;
import pd16.service.AbstractApiGateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractApiGatewayTest {
    private static class TestGateway extends AbstractApiGateway {
        TestGateway() {
            super(null, null);
        }
        String buildQuery(String... params) {
            return buildQueryParamsFrom(params);
        }
    }

    private final TestGateway gateway = new TestGateway();

    @Test
    void shouldBuildQueryParamsFromKeyValuePairs() {
        String result = gateway.buildQuery(
                "latitude", "52.2297",
                "longitude", "21.0122"
        );
        assertEquals("latitude=52.2297&longitude=21.0122", result);
    }

    @Test
    void shouldEncodeSpecialCharacters() {
        String result = gateway.buildQuery(
                "city", "New York"
        );
        assertEquals("city=New+York", result);
    }

    @Test
    void shouldThrowExceptionForOddNumberOfParams() {
        assertThrows(
                IllegalArgumentException.class,
                () -> gateway.buildQuery("latitude", "52.2297", "longitude")
        );
    }
}
