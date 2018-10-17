package com.example.demo.role.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.employee.domain.Employee;

@Entity
@Table(name="t_group")
public class Group {
	private Long id;
	private Role role;
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@OneToOne(cascade=CascadeType.MERGE)
	public Role getRole() {
		return role;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
