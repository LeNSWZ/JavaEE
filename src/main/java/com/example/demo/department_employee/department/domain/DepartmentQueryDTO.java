package com.example.demo.department_employee.department.domain;

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

import com.example.demo.common.beans.Status;

public class DepartmentQueryDTO {
	
	private String departmentName;
	private String departmentNumber;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date createTimeStart;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date createTimeEnd;
	private String deptStatus;
	
	@SuppressWarnings("serial")
	public static Specification<Department> getWhereClause(final DepartmentQueryDTO deptQueryDTO) {
		return new Specification<Department>() {
			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();

				if (StringUtils.isNotBlank(deptQueryDTO.getDepartmentName())) {
					predicate.add(criteriaBuilder.like(root.get("departmentName").as(String.class),
							"%" + deptQueryDTO.getDepartmentName() + "%"));
				}
				if (StringUtils.isNotBlank(deptQueryDTO.getDepartmentNumber())) {
					predicate.add(criteriaBuilder.like(root.get("departmentNumber").as(String.class),
							"%" + deptQueryDTO.getDepartmentNumber() + "%"));
				}
				if (null!=deptQueryDTO.getCreateTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
							deptQueryDTO.getCreateTimeStart()));
				}
				if (null!=deptQueryDTO.getCreateTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class),
							deptQueryDTO.getCreateTimeEnd()));
				}
				if (StringUtils.isNotBlank(deptQueryDTO.getDeptStatus()) && deptQueryDTO.getDeptStatus().equals("close")) {
					predicate.add(criteriaBuilder.equal(root.get("deptStatus").as(Status.class),Status.close));
				}else {
					predicate.add(criteriaBuilder.equal(root.get("deptStatus").as(Status.class),Status.activity));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
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

	public String getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}
	
}