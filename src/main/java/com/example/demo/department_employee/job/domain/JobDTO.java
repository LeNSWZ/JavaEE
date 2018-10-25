package com.example.demo.department_employee.job.domain;

import com.example.demo.common.beans.BeanUtils;
import com.example.demo.department_employee.department.domain.Department;

public class JobDTO {
	private Long id;
	private String jobName;
	private Long departmentId;
	private String departmentName;
	
	public static void dtoToEntity(JobDTO dto,Job entity) {
		BeanUtils.copyProperties(dto, entity);
		if (dto.getDepartmentId() != null && dto.getDepartmentId() > 0) {
			Department department = new Department();
			department.setId(dto.getDepartmentId());
			//department.setDepartmentName(dto.getDepartmentName());
			entity.setDepartment(department);
		}
	}
	
	public static void entityToDTO(Job entity,JobDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		dto.setDepartmentId(entity.getDepartment().getId());
		dto.setDepartmentName(entity.getDepartment().getDepartmentName());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "JobDTO [id=" + id + ", jobName=" + jobName + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + "]";
	}
}
