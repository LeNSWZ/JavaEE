package com.example.demo.role.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.role.domain.Group;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> , JpaSpecificationExecutor<Group> {

}
