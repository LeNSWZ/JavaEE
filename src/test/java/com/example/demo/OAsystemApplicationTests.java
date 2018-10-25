package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.department.service.IDepartmentService;
import com.example.demo.department_employee.employee.domain.Employee;
import com.example.demo.department_employee.employee.service.IEmployeeService;
import com.example.demo.department_employee.role.domain.Role;
import com.example.demo.department_employee.role.service.IRoleService;




@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OAsystemApplicationTests {

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IRoleService roleservice;
	
	@Test
	public void contextLoads() {
		
		Department org1 = new Department();
		org1.setDepartmentNumber("D00001");
		org1.setDepartmentName("东莞理工学院");

		Department org2 = new Department();
		org2.setDepartmentNumber("D00002");
		org2.setDepartmentName("东莞理工学院计算机与网络安全学院");

		Department org3 = new Department();
		org3.setDepartmentNumber("D00003");
		org3.setDepartmentName("机械工程学院");

		Department org4 = new Department();
		org4.setDepartmentNumber("D00004");
		org4.setDepartmentName("国际学院");

		Department org5 = new Department();
		org5.setDepartmentNumber("D00021");
		org5.setDepartmentName("计算机科学与应用专业");

		Department org6 = new Department();
		org6.setDepartmentNumber("D00022");
		org6.setDepartmentName("软件工程专业");

		Department org7 = new Department();
		org7.setDepartmentNumber("D00023");
		org7.setDepartmentName("网络安全专业");
		
		org1.getChildrens().add(org2);
		org1.getChildrens().add(org3);
		org1.getChildrens().add(org4);

		org2.setDepartmentParent(org1);
		org3.setDepartmentParent(org1);
		org4.setDepartmentParent(org1);

		org2.getChildrens().add(org5);
		org2.getChildrens().add(org6);
		org2.getChildrens().add(org7);

		org5.setDepartmentParent(org2);
		org6.setDepartmentParent(org2);
		org7.setDepartmentParent(org2);
		
		//departmentService.save(org1);
	}
	
	@Test
	public void departmentListSave() {
		List<Department> departments = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Department department = new Department();
			department.setDepartmentName("departmentName"+i);
			department.setDepartmentNumber("departmentNumber"+i);
			department.setDuties("duties"+i);
			department.setIntroduction("introduction"+i);
			department.setCreateTime(new Date());
			departments.add(department);
		}
		departmentService.saveAll(departments);
	}
	
	@Test
	public void departmentSaveOne() {
		Department department = new Department();
		department.setDepartmentName("departmentName222");
		department.setDepartmentNumber("departmentNumber222");
		department.setDuties("duties222");
		department.setIntroduction("introduction222");
		department.setCreateTime(new Date());
		//departmentService.save(department);
	}
	
	@Test
	public void departmentTestUnique() {
		Department department = new Department();
		department.setDepartmentName("departmentName1");
		department.setDepartmentNumber("departmentNumber1");
		department.setDuties("duties");
		department.setIntroduction("introduction");
		department.setCreateTime(new Date());
		//departmentService.save(department);
	}
	
	@Test
	public void departmentDelete() {
		//departmentService.deleteById(1L);
		Long[] ids = new Long[20];
		
		for(int i=2;i<12;i++) {
			ids[i-2] = (long) i;
		}
		departmentService.deleteAll(ids);
	}

	@Test
	public void departmentFind() {
		Department department = departmentService.findById(1L).get();
		System.out.println("department::"+department);
		List<Department> departments = departmentService.findAll();
		for (Department dept : departments) {
			System.out.println(dept);
		}
	}
	
	@Test
	public void roleSave() {
		Role role = new Role();
		role.setRoleName("roleName111");
		roleservice.save(role);
	}
	
	
	@Test
	public void roleFind() {
		Role role = roleservice.findById(1).get();
		System.out.println(role);
	}
	
	@Test
	public void EmployeeSaveTestPassword() {
		Employee employee = new Employee();
		employee.setAccount("account5555555555111");
		//employeeService.save(employee);
		
		/*employee = employeeService.findById(1L).get();
		System.out.println(employee);*/
	}
	
	public void jobSave() {
		
	}
	
	public void educationSave() {
		
	}
	
	public void nationalitySave() {
		
	}

//	@Test
//	public void delete() {
//		File testFile = new File("E:\\spring-tool-suit-git\\oa-system\\oa-system\\static\\default\\testdel");
//		UploadOrDownloadFileUtil.deleteFolder(testFile);
//	}




}
