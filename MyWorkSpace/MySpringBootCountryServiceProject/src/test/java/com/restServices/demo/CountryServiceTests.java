package com.restServices.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.CountryServices.demo.beans.Country;
import com.CountryServices.demo.repository.CountryRepository;
import com.CountryServices.demo.services.CountryService;

//step1
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {CountryServiceTests.class})
public class CountryServiceTests {
	
	//step 2 mock objects in the service class
	
	@Mock
	CountryRepository countryrep;
	
	@InjectMocks
	CountryService countryservice;
	
	public List<Country> mycountries;
	
	@Test
	@Order(1)
	public void testGetAllCountries() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washigton"));
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(2,countryservice.getAllCountries().size());
		
	}
	
	@Test
	@Order(2)
	public void testGetCountrybyID() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washigton"));
		int countryID=1;
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(1,countryservice.getCountrybyID(countryID).getId());
	}
	
	@Test
	@Order(3)
	public void testGetCountrybyName() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washigton"));
		String countryName="USA";
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(countryName,countryservice.getCountrybyName(countryName).getCountryName());
	}
	
	@Test
	@Order(4)
	public void testAddCountry() {
		
		Country country = new Country(3, "Germany", "Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		countryservice.addCountry(country);
		assertEquals(country, countryservice.addCountry(country));
		
	}
	
	@Test
	@Order(5)
	public void testUpdateCountry() {
		
		Country country = new Country(3, "Germany", "Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		countryservice.addCountry(country);
		assertEquals(country, countryservice.updateCountry(country));
		
	}
	
	@Test
	@Order(6)
	public void testDeleteCountry() {
		Country country = new Country(3, "Germany", "Berlin");
		countryservice.deleteCountry(country);
		verify(countryrep,times(1)).delete(country);
	}

}
