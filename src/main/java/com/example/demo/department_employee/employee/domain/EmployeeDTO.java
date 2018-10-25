package com.example.demo.department_employee.employee.domain;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.example.demo.common.beans.BeanUtils;
import com.example.demo.common.beans.Gender;
import com.example.demo.common.beans.Status;
import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.education.domain.Education;
import com.example.demo.department_employee.job.domain.Job;
import com.example.demo.department_employee.nationality.domain.Country;
import com.example.demo.department_employee.nationality.domain.Nation;
import com.example.demo.department_employee.nationality.domain.Nationality;
import com.example.demo.department_employee.role.domain.Group;
import com.example.demo.department_employee.role.domain.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDTO {
	private Long id;
	//员工帐号
	private String account;
	//员工姓名
	private String employeeName;
	//员工密码,设置一个随机的密码
	private String password;
	//工资
	private int wages = 0;
	//部门id
	private Long department_id;
	private String departmentName;
	//角色（权限）id
	private int role_id;
	private String roleName;
	//职位id
	private Long job_id;
	private String jobName;
	//头像
	private String pricture;
	//性别
	private Gender gender;
	//电话
	private String telphone;
	//员工邮箱
	private String email;
	//员工地址
	private String address;
	//员工身份证
	private String idCard;
	/*    员工国籍/民族         */
	private Long nationality_id;
	//员工国籍
	private int country_id;
	/*英文简写*/
	private String abbr;
	/*英文全称*/
	private String english;
	/*中文名称*/
	private String note;
	/*中文拼音缩写*/
	private String pycode;
	//员工民族
	private int nation_id;
	private String nationName;
	
	//员工学历
	private Long education_id;
	private String educationName;
	//学校
	private String university;
	//学院
	private String institute;
	//专业
	private String major;
	
	//入职时间
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	private Date entryTime;
	//离职时间
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	private Date separationTime;
	//设置员工的状态值，是在职的还是离职的
	@Enumerated(EnumType.STRING)
	private Status status;
	//上级领导
	private Long employeeLeaderId;
	private String employeeLeaderName;
	
	public static void DtoToEntity(EmployeeDTO dto,Employee entity) {
		int wagesEntity = entity.getWages();
		BeanUtils.copyProperties(dto, entity);		
		if (dto.getWages() == 0) {
			entity.setWages(wagesEntity);
		}
		if (dto.getDepartment_id() != null && dto.getDepartment_id() > 0) {
			Department department = new Department();
			department.setId(dto.getDepartment_id());
			entity.setDepartmentId(department);
		}
		if (dto.getJob_id() != null && dto.getJob_id() >0) {
			Job job = new Job();
			job.setId(dto.getJob_id());
			entity.setJobId(job);
		}
		if (dto.getRole_id() > 0) {
			Group group = new Group();
			Role role = new Role();
			role.setId(dto.getRole_id());
			group.setRole(role);
			entity.setGroupId(group);
		}
		if (dto.getEducation_id() != null && dto.getEducation_id() > 0) {
			Education education = new Education();
			education.setId(dto.getEducation_id());
			entity.setEducationId(education);
		}
		if (dto.getCountry_id() > 0) {
			Nationality nationality = new Nationality();
			Country country = new Country();
			country.setId(dto.getCountry_id());
			nationality.setCountry(country);
			
			if (dto.getNation_id() > 0) {
				Nation nation = new Nation();
				nation.setId(dto.getNation_id());
				nationality.setNation(nation);
			}
			
			entity.setNationalityId(nationality);
		}
		/*if (dto.getEmployeeLeaderName() != null && dto.getEmployeeLeaderId() > 0) {
			Employee employee = new Employee();
			employee.setId(dto.getEmployeeLeaderId());
			employee.setEmployeeName(dto.getEmployeeLeaderName());
			entity.setEmployeeLeader(employee);
		}*/
		//return entity;
	}
	
	public static void EntityToDTO(Employee entity,EmployeeDTO dto) {
		//EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(entity, dto);
		if (entity.getDepartmentId() != null) {
			dto.setDepartment_id(entity.getDepartmentId().getId());
			dto.setDepartmentName(entity.getDepartmentId().getDepartmentName());
		}
		if (entity.getJobId() != null) {
			dto.setJob_id(entity.getJobId().getId());
			dto.setJobName(entity.getJobId().getJobName());
		}
		if (entity.getGroupId() != null) {
			dto.setRole_id(entity.getGroupId().getRole().getId());
			dto.setRoleName(entity.getGroupId().getRole().getRoleName());
		}
		if (entity.getEducationId() != null) {
			dto.setEducation_id(entity.getEducationId().getId());
			dto.setEducationName(entity.getEducationId().getEducationName());
			dto.setUniversity(entity.getEducationId().getUniversity());
			dto.setInstitute(entity.getEducationId().getInstitute());
			dto.setMajor(entity.getEducationId().getMajor());
		}
		if (entity.getNationalityId() != null) {
			dto.setNationality_id(entity.getNationalityId().getId());
			dto.setCountry_id(entity.getNationalityId().getCountry().getId());
			dto.setAbbr(entity.getNationalityId().getCountry().getAbbr());
			dto.setEnglish(entity.getNationalityId().getCountry().getEnglish());
			dto.setNote(entity.getNationalityId().getCountry().getNote());
			dto.setPycode(entity.getNationalityId().getCountry().getPycode());
			if (entity.getNationalityId().getNation() != null) {
				dto.setNation_id(entity.getNationalityId().getNation().getId());
				dto.setNationName(entity.getNationalityId().getNation().getNationName());
			}
		}
		if (entity.getEmployeeLeader() != null) {
			dto.setEmployeeLeaderId(entity.getEmployeeLeader().getId());
			dto.setEmployeeLeaderName(entity.getEmployeeLeader().getEmployeeName());
		}
		//return dto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getWages() {
		return wages;
	}
	public void setWages(int wages) {
		this.wages = wages;
	}
	public Long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getJob_id() {
		return job_id;
	}
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getPricture() {
		return pricture;
	}
	public void setPricture(String pricture) {
		this.pricture = pricture;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Long getNationality_id() {
		return nationality_id;
	}
	public void setNationality_id(Long nationality_id) {
		this.nationality_id = nationality_id;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPycode() {
		return pycode;
	}
	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	public int getNation_id() {
		return nation_id;
	}
	public void setNation_id(int nation_id) {
		this.nation_id = nation_id;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	public Long getEducation_id() {
		return education_id;
	}
	public void setEducation_id(Long education_id) {
		this.education_id = education_id;
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Date getSeparationTime() {
		return separationTime;
	}
	public void setSeparationTime(Date separationTime) {
		this.separationTime = separationTime;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getEmployeeLeaderId() {
		return employeeLeaderId;
	}
	public void setEmployeeLeaderId(Long employeeLeaderId) {
		this.employeeLeaderId = employeeLeaderId;
	}
	public String getEmployeeLeaderName() {
		return employeeLeaderName;
	}
	public void setEmployeeLeaderName(String employeeLeaderName) {
		this.employeeLeaderName = employeeLeaderName;
	}
}
