package com.CountryServices.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CountryServices.demo.beans.Country;
import com.CountryServices.demo.services.CountryService;

//this means whenever u send a request, it should come to this controller class
@RestController
public class CountryController {
	
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries(){
		
		//return countryService.getAllCountries();
		try {
			List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	//using @PathVariable bcuz it's a path parameter
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountriesById(@PathVariable(value="id") int id){
		
		//return countryService.getCountrybyID(id);
		
		try {
			Country country = countryService.getCountrybyID(id);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	//using @RequestParam bcuz it's a query parameter
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName){
		
		//return countryService.getCountrybyName(countryName);
		try {
			Country country = countryService.getCountrybyName(countryName);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		
		//return countryService.addCountry(country);
		try {
			country = countryService.addCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.CREATED);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id,@RequestBody Country country) {
		
		//return countryService.updateCountry(country);
		try {
			Country existCountry = countryService.getCountrybyID(id);
			
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapital(country.getCountryCapital());
			
			Country updated_country = countryService.updateCountry(existCountry);
			return new ResponseEntity<Country>(updated_country, HttpStatus.OK);
			
		}
		catch(Exception e) {
			return new ResponseEntity<Country>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable(value="id") int id) {
		
		//return countryService.deleteCountry(id);
		
		Country country = null;
		try {
			country = countryService.getCountrybyID(id);
			countryService.deleteCountry(country);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
		
		
	}
}
