package com.example.demo.department_employee.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.department_employee.role.domain.Group;
import com.example.demo.department_employee.role.domain.GroupDTO;

public interface IGroupService {
	public void save(GroupDTO dto);
	public void deleteById(Long id);
	public void update(GroupDTO dto);
	public GroupDTO findById(Long id);
	public Page<GroupDTO> findAll(Specification<Group> spec,Pageable pageable);
}
