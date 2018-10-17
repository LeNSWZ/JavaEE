package com.example.demo.employee.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.common.beans.Gender;

public class EmployeeQueryDTO {
	private String account;
	private int upperWages=0;
	private int lowerWages=0;
	private Long departmentId;
	private Long jobId;
	private Gender gender;
	private String telphone;
	private String employeeName;
	private String address;
	private Long nationalId;
	private Long educationId;
	
	public static Specification<Employee> getWhereClause(final EmployeeQueryDTO employeeQueryDTO){
		return new Specification<Employee>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unlikely-arg-type")
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNoneBlank(employeeQueryDTO.getAccount())) {
					predicate.add(criteriaBuilder.like(root.get("account").as(String.class)
							, "%"+employeeQueryDTO.getAccount()+"%"));
				}
				if (employeeQueryDTO.getUpperWages() >0) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("wages").as(int.class)
							, employeeQueryDTO.getUpperWages()));
				}
				if (employeeQueryDTO.getLowerWages() >0) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("wages").as(int.class)
							, employeeQueryDTO.getLowerWages()));
				}
				if (employeeQueryDTO.getDepartmentId() != null) {
					predicate.add(criteriaBuilder.equal(root.get("departmentId").get("id").as(Long.class)
							, employeeQueryDTO.getDepartmentId()));
				}
				if (employeeQueryDTO.getJobId() != null) {
					predicate.add(criteriaBuilder.equal(root.get("jobId").get("id").as(Long.class)
							, employeeQueryDTO.getJobId()));
				}
				if (employeeQueryDTO.getGender() != null && !employeeQueryDTO.getGender().equals("")) {
					predicate.add(criteriaBuilder.equal(root.get("gender").as(Gender.class)
							, employeeQueryDTO.getGender()==Gender.MAN? Gender.MAN:Gender.WOMAN));
				}
				if (StringUtils.isNoneBlank(employeeQueryDTO.getTelphone())) {
					predicate.add(criteriaBuilder.like(root.get("telphone").as(String.class)
							, "%"+employeeQueryDTO.getTelphone()+"%"));
				}
				if (StringUtils.isNoneBlank(employeeQueryDTO.getEmployeeName())) {
					predicate.add(criteriaBuilder.like(root.get("employeeName").as(String.class)
							, "%"+employeeQueryDTO.getEmployeeName()+"%"));
				}
				if (StringUtils.isNoneBlank(employeeQueryDTO.getAddress())) {
					predicate.add(criteriaBuilder.like(root.get("address").as(String.class)
							, "%"+employeeQueryDTO.getAddress()+"%"));
				}
				if (employeeQueryDTO.getNationalId() != null) {
					predicate.add(criteriaBuilder.equal(root.get("nationalId").as(Long.class)
							, employeeQueryDTO.getNationalId()));
				}if (employeeQueryDTO.getEducationId() != null) {
					predicate.add(criteriaBuilder.equal(root.get("educationId").as(Long.class)
							, employeeQueryDTO.getEducationId()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getUpperWages() {
		return upperWages;
	}

	public void setUpperWages(int upperWages) {
		this.upperWages = upperWages;
	}

	public int getLowerWages() {
		return lowerWages;
	}

	public void setLowerWages(int lowerWages) {
		this.lowerWages = lowerWages;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNationalId() {
		return nationalId;
	}

	public void setNationalId(Long nationalId) {
		this.nationalId = nationalId;
	}

	public Long getEducationId() {
		return educationId;
	}

	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	
	
}
