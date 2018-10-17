package com.example.demo.nationality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.nationality.domain.Nation;
import com.example.demo.nationality.service.INationService;

@RestController
@RequestMapping("/nation")
public class NationController {
	@Autowired
	private INationService nationService;
	
	@RequestMapping("/getNationName")
	public Nation getNationName() {
		return (Nation) nationService.findAll();
	}
	
}
