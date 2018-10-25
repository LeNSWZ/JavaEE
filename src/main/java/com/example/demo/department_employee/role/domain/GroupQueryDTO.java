package com.example.demo.department_employee.role.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class GroupQueryDTO {
	private int roleId;
	private String roleName;
	private Long employeeId;
	private String employeeName;
	
	public static Specification<Group> getWhereClause(final GroupQueryDTO dto) {
		return new Specification<Group>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				if (dto.getRoleId() > 0) {
					predicates.add(criteriaBuilder.equal(root.get("role").get("id").as(Integer.class), dto.getRoleId()));
				}
				if (StringUtils.isNotBlank(dto.getRoleName())) {
					predicates.add(criteriaBuilder.like(root.get("role").get("roleName").as(String.class), "%"+dto.getRoleName()+"%"));
				}
				if (dto.getEmployeeId() != null && dto.getEmployeeId() > 0) {
					predicates.add(criteriaBuilder.equal(root.get("employee").get("id").as(Integer.class), dto.getEmployeeId()));
				}
				if (StringUtils.isNotBlank(dto.getEmployeeName())) {
					predicates.add(criteriaBuilder.like(root.get("employee").get("employeeName").as(String.class), "%"+dto.getEmployeeName()+"%"));
				}
				
				Predicate[] predicate = new Predicate[predicates.size()];
				
				return query.where(predicates.toArray(predicate)).getRestriction();
			}
		};
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
