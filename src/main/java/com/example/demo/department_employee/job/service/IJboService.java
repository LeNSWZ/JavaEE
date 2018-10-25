package com.example.demo.department_employee.job.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.department_employee.job.domain.Job;
import com.example.demo.department_employee.job.domain.JobDTO;

public interface IJboService {
	public void save(JobDTO dto);
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public void update(JobDTO dto);
	public JobDTO findById(Long id);
	public List<JobDTO> findAll();
	public Page<JobDTO> findAll(Specification<Job> spec,Pageable pageable);
	public List<JobDTO> findByDepartmentId(Long departmentId);
}
