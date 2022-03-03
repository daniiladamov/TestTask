package com.adamovdv.testtask.countrydirectory.controllers;

import com.adamovdv.testtask.countrydirectory.services.CountryService;
import lombok.AllArgsConstructor;
import com.adamovdv.testtask.countrydirectory.models.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class CountryController {

    private CountryService countryService;

@GetMapping("/list")
    public List<Country> getCountries(){
    return countryService.getList();
}

@PostMapping("/create")
    public ResponseEntity createCountry(@RequestBody Country country){
    Country countryCreate=countryService.create(country);

    if (countryCreate !=null)
        return ResponseEntity.ok("Operation is done \n"+countryCreate);
    else
        return ResponseEntity.badRequest().body("The country is already in the DB with id="
        +countryService.getCountry(country.getName().toLowerCase()).getId());
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCountry(@PathVariable Long id){
    if (countryService.delete(id))
        return ResponseEntity.ok("The country with id="+id+" was deleted");
    else
        return ResponseEntity.notFound().build();
}

@GetMapping("/country/{id}")
    public ResponseEntity getCountry(@PathVariable Long id){
    Country country=countryService.getCountry(id);
    if (country!=null)
        return ResponseEntity.ok(country);
    else
        return ResponseEntity.notFound().build();
}



}
