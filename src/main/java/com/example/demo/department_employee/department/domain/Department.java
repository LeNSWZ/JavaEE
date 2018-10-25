package com.example.demo.department_employee.department.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.common.beans.Status;
import com.example.demo.department_employee.employee.domain.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_department")
public class Department {
	
	private Long id;
	//部门名称
	private String departmentName;
	//部门编号
	private String departmentNumber;
	//部门简介
	private String introduction;
	//部门职责
	private String duties;
	//部门创建时间
	private Date createTime;
	//部门的状态，是还在使用的部门，还是已经废除的部门
	private Status deptStatus = Status.activity;
	//上级部门
	private Department departmentParent;
	//下级部门
	private List<Department> childrens = new ArrayList<>();
	//部门员工
	private List<Employee> employees = new ArrayList<>();


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@Column(name="department_name",unique=true)
	public String getDepartmentName() {
		return departmentName;
	}
	@Column(name="department_number",unique=true)
	public String getDepartmentNumber() {
		return departmentNumber;
	}
	@Lob
    @Column(columnDefinition="text")
	public String getIntroduction() {
		return introduction;
	}
	@Lob
    @Column(columnDefinition="text")
	public String getDuties() {
		return duties;
	}
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	@Enumerated(EnumType.STRING)
	public Status getDeptStatus() {
		return deptStatus;
	}
	@ManyToOne(cascade=CascadeType.MERGE)
	public Department getDepartmentParent() {
		return departmentParent;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="departmentParent")
	public List<Department> getChildrens() {
		return childrens;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="departmentId")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setDeptStatus(Status deptStatus) {
		this.deptStatus = deptStatus;
	}
	public void setDepartmentParent(Department departmentParent) {
		this.departmentParent = departmentParent;
	}
	public void setChildrens(List<Department> childrens) {
		this.childrens = childrens;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "Department [id="+ id +", departmentName=" + departmentName + ", departmentNumber=" + departmentNumber + ", introduction=" + introduction + ", duties="
				+ duties + ", createTime=" + createTime + ", deptStatus=" + deptStatus + "]";
	}
	
	
}
