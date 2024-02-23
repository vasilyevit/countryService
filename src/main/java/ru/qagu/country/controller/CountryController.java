package ru.qagu.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.qagu.country.data.CountryEntity;
import ru.qagu.country.model.Country;
import ru.qagu.country.model.UpdateRequest;
import ru.qagu.country.services.CountryService;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country")
    public List<Country> getAllCountry() {
        return countryService.getAllCountry();
    }

    @GetMapping("/country/{countryCode}")
    public Country getCountryByCountryCode(@PathVariable String countryCode) {
        return Country.fromEntity(countryService.getCountryByCountryCode(countryCode));
    }

    @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PatchMapping("/updatecountry/{countryCode}")
    public Country updateCountry(
            @PathVariable String countryCode,
            @RequestBody UpdateRequest updateRequest
    ) {
        CountryEntity entity = countryService.getCountryByCountryCode(countryCode);
        if (entity != null) {
            return countryService.updateCountry(entity, updateRequest);
        } else throw new IllegalArgumentException("Can't find country with country code" + countryCode);
    }
}
