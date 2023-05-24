package com.test.bancolombia.contry.Controllers;

import com.test.bancolombia.contry.models.Country;
import com.test.bancolombia.contry.repositoies.LogRepository;
import com.test.bancolombia.contry.services.CountryService;
import com.test.bancolombia.contry.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/countries")
public class CountryController {
    @Autowired
    private  LogRepository logRepository;
    @Autowired
    private  LogService logService;
    @Autowired
    private CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<?> getCountries(){
        String response = countryService.getCountries();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getCountryByName(@PathVariable String name){
        Country country = countryService.getCountryByName(name);

        if (country != null) {
            Country countryInfo = new Country();
            countryInfo.setArea(country.getArea());
            countryInfo.setPopulation(country.getPopulation());
            return ResponseEntity.ok(countryInfo);
        }else{
            String msg = "El país '" + name + "' no fue encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }

    }
}