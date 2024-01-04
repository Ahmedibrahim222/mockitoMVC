package com.CountryServices.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CountryServices.demo.beans.Country;

public interface CountryRepository extends JpaRepository<Country,Integer>{

}
