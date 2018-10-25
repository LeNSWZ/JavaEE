package com.example.demo.department_employee.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.common.web.TreeNode;
import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.department.domain.DepartmentBasicInfoDTO;

public interface IDepartmentService {
	public void save(DepartmentBasicInfoDTO dto);
	public void saveAll(List<Department> depts);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);

	public void update(DepartmentBasicInfoDTO dto);
	
	public Optional<Department> findById(Long id);
	public List<Department> findAll();
	
	public boolean existsById(Long id);
	public long count();
	//动态条件查询
	public Page<DepartmentBasicInfoDTO> findAll(Specification<Department> spec, Pageable pageable);
	public Page<DepartmentBasicInfoDTO> findAll(Pageable pageable);
	public List<DepartmentBasicInfoDTO> findAllOfBasicInfoDTO();
	
	public List<TreeNode> findNodes(Long parentId,String flag);
	public Page<DepartmentBasicInfoDTO> findByDepartment(Long departmentId, Pageable pageable);
}
