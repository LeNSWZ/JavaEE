package com.example.demo.department_employee.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.department_employee.role.domain.Role;
import com.example.demo.department_employee.role.service.IRoleService;


@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/getRoleName")
	public List<Role> getRoleName() {
		return roleService.findAll();
	}
}
