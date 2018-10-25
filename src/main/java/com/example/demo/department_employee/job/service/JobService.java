package com.example.demo.department_employee.job.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.job.domain.Job;
import com.example.demo.department_employee.job.domain.JobDTO;
import com.example.demo.department_employee.job.repository.JobRepository;

@Service("jService")
@Transactional
public class JobService implements IJboService {
	//@Resource(name="jRepository")
	@Autowired
	private JobRepository jRepository;
	
	@Override
	public void save(JobDTO dto) {
		Job job = new Job();
		JobDTO.dtoToEntity(dto, job);
		jRepository.save(job);
	}

	@Override
	public void deleteById(Long id) {
		Job job = jRepository.findById(id).get();
		job.setDepartment(null);
		jRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idList = new ArrayList<>(Arrays.asList(ids));	
		List<Job> jobs = (List<Job>) jRepository.findAllById(idList);
		if (jobs.size() > 0) {
			for (Job job : jobs) {
				job.setDepartment(null);
			}
			jRepository.deleteAll(jobs);
		}		
	}
	
	@Override
	public void update(JobDTO dto) {
		Job job = jRepository.findById(dto.getId()).get();
		JobDTO.dtoToEntity(dto, job);
		jRepository.save(job);
	}
	
	@Override
	public JobDTO findById(Long id) {
		Job job = jRepository.findById(id).get();
		JobDTO dto = new JobDTO();
		JobDTO.entityToDTO(job, dto);
		return dto;
	}

	@Override
	public List<JobDTO> findAll() {
		List<Job> jobs = (List<Job>) jRepository.findAll();
		List<JobDTO> dtos = new ArrayList<>();
		for (Job job : jobs) {
			JobDTO dto = new JobDTO();
			JobDTO.entityToDTO(job, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Page<JobDTO> findAll(Specification<Job> spec, Pageable pageable) {
		Page<Job> jobPage = jRepository.findAll(spec, pageable);
		List<JobDTO> dtos = new ArrayList<>();
		for (Job job : jobPage.getContent()) {
			JobDTO dto = new JobDTO();
			JobDTO.entityToDTO(job, dto);
			dtos.add(dto);
		}
		return new PageImpl<>(dtos, pageable, jobPage.getTotalElements());
	}
	
	@Override
	public List<JobDTO> findByDepartmentId(Long departmentId){
		List<Job> jobs = jRepository.findByDepartmentId(departmentId);
		List<JobDTO> dtos = new ArrayList<>();
		for (Job job : jobs) {
			JobDTO dto = new JobDTO();
			JobDTO.entityToDTO(job, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
