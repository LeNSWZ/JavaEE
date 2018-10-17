package com.example.demo.employee.repositoty;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.employee.domain.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>,JpaSpecificationExecutor<Employee> {

}
