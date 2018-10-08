package com.example.demo.notice.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;			//注解：
import org.springframework.stereotype.Component;
//import org.springframework.data.repository.Repository;	//接口		
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import com.example.demo.notice.domain.Notice;

@Repository
public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long>//分页和排序
											,JpaSpecificationExecutor<Notice>//动态查询
{

		

}
