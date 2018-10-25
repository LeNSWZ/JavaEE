package com.example.demo.department_employee.role.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.department_employee.role.domain.Role;

public interface IRoleService {
	public void save(Role role);
	public void deleteById(int id);
	public Optional<Role> findById(int id);
	public List<Role> findAll();
}
