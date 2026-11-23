package pd16.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.dto.CountryResponse;
import pd16.model.Country;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

public class CountryGateway extends AbstractApiGateway {
    private static final String BASE_URL = "https://restcountries.com/v3.1/name";

    public CountryGateway(HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient, objectMapper);
    }

    public Country getCountry(String countryName) throws IOException, InterruptedException {
        String url = BASE_URL + "/" + countryName;
        List<CountryResponse> countries = sendGet(
                url,
                "REST Countries",
                body -> objectMapper.readValue(
                        body,
                        objectMapper.getTypeFactory()
                                .constructCollectionType(
                                        List.class,
                                        CountryResponse.class
                                )
                )
        );
        if (countries.isEmpty()) {
            throw new IOException("Country not found: " + countryName);
        }
        CountryResponse data = countries.get(0);
        String capital = data.capital() == null
                || data.capital().isEmpty()
                ? null
                : data.capital().get(0);
        return new Country(
                data.name().common(),
                capital,
                data.region(),
                data.cca2(),
                data.population()
        );
    }
}
