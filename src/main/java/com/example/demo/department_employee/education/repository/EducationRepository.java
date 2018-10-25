package com.example.demo.department_employee.education.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.education.domain.Education;


@Repository
public interface EducationRepository extends PagingAndSortingRepository<Education, Long>,JpaSpecificationExecutor<Education> {

}
