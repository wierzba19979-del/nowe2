package pd16.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Geo(
        @JsonProperty("lat") String latitude,
        @JsonProperty("lng") String longitude
) {}
