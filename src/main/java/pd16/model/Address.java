package pd16.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Address(
        String street,
        String city,
        @JsonProperty("geo") Point point
) {
}
