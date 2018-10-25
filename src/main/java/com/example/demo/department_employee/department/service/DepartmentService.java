package com.example.demo.department_employee.department.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.beans.Status;
import com.example.demo.common.web.TreeNode;
import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.department.domain.DepartmentBasicInfoDTO;
import com.example.demo.department_employee.department.repository.DepartmentRepository;

@Service("deptService")
@Transactional
public class DepartmentService implements IDepartmentService {
	@Autowired
	private DepartmentRepository deptRepository;

	@Override
	public void save(DepartmentBasicInfoDTO dto) {
		Department department = new Department();
		DepartmentBasicInfoDTO.DtoToEntity(dto, department);
		/*if (dto.getDepartmentParentId() != null) {
			Department departmentParent = deptRepository.findById(dto.getDepartmentParentId()).get();
			dept.setDepartmentParent(departmentParent);
		}*/
		deptRepository.save(department);
	}

	@Override
	public void saveAll(List<Department> depts) {
		deptRepository.saveAll(depts);
	}

	@Override
	public void deleteById(Long id) {
		Department dept = deptRepository.findById(id).get();
		if (dept != null) {
			dept.setDeptStatus(Status.close);
		}
		deptRepository.save(dept);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idList = new ArrayList<>(Arrays.asList(ids));	
		List<Department> depts = (List<Department>) deptRepository.findAllById(idList);
		if (depts.size() >0) {
			for (Department dept : depts) {
				dept.setDeptStatus(Status.close);
			}
		}
		deptRepository.saveAll(depts);
	}
	
	@Override
	public void update(DepartmentBasicInfoDTO dto) {
		Department dept = deptRepository.findById(dto.getId()).get();
		DepartmentBasicInfoDTO.DtoToEntity(dto, dept);
		/*if (dto.getDepartmentParentId() != null) {
			Department departmentParent = deptRepository.findById(dto.getDepartmentParentId()).get();
			dept.setDepartmentParent(departmentParent);
		}*/
		deptRepository.save(dept);
	}

	@Override
	public Optional<Department> findById(Long id) {
		return deptRepository.findById(id);
	}

	@Override
	public List<Department> findAll() {
		return (List<Department>) deptRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return deptRepository.existsById(id);
	}

	@Override
	public long count() {
		return deptRepository.count();
	}

	@Override
	public Page<DepartmentBasicInfoDTO> findAll(Specification<Department> spec, Pageable pageable) {
		List<DepartmentBasicInfoDTO> dtos = new ArrayList<>();
		Page<Department> departmentPage = deptRepository.findAll(spec, pageable);
		List<Department> departments = departmentPage.getContent();
		for (Department department : departments) {
			DepartmentBasicInfoDTO dto = new DepartmentBasicInfoDTO();
			DepartmentBasicInfoDTO.EntityToDTO(department, dto);
			dtos.add(dto);
		}
		
		return new PageImpl<>(dtos, pageable, departmentPage.getTotalElements());
	}

	@Override
	public List<DepartmentBasicInfoDTO> findAllOfBasicInfoDTO() {
		List<Department> departments = (List<Department>) deptRepository.findAll();
		List<DepartmentBasicInfoDTO> departmentBasicInfoDTOs = new ArrayList<>();
		
		for(Department department : departments) {
			if (department.getDeptStatus() == Status.activity) {
				DepartmentBasicInfoDTO dto = new DepartmentBasicInfoDTO();
				DepartmentBasicInfoDTO.EntityToDTO(department, dto);
				departmentBasicInfoDTOs.add(dto);
			}
		}
		
		return departmentBasicInfoDTOs;
	}

	@Override
	public List<TreeNode> findNodes(Long parentId,String flag) {
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		
		List<Department> lists = null;
		if(parentId==null) {
			if (flag.equals("findNodes")) {
				lists =  deptRepository.findParentNodes();
			}else if (flag.equals("findNodesExcludeClose")) {
				lists =  deptRepository.findParentNodesExcludeClose(Status.activity);
			}
		}else {
			if (flag.equals("findNodes")) {
				lists =  deptRepository.findChildNodes(parentId);
			}else if (flag.equals("findNodesExcludeClose")) {
				lists =  deptRepository.findChildNodesExcludeClose(parentId,Status.activity);
			}			
		}
		
		for(Department department : lists) {
			TreeNode node  = new TreeNode();
			
			node.setId(department.getId());
			node.setText(department.getDepartmentName());
			
			if(department.getChildrens() != null) {
				if(department.getChildrens().size() > 0) {
					node.setLeaf(false);//设置为父节点
				}else {
					node.setLeaf(true);//设置为子节点
				}
			}
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public Page<DepartmentBasicInfoDTO> findAll(Pageable pageable) {
		Page<Department> departmentPage = deptRepository.findAll(pageable);
		List<Department> departments = departmentPage.getContent();
		List<DepartmentBasicInfoDTO> dtos = new ArrayList<>();
		for (Department department : departments) {
			DepartmentBasicInfoDTO dto = new DepartmentBasicInfoDTO();
			DepartmentBasicInfoDTO.EntityToDTO(department, dto);
			dtos.add(dto);
		}
		
		return new PageImpl<>(dtos, pageable, departmentPage.getTotalElements());
	}

	@Override
	public Page<DepartmentBasicInfoDTO> findByDepartment(Long departmentId, Pageable pageable) {
		//获取当前节点的信息
		Department departmentParent = deptRepository.findById(departmentId).get();
		DepartmentBasicInfoDTO dtoParent = new DepartmentBasicInfoDTO();
		DepartmentBasicInfoDTO.EntityToDTO(departmentParent, dtoParent);
		
		Page<Department> departmentPage = deptRepository.findChildNodes(departmentId, pageable);
		List<DepartmentBasicInfoDTO> dtos = new ArrayList<>();
		//把当前节点的信息添加到dtos中，跟子节点一同返回到前台
		dtos.add(dtoParent);
		
		if (departmentPage.getContent().size()>0) {
			for (Department department : departmentPage.getContent()) {
				DepartmentBasicInfoDTO dto = new DepartmentBasicInfoDTO();
				DepartmentBasicInfoDTO.EntityToDTO(department, dto);
				dtos.add(dto);
			}
			
			return new PageImpl<>(dtos, pageable, departmentPage.getTotalElements());
		}else {
			return new PageImpl<>(dtos, pageable, departmentPage.getTotalElements());
		}
	}

}
