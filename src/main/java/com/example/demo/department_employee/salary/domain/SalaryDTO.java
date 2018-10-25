package com.example.demo.department_employee.salary.domain;

import java.util.Date;

import com.example.demo.common.beans.BeanUtils;
import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.employee.domain.Employee;
import com.example.demo.department_employee.job.domain.Job;
import com.fasterxml.jackson.annotation.JsonFormat;



public class SalaryDTO {
	private Long id;
	
	private String userName;
	
	private String userId;
	
	private String department;
	
	private String position;
	
	private Long employeeId;
	
	private Long departmentId;
	
	private Long jobId;
	
	private String baseSalary;
	
	private String meritPay;
	
	private String monthlySalary;
	
	private Date createTime;

	public static Salary dtoToEntity(SalaryDTO dto,Salary salary) {
		BeanUtils.copyProperties(dto, salary);
		Long employeeId = dto.getEmployeeId();
		if (employeeId != null && employeeId > 0) {
			Employee employee = new Employee();
			employee.setId(employeeId);
			employee.setEmployeeName(dto.getUserName());
			salary.setEmployee(employee);
		}
		Long departmentId = dto.getDepartmentId();
		if (departmentId != null && departmentId > 0) {
			Department departments = new Department();
			departments.setId(departmentId);
			salary.setDepartments(departments);
		}
		Long jobId = dto.getJobId();
		if (jobId != null && jobId > 0) {
			Job job = new Job();
			job.setId(jobId);
			salary.setJob(job);
		}
		return salary;
	}
	
	public static SalaryDTO entityToDTO(Salary salary,SalaryDTO dto) {
		BeanUtils.copyProperties(salary, dto);
		if (salary.getDepartments() != null) {
			dto.setDepartmentId(salary.getDepartments().getId());
			dto.setDepartment(salary.getDepartments().getDepartmentName());
			salary.setDepartment(salary.getDepartments().getDepartmentName());
		}
		if (salary.getJob() != null) {
			dto.setJobId(salary.getJob().getId());
			dto.setPosition(salary.getJob().getJobName());
			salary.setPosition(salary.getJob().getJobName());
		}
		if (salary.getEmployee() != null) {
			dto.setUserId(salary.getEmployee().getAccount());
			dto.setUserName(salary.getEmployee().getEmployeeName());
			salary.setUserId(salary.getEmployee().getAccount());
			salary.setUserName(salary.getEmployee().getEmployeeName());
		}
		return dto;
	}
	
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
}
