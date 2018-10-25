package com.example.demo.department_employee.job.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class JobQueryDTO {
	private String jobName;
	private Long departmentId;
	private String departmentName;
	
	public static Specification<Job> getWhereClause(final JobQueryDTO jobQueryDTO) {
		return new Specification<Job>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(StringUtils.isNotBlank(jobQueryDTO.getJobName())) {
					predicates.add(criteriaBuilder.like(root.get("jobName").as(String.class), "%"+jobQueryDTO.getJobName()+"%"));
				}
				if (jobQueryDTO.getDepartmentId() != null && jobQueryDTO.getDepartmentId() > 0) {
					predicates.add(criteriaBuilder.equal(root.get("department").get("id").as(Long.class), jobQueryDTO.getDepartmentId()));
				}
				if (StringUtils.isNotBlank(jobQueryDTO.getDepartmentName())) {
					predicates.add(criteriaBuilder.like(root.get("department").get("departmentName").as(String.class), "%"+jobQueryDTO.getDepartmentName()+"%"));
				}
				Predicate[] pre = new Predicate[predicates.size()];
				return query.where(predicates.toArray(pre)).getRestriction();
			}
			
		};
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
