package com.restServices.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.CountryServices.demo.beans.Country;
import com.CountryServices.demo.controllers.CountryController;
import com.CountryServices.demo.services.CountryService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {CountryControllerTest.class})
public class CountryControllerTest {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void testGetAllCountries() {
		
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washigton"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries);
		ResponseEntity<List<Country>> res = countryController.getCountries();
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2, res.getBody().size());
	}
	
	@Test
	@Order(2)
	public void testGetCountryByID() {
		
		country = new Country(2, "USA", "Washington");
		int countryID=2;
		
		when(countryService.getCountrybyID(countryID)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountriesById(countryID);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(countryID, res.getBody().getId());
		
		
	}
	
	@Test
	@Order(3)
	public void testGetCountryByName() {
		
		country = new Country(2, "USA", "Washington");
		String countryName="USA";
		
		when(countryService.getCountrybyName(countryName)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(countryName, res.getBody().getCountryName());
		
	}
	
	@Test
	@Order(4)
	public void testAddCountry() {
		
		country = new Country(3, "Germany", "Berlin");
		when(countryService.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country, res.getBody());
		
	}
	
	@Test
	@Order(5)
	public void testUpdateCountry() {
		
		country = new Country(3, "Japan", "Tokyo");
		int countryID=3;
		
		when(countryService.getCountrybyID(countryID)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> res = countryController.updateCountry(countryID, country);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(3, res.getBody().getId());
		assertEquals("Japan", res.getBody().getCountryName());
		assertEquals("Tokyo", res.getBody().getCountryCapital());
		
	}
	
	@Test
	@Order(6)
	public void testDeleteCountry() {
		
		country = new Country(3, "Japan", "Tokyo");
		int countryID=3;
		
		when(countryService.getCountrybyID(countryID)).thenReturn(country);
		ResponseEntity<Country> res = countryController.deleteCountry(countryID);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}

}
