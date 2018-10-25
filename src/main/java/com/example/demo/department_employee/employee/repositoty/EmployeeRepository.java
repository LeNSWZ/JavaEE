package com.example.demo.department_employee.employee.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.employee.domain.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>,JpaSpecificationExecutor<Employee> {
	
	@Query("from Employee emp where emp.employeeName = ?1 and emp.departmentId.id = ?2")
	public Employee findOneEmployee(String employeeName,Long departmentId);
	
	@Query("from Employee emp where emp.departmentId.id = ?1")
	public List<Employee> findByDepartment_id(Long departmentId);
	
	public Employee findByAccount(String account);
}
