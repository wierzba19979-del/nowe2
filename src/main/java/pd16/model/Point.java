package pd16.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Point(
        @JsonProperty("lat") double latitude,
        @JsonProperty("lng") double longitude
) {}