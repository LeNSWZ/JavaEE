package com.example.demo.department_employee.education.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class EducationQueryDTO {
	//学历
	private String educationName;
	//学校
	private String university;
	//学院
	private String institute;
	//专业
	private String major;
	
	public static Specification<Education> getWhereClause(final EducationQueryDTO dto) {
		return new Specification<Education>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Education> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (StringUtils.isNotBlank(dto.getEducationName())) {
					predicates.add(criteriaBuilder.like(root.get("educationName").as(String.class), "%"+dto.getEducationName()+"%"));
				}
				if (StringUtils.isNotBlank(dto.getUniversity())) {
					predicates.add(criteriaBuilder.like(root.get("university").as(String.class), "%"+dto.getUniversity()+"%"));
				}
				if (StringUtils.isNotBlank(dto.getInstitute())) {
					predicates.add(criteriaBuilder.like(root.get("institute").as(String.class), "%"+dto.getInstitute()+"%"));
				}
				if (StringUtils.isNotBlank(dto.getMajor())) {
					predicates.add(criteriaBuilder.like(root.get("major").as(String.class), "%"+dto.getMajor()+"%"));
				}
				Predicate[] pre = new Predicate[predicates.size()];
				
				return query.where(predicates.toArray(pre)).getRestriction();
			}
		};
	}
	
	public String getEducationName() {
		return educationName;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}	
}
