package com.example.demo.department_employee.role.domain;

import com.example.demo.department_employee.employee.domain.Employee;

public class GroupDTO {
	private Long id;
	private int roleId;
	private String roleName;
	private Long employeeId;
	private String employeeName;
	
	public static Group dtoToEntity(GroupDTO dto) {
		Group entity = new Group();
		entity.setId(dto.getId());
		Role role = new Role();
		role.setId(dto.getRoleId());
		Employee employee = new Employee();
		employee.setId(dto.getEmployeeId());
		entity.setRole(role);
		entity.setEmployee(employee);
		return entity;
	}
	
	public static GroupDTO entityToDTO(Group entity) {
		GroupDTO dto = new GroupDTO();
		dto.setId(entity.getId());
		dto.setRoleId(entity.getRole().getId());
		dto.setRoleName(entity.getRole().getRoleName());
		dto.setEmployeeId(entity.getEmployee().getId());
		dto.setEmployeeName(entity.getEmployee().getEmployeeName());
		
		return dto;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	
}
