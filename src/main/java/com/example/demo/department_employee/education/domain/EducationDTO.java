package com.example.demo.department_employee.education.domain;

import org.springframework.beans.BeanUtils;

public class EducationDTO {
	private Long id;
	//学历
	private String educationName;
	//学校
	private String university;
	//学院
	private String institute;
	//专业
	private String major;
	
	public static Education dtoToEntity(EducationDTO dto) {
		Education entity = new Education();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}
	
	public static EducationDTO entityToDTO(Education entity) {
		EducationDTO dto = new EducationDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
