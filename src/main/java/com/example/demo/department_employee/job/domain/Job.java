package com.example.demo.department_employee.job.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.department_employee.department.domain.Department;

@Entity
@Table(name="t_job")
public class Job {
	private Long id;
	private String jobName;
	private Department department;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getJobName() {
		return jobName;
	}
	@ManyToOne(cascade=CascadeType.MERGE)
	public Department getDepartment() {
		return department;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setJobName(String name) {
		this.jobName = name;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + jobName + "]";
	}

}
