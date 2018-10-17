package com.example.demo.job.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.job.domain.Job;
import com.example.demo.job.repository.JobRepository;

@Service
@Transactional
public class JobService implements IJboService {
	@Autowired
	private JobRepository jobrepository;
	
	@Override
	public void save(Job job) {
		jobrepository.save(job);
	}

	@Override
	public void deleteById(Long id) {
		jobrepository.deleteById(id);
	}

	@Override
	public Optional<Job> findById(Long id) {
		return jobrepository.findById(id);
	}

	@Override
	public List<Job> findAll() {
		return (List<Job>) jobrepository.findAll();
	}

}
