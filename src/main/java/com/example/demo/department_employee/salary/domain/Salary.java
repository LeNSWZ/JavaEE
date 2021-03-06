package com.example.demo.department_employee.salary.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.employee.domain.Employee;
import com.example.demo.department_employee.job.domain.Job;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

@Entity
@Table(name="t_salary")
public class Salary {
	
	@Excel(name = "id" ,orderNum = "0")
	private Long id;
	
	@Excel(name = "员工姓名" ,orderNum = "2")
	private String userName;
	
	@Excel(name = "员工编号" ,orderNum = "1")
	private String userId;
	
	@Excel(name = "员工部门" ,orderNum = "3")
	private String department;
	
	@Excel(name = "员工职位" ,orderNum = "4")
	private String position;
	
	@Excel(name = "基本工资" ,orderNum = "5")
	private String baseSalary;
	
	@Excel(name = "绩效工资" ,orderNum = "6")
	private String meritPay;
	
	@Excel(name = "月度工资" ,orderNum = "7")
	private String monthlySalary;
	
	@Excel(name = "创建日期" ,orderNum = "8",importFormat = "yyyy-MM-dd")
	private Date createTime;
	
	private Employee employee;
	
	private Department departments;
	
	private Job job;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public String getMeritPay() {
		return meritPay;
	}
	public void setMeritPay(String meritPay) {
		this.meritPay = meritPay;
	}
	
	public String getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Department getDepartments() {
		return departments;
	}
	public void setDepartments(Department departments) {
		this.departments = departments;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
}
