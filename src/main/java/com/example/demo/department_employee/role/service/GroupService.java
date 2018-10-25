package com.example.demo.department_employee.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.role.domain.Group;
import com.example.demo.department_employee.role.domain.GroupDTO;
import com.example.demo.department_employee.role.repository.GroupRepository;

@Service
@Transactional
public class GroupService implements IGroupService {
	@Autowired
	private GroupRepository groupRepository;
		
	@Override
	public void save(GroupDTO dto) {
		Group group = GroupDTO.dtoToEntity(dto);
		groupRepository.save(group);
	}

	@Override
	public void deleteById(Long id) {
		groupRepository.deleteById(id);
	}

	@Override
	public void update(GroupDTO dto) {
		Group group = GroupDTO.dtoToEntity(dto);
		groupRepository.save(group);
	}

	@Override
	public GroupDTO findById(Long id) {
		Group group = groupRepository.findById(id).get();
		GroupDTO dto = GroupDTO.entityToDTO(group);
		return dto;
	}

	@Override
	public Page<GroupDTO> findAll(Specification<Group> spec, Pageable pageable) {
		Page<Group> groupPage = groupRepository.findAll(spec, pageable);
		List<GroupDTO> dtos = new ArrayList<>();
		for (Group group : groupPage.getContent()) {
			GroupDTO dto = GroupDTO.entityToDTO(group);
			dtos.add(dto);
		}
		
		return new PageImpl<>(dtos, pageable, groupPage.getTotalElements());
	}

}
