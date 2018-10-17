package com.example.demo.job.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.department.domain.Department;

@Entity
@Table(name="t_job")
public class Job {
	private Long id;
	private String name;
	private Department departmentId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public Department getDepartmentId() {
		return departmentId;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Job [name=" + name + "]";
	}
}
