package com.example.demo.department_employee.nationality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.department_employee.nationality.domain.Country;
import com.example.demo.department_employee.nationality.domain.Nation;
import com.example.demo.department_employee.nationality.service.ICountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
	@Autowired
	private ICountryService countryService;
	
	@RequestMapping("/getCountryName")
	public List<Country> getCountryName() {
		return countryService.findAll();
	}

	@RequestMapping("/getNationName")
	public List<Nation> getNationName(@RequestParam("countryId") Integer id) {
//		System.out.println("进来了！");
//		System.out.println("id:" + id);
		System.out.println(id);
		Country country = countryService.findById(id).get();
		if (null != country.getNations()) {
			List<Nation> nations = country.getNations();
			return nations;
		} else {
			return null;
		}
	}
}
