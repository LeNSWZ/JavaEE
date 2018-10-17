package com.example.demo.department.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.beans.BeanUtils;
import com.example.demo.employee.domain.Employee;

/**
 * 该dto用于处理有关部门员工的相关操作
 * @author midMoonNight
 *
 */
public class DepartmentDTO {
	private Long id;
	
	private String departmentName;
	
	private List<Long> employeesIds = new ArrayList<>();
	private List<String> employeesAccount = new ArrayList<>();
	private List<String> employeesName = new ArrayList<>();
	
	
	public static void DtoToEntity(DepartmentDTO departmentDTO,Department department) {
		BeanUtils.copyProperties(departmentDTO, department);
		for (Long id : departmentDTO.getEmployeesIds()) {
			Employee employee=new Employee();
			employee.setId(id);
			department.getEmployees().add(employee);
		}
	}
	
	public static void EntityToDTO(Department department,DepartmentDTO departmentDTO) {
		BeanUtils.copyProperties(department, departmentDTO);
		List<Employee>employees=department.getEmployees();
		for (Employee employee : employees) {
			departmentDTO.getEmployeesName().add(employee.getEmployeeName());
			departmentDTO.getEmployeesIds().add(employee.getId());
			departmentDTO.getEmployeesAccount().add(employee.getAccount());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Long> getEmployeesIds() {
		return employeesIds;
	}

	public void setEmployeesIds(List<Long> employeesIds) {
		this.employeesIds = employeesIds;
	}

	public List<String> getEmployeesAccount() {
		return employeesAccount;
	}

	public void setEmployeesAccount(List<String> employeesAccount) {
		this.employeesAccount = employeesAccount;
	}

	public List<String> getEmployeesName() {
		return employeesName;
	}

	public void setEmployeesName(List<String> employeesName) {
		this.employeesName = employeesName;
	}
	
	
}
