package com.example.demo.department_employee.employee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.common.beans.Gender;
import com.example.demo.common.beans.Status;
import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.education.domain.Education;
import com.example.demo.department_employee.employee.util.EmployeeRadomPassword;
import com.example.demo.department_employee.job.domain.Job;
import com.example.demo.department_employee.nationality.domain.Nationality;
import com.example.demo.department_employee.role.domain.Group;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_employee")
public class Employee {

	private Long id;
	//员工帐号
	private String account;
	//员工密码,设置一个随机的密码
	private String password = EmployeeRadomPassword.getStringRandom(6);
	//工资
	private int wages = 0;
	//部门id
	private Department departmentId;
	//角色组id
	private Group groupId;
	//职位id
	private Job jobId;
	//头像
	private String pricture;
	//性别
	private Gender gender;
	//电话
	private String telphone;
	//员工姓名
	private String employeeName;
	//员工地址
	private String address;
	//员工身份证
	private String idCard;
	//员工民族
	private Nationality nationalityId;
	//员工学历
	private Education educationId;
	//员工邮箱
	private String email;
	//入职时间
	private Date entryTime;
	//离职时间	
	private Date separationTime;
	//设置员工的状态值，是在职的还是离职的	
	private Status status = Status.in_service;
	//上级领导
	private Employee employeeLeader;
	//下属
	private List<Employee> subordinate = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getAccount() {
		return account;
	}
	public String getPassword() {
		return password;
	}
	public int getWages() {
		return wages;
	}
	@ManyToOne(cascade=CascadeType.MERGE)
	public Department getDepartmentId() {
		return departmentId;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public Group getGroupId() {
		return groupId;
	}
	@OneToOne(cascade=CascadeType.MERGE)
	public Job getJobId() {
		return jobId;
	}
	public String getPricture() {
		return pricture;
	}
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public String getTelphone() {
		return telphone;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public String getAddress() {
		return address;
	}
	public String getIdCard() {
		return idCard;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public Nationality getNationalityId() {
		return nationalityId;
	}
	@OneToOne(cascade=CascadeType.MERGE)
	public Education getEducationId() {
		return educationId;
	}
	public String getEmail() {
		return email;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getEntryTime() {
		return entryTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getSeparationTime() {
		return separationTime;
	}
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	@ManyToOne(cascade=CascadeType.MERGE)
	public Employee getEmployeeLeader() {
		return employeeLeader;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="employeeLeader")
	public List<Employee> getSubordinate() {
		return subordinate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setWages(int wages) {
		this.wages = wages;
	}
	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setGroupId(Group groupId) {
		this.groupId = groupId;
	}
	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}
	public void setPricture(String pricture) {
		this.pricture = pricture;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setNationalityId(Nationality nationalityId) {
		this.nationalityId = nationalityId;
	}
	public void setEducationId(Education educationId) {
		this.educationId = educationId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public void setSeparationTime(Date separationTime) {
		this.separationTime = separationTime;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setEmployeeLeader(Employee employeeLeader) {
		this.employeeLeader = employeeLeader;
	}
	public void setSubordinate(List<Employee> subordinate) {
		this.subordinate = subordinate;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", account=" + account + ", password=" + password + ", wages=" + wages
				+ ", pricture=" + pricture + ", gender=" + gender + ", telphone=" + telphone + ", employeeName="
				+ employeeName + ", address=" + address + ", idCard=" + idCard + ", email=" + email + ", entryTime="
				+ entryTime + "]";
	}
	
	
}
