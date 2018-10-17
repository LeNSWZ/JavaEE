package com.example.demo.role.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.role.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
