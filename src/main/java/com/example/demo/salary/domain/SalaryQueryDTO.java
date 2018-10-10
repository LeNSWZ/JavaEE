package com.example.demo.salary.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

public class SalaryQueryDTO {
	
	private String userName;
	
	private String userId;
	
	private String department;
	
	private String position;
	
	private String baseSalary;
	
	private String meritPay;
	
	private String monthlySalary;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")  
	private Date createTimeStart;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")  
	private Date createTimeEnd;

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

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<Salary> getWhereClause(final SalaryQueryDTO salaryQueryDTO) {
		return new Specification<Salary>() {
			@Override
			public Predicate toPredicate(Root<Salary> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(salaryQueryDTO.getUserId())) {
					predicate.add(criteriaBuilder.like(root.get("userId").as(String.class),
							"%" + salaryQueryDTO.getUserId() + "%"));
				}
				if (StringUtils.isNotBlank(salaryQueryDTO.getUserName())) {
					predicate.add(criteriaBuilder.like(root.get("userName").as(String.class),
							"%" + salaryQueryDTO.getUserName() + "%"));
				}
				if (StringUtils.isNotBlank(salaryQueryDTO.getDepartment())) {
					predicate.add(criteriaBuilder.like(root.get("department").as(String.class),
							"%" + salaryQueryDTO.getDepartment() + "%"));
				}
				if (null!=salaryQueryDTO.getCreateTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
							salaryQueryDTO.getCreateTimeStart()));
				}
				if (null!=salaryQueryDTO.getCreateTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class),
							salaryQueryDTO.getCreateTimeEnd()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
