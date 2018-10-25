package com.example.demo.department_employee.nationality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.department_employee.nationality.domain.Nation;
import com.example.demo.department_employee.nationality.service.INationService;

@RestController
@RequestMapping("/nation")
public class NationController {
	@Autowired
	private INationService nationService;
	
	@RequestMapping("/getNationName")
	public List<Nation> getNationName() {
		return nationService.findAll();
	}
	
}
