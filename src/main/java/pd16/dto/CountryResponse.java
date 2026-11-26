package pd16.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CountryResponse(
        Name name,
        List<String> capital,
        String region,
        String cca2,
        long population
) {
    public record Name(
            String common
    ) {}
}
