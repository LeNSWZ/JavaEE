package com.example.demo.department_employee.role.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.role.domain.Role;
import com.example.demo.department_employee.role.repository.RoleRepository;

@Service
@Transactional
public class RoleService implements IRoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void deleteById(int id) {
		roleRepository.findById(id);
	}

	@Override
	public Optional<Role> findById(int id) {
		return roleRepository.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}
	
}
