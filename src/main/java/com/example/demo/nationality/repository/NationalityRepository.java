package com.example.demo.nationality.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.nationality.domain.Nationality;

@Repository
public interface NationalityRepository extends PagingAndSortingRepository<Nationality, Long>,JpaSpecificationExecutor<Nationality> {

}
