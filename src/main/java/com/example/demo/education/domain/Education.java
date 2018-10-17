package com.example.demo.education.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教育经历实体类，主要是用于设置员工的学历
 * @author midMoonNight
 *
 */

@Entity
@Table(name="t_education")
public class Education {
	private Long id;
	//学历
	private String educationName;
	//学校
	private String university;
	//学院
	private String institute;
	//专业
	private String major;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getEducationName() {
		return educationName;
	}
	public String getUniversity() {
		return university;
	}
	public String getInstitute() {
		return institute;
	}
	public String getMajor() {
		return major;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Education [educationName=" + educationName + ", university=" + university + ", institute=" + institute
				+ ", major=" + major + "]";
	}
}
