package com.example.demo.department_employee.department.domain;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.common.beans.BeanUtils;
import com.example.demo.common.beans.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DepartmentBasicInfoDTO {
	private Long id;
	private String departmentName;
	//部门编号
	private String departmentNumber;
	//部门简介
	private String introduction;
	//部门职责
	private String duties;
	//部门创建时间
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	private Date createTime;
	@Enumerated(EnumType.STRING)
	private Status deptStatus;
	//上级部门
	private Long departmentParentId;
	private String departmentParentName;
	
	public static void DtoToEntity(DepartmentBasicInfoDTO dto, Department department) {
		BeanUtils.copyProperties(dto, department);
		Long departmentParentId = dto.getDepartmentParentId();
		if (departmentParentId != null && departmentParentId > 0) {
			Department departmentParent = new Department();
			departmentParent.setId(departmentParentId);
			department.setDepartmentParent(departmentParent);
		}
	}
	
	public static void EntityToDTO(Department department, DepartmentBasicInfoDTO dto) {
		BeanUtils.copyProperties(department, dto);
		if (department.getDepartmentParent() != null) {
			dto.setDepartmentParentId(department.getDepartmentParent().getId());
			dto.setDepartmentParentName(department.getDepartmentParent().getDepartmentName());
		}
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Status getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(Status deptStatus) {
		this.deptStatus = deptStatus;
	}

	public Long getDepartmentParentId() {
		return departmentParentId;
	}

	public void setDepartmentParentId(Long departmentParentId) {
		this.departmentParentId = departmentParentId;
	}

	public String getDepartmentParentName() {
		return departmentParentName;
	}

	public void setDepartmentParentName(String departmentParentName) {
		this.departmentParentName = departmentParentName;
	}
	
	
}
