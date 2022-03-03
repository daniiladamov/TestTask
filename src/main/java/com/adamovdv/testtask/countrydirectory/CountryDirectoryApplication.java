package com.adamovdv.testtask.countrydirectory;

import com.adamovdv.testtask.countrydirectory.models.Country;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MappedTypes(Country.class)
@MapperScan("com.adamovdv.testtask.countrydirectory.mappers")
@SpringBootApplication
public class CountryDirectoryApplication {

    public static void main(String[] args){
    SpringApplication.run(CountryDirectoryApplication.class, args);

    }

}
