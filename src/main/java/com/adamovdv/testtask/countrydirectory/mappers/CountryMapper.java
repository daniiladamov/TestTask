package com.adamovdv.testtask.countrydirectory.mappers;

import com.adamovdv.testtask.countrydirectory.models.Country;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountryMapper {

    @Select("select * from Countries")
    List<Country> findAll();

    @Insert("insert into Countries (name, shorty) values(#{name}, #{shorty})")
    void create(Country country);

    @Select("select * from Countries where name=#{name}")
    Country findByName(String name);

    @Select("select * from Countries where id=#{id}")
    Country findById(Long id);

    @Delete("delete from Countries where id=#{id}")
    void delete(Long id);


}
