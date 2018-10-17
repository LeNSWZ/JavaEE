package com.example.demo.job.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.job.domain.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

}
