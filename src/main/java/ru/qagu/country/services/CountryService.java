package ru.qagu.country.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.qagu.country.data.CountryEntity;
import ru.qagu.country.data.CountryRepository;
import ru.qagu.country.model.Country;
import ru.qagu.country.model.UpdateRequest;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountry() {
        return countryRepository.findAll()
                .stream()
                .map(Country::fromEntity)
                .toList();
    }

    public Country addCountry(Country country) {
        CountryEntity entity = CountryEntity.fromJson(country);
        countryRepository.save(entity);
        return country;
    }

    public Country updateCountry(CountryEntity entity, UpdateRequest updateRequest) {
        if (updateRequest.getCountryName() != null) {
            entity.setCountryName(updateRequest.getCountryName());
        }
        if (updateRequest.getCountryCode() != null) {
            entity.setCountryCode(updateRequest.getCountryCode());
        }
        countryRepository.save(entity);
        return Country.fromEntity(entity);
    }

    public CountryEntity getCountryByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }
}
