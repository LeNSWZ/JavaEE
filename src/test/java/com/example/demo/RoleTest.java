package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.department_employee.role.domain.Role;
import com.example.demo.department_employee.role.service.IRoleService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {
	@Autowired
	private IRoleService roleService;
	
	@Test
	public void roleSave() {
		Role r1 = new Role();
		r1.setRoleName("superAdmin");
		
		Role r2 = new Role();
		r2.setRoleName("Admin");
		
		Role r3 = new Role();
		r3.setRoleName("User");
		
		roleService.save(r1);
		roleService.save(r2);
		roleService.save(r3);
	}
}
