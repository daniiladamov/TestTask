package com.adamovdv.testtask.countrydirectory.services;

import com.adamovdv.testtask.countrydirectory.mappers.CountryMapper;
import com.adamovdv.testtask.countrydirectory.models.Country;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {

    CountryMapper countryMapper;

    public List<Country> getList() {
        return countryMapper.findAll();
    }

    public Country create(Country country){
        Country countryFromDB=countryMapper.findByName(country.getName().toLowerCase());
        if (countryFromDB!=null) return null;
        else {
            if (country.getShorty().length()>3) country.setShorty(country.getShorty().substring(0,3).toUpperCase());
            else country.setShorty(country.getShorty().toUpperCase());
            country.setName(country.getName().toLowerCase());
            countryMapper.create(country);
            countryFromDB=countryMapper.findByName(country.getName());
            return countryFromDB;

        }
    }
    @CacheEvict(value=  "countryCache", key="#id")
    public boolean delete(Long id) {
        Country countryFromDB=countryMapper.findById(id);
        if (countryFromDB==null) return false;
        else {
            countryMapper.delete(id);
            return true;
        }
    }

    @Cacheable(value= "countryCache", key = "#id")
    public Country getCountry(Long id)  {
        return countryMapper.findById(id);
    }

    public Country getCountry(String name)  { return countryMapper.findByName(name);}


}
