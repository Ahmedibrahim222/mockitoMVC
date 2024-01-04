package com.CountryServices.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.CountryServices.demo.beans.Country;
import com.CountryServices.demo.controllers.AddResponse;
import com.CountryServices.demo.repository.CountryRepository;

//@component will allow us to to able to autowired this class in another c
@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryrep;
	
	
	public List<Country> getAllCountries() {
		
		//return countryrep.findAll();
		
		List<Country> countries = countryrep.findAll();
		return countries;
		
	}
	
	public Country getCountrybyID(int id) {
		
		//return countryrep.findById(id).get();
		
		List<Country> countries = countryrep.findAll();
		Country country = null;
		
		for(Country con:countries) {
			if(con.getId()==id)
				country=con;
		}
		return country;
	}
	
	public Country getCountrybyName(String countryName) {
		
		 List<Country> countries = countryrep.findAll();
		 Country country = null;
		 
		 for(Country con:countries) {
			 if(con.getCountryName().equalsIgnoreCase(countryName))
				 country=con;
		 }
		 return country;
	}
	
	public Country addCountry(Country country) {
		
		country.setId(getMaxId());
		countryrep.save(country);
		return country;
	}
	
	//Utility method to get max id
	public int getMaxId() {
		
		return countryrep.findAll().size()+1;
	
	}
	
	public Country updateCountry(Country country) {
		
		countryrep.save(country);
		return country;
	}
	
	public void deleteCountry(Country country) {
		
		countryrep.delete(country);
		
//		AddResponse res = new AddResponse();
//		res.setMsg("Country deleted...!");
//		res.setId(country);
//		return res;
		
		
	}


}
