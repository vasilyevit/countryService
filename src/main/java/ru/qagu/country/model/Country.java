package ru.qagu.country.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.qagu.country.data.CountryEntity;

public record Country(@JsonProperty("country_name") String countryName,
                      @JsonProperty("country_code") String countryCode) {

    public static Country fromEntity(CountryEntity entity) {
        return new Country(entity.getCountryName(), entity.getCountryCode());
    }
}
