package com.example.demo.department_employee.role.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.role.domain.Group;


@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> , JpaSpecificationExecutor<Group> {

}
