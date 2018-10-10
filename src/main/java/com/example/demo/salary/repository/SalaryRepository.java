package com.example.demo.salary.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.salary.domain.Salary;

@Repository
public interface SalaryRepository extends PagingAndSortingRepository<Salary, Long>//分页和排序
										  ,JpaSpecificationExecutor<Salary>//动态查询
{



}
