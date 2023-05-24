package com.test.bancolombia.contry.services;

import com.test.bancolombia.contry.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Service
public class CountryService {
    @Autowired
    private  LogService logService;
    @Value("https://restcountries.com/v3.1/all")
    private String apiUrlAll;
    @Value("${api.url}")
    private String apiUrl;

    public String getCountries(){
        RestTemplate restTemplate = new RestTemplate();
        logService.saveLog("todos los paises", apiUrlAll);
        String response = restTemplate.getForObject(apiUrlAll, String.class);
        return response;
    }

    public Country getCountryByName(@PathVariable String name){
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + name + "?fullText=true";

        try {
            logService.saveLog(name, url);
            Country[] countries = restTemplate.getForObject(url, Country[].class);

            if (countries != null && countries.length > 0) {
                return countries[0];
            }
        }catch (HttpClientErrorException.NotFound ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
