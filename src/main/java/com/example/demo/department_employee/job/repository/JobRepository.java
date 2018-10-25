package com.example.demo.department_employee.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.job.domain.Job;

@Repository("JRepository")
public interface JobRepository extends PagingAndSortingRepository<Job, Long>,JpaSpecificationExecutor<Job> {
	@Query("from Job job where job.department.id = ?1")
	public List<Job> findByDepartmentId(Long departmentId);
}
