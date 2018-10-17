package com.example.demo.nationality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.nationality.domain.Country;
import com.example.demo.nationality.service.ICountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
	@Autowired
	private ICountryService countryService;
	
	@RequestMapping("/getCountryName")
	public Country getCountryName() {
		return (Country) countryService.findAll();
	}
}
