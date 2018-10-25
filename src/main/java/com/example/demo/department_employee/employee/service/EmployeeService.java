package com.example.demo.department_employee.employee.service;

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

import com.example.demo.department_employee.department.domain.Department;
import com.example.demo.department_employee.department.repository.DepartmentRepository;
import com.example.demo.department_employee.education.domain.Education;
import com.example.demo.department_employee.education.repository.EducationRepository;
import com.example.demo.department_employee.employee.domain.Employee;
import com.example.demo.department_employee.employee.domain.EmployeeDTO;
import com.example.demo.department_employee.employee.repositoty.EmployeeRepository;
import com.example.demo.department_employee.job.domain.Job;
import com.example.demo.department_employee.job.repository.JobRepository;
import com.example.demo.department_employee.nationality.domain.Country;
import com.example.demo.department_employee.nationality.domain.Nation;
import com.example.demo.department_employee.nationality.domain.Nationality;
import com.example.demo.department_employee.nationality.repository.CountryRepository;
import com.example.demo.department_employee.nationality.repository.NationRepository;
import com.example.demo.department_employee.role.domain.Group;
import com.example.demo.department_employee.role.domain.Role;
import com.example.demo.department_employee.role.repository.GroupRepository;
import com.example.demo.department_employee.role.repository.RoleRepository;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository deptRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private JobRepository jRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EducationRepository educationRepository;
	@Autowired
	private NationRepository nationRepository;
	
	@Override
	public Employee save(EmployeeDTO dto) {
		Employee entity = new Employee();
		String password = entity.getPassword();
		EmployeeDTO.DtoToEntity(dto,entity);
		entity.setPassword(password);
		Group group = entity.getGroupId();
		group.setEmployee(entity);
		Employee employee = employeeRepository.save(entity);
		if (employee.getId() != null) {
			return employee;
		}
		return null;
	}

	@Override
	public void saveAll(List<Employee> employees) {
		employeeRepository.saveAll(employees);
	}

	@Override
	public void deleteById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		employee.setDepartmentId(null);
		List<Employee> subordinates = employee.getSubordinate();
		for (Employee subordinate : subordinates) {
			subordinate.setEmployeeLeader(null);
		}
		employee.setSubordinate(null);
		Group group = groupRepository.findById(employee.getGroupId().getId()).get();
		employee.setGroupId(null);
		group.setEmployee(null);
		groupRepository.delete(group);
		employeeRepository.delete(employee);		
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idList = new ArrayList<>(Arrays.asList(ids));	
		List<Employee> employees = (List<Employee>) employeeRepository.findAllById(idList);
		List<Long> groupId = new ArrayList<>();
		if (employees.size() > 0) {
			for (Employee employee : employees) {
				employee.setDepartmentId(null);
				groupId.add(employee.getGroupId().getId());
				employee.setGroupId(null);
				
				List<Employee> subordinates = employee.getSubordinate();
				for (Employee subordinate : subordinates) {
					subordinate.setEmployeeLeader(null);
				}
				employee.setSubordinate(null);
			}
			List<Group> groups = (List<Group>) groupRepository.findAllById(groupId);
			for (Group group : groups) {
				group.setEmployee(null);
			}
			groupRepository.deleteAll(groups);
			employeeRepository.deleteAll(employees);
		}		
	}
	
	@Override
	public void update(EmployeeDTO dto) {
		Employee employee = employeeRepository.findById(dto.getId()).get();
		EmployeeDTO.DtoToEntity(dto,employee);
		
		if (dto.getDepartment_id() != null && dto.getDepartment_id() > 0) {
			Department department = deptRepository.findById(dto.getDepartment_id()).get();
			employee.setDepartmentId(department);
		}
		if (dto.getJob_id() != null && dto.getJob_id() >0) {
			Job job = jRepository.findById(dto.getJob_id()).get();
			employee.setJobId(job);
		}		
		if (dto.getEducation_id() != null && dto.getEducation_id() > 0) {
			Education education = educationRepository.findById(dto.getEducation_id()).get();			
			employee.setEducationId(education);
		}
		if (dto.getRole_id() > 0) {
			Group group = new Group();
			Role role = roleRepository.findById(dto.getRole_id()).get();
			group.setRole(role);
			employee.setGroupId(group);
		}
		if (dto.getCountry_id() > 0) {
			Nationality nationality = new Nationality();
			Country country = countryRepository.findById(dto.getCountry_id()).get();
			nationality.setCountry(country);
			
			if (dto.getNation_id() > 0) {
				Nation nation = new Nation();
				nation.setId(dto.getNation_id());
				nationality.setNation(nation);
			}
			
			employee.setNationalityId(nationality);
		}
		if (dto.getEmployeeLeaderId() != null && dto.getEmployeeLeaderId() > 0) {
			Employee employeeLeader = employeeRepository.findById(dto.getEmployeeLeaderId()).get();
			employee.setEmployeeLeader(employeeLeader);
		}		
		
		employeeRepository.save(employee);
	}

	@Override
	public void updatePersonalInformation(EmployeeDTO dto) {
		Employee employee = employeeRepository.findById(dto.getId()).get();
		EmployeeDTO.DtoToEntity(dto, employee);

		// 修改国籍和民族
		Nationality nationality = null;
		Country country = new Country();
		Nation nation = new Nation();
		if (null != employee.getNationalityId() && null != employee.getNationalityId().getId()) {
			nationality = employee.getNationalityId();
		} else {
			nationality = new Nationality();
		}
		if (dto.getCountry_id() > 0) {
			country = countryRepository.findById(dto.getCountry_id()).get();
		}
		if (dto.getNation_id() > 0) {
			nation = nationRepository.findById(dto.getNation_id()).get();
		}
		nationality.setCountry(country);
		nationality.setNation(nation);
		employee.setNationalityId(nationality);

		// 修改学历
		Education education = null;
		if (null != employee.getEducationId() && null != employee.getEducationId().getId()) {
			education = employee.getEducationId();
		} else {
			education = new Education();
		}
		if (null != dto.getEducationName()) {
			education.setEducationName(dto.getEducationName());
		}
		if (null != dto.getUniversity()) {
			education.setUniversity(dto.getUniversity());
		}
		if (null != dto.getInstitute()) {
			education.setInstitute(dto.getInstitute());
		}
		if (null != dto.getMajor()) {
			education.setMajor(dto.getMajor());
		}
		employee.setEducationId(education);
		employeeRepository.save(employee);
	}

	@Override
	public EmployeeDTO findById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		EmployeeDTO dto = new EmployeeDTO();
		EmployeeDTO.EntityToDTO(employee,dto);
		return dto;
	}

	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
		List<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee entity : employeeList) {
			EmployeeDTO dto = new EmployeeDTO();
			EmployeeDTO.EntityToDTO(entity,dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public boolean existsById(Long id) {
		return employeeRepository.existsById(id);
	}

	@Override
	public long count() {
		return employeeRepository.count();
	}

	@Override
	public Page<EmployeeDTO> findAll(Specification<Employee> spec, Pageable pageable) {
		List<EmployeeDTO> dtos = new ArrayList<>();
		Page<Employee> employeePage = employeeRepository.findAll(spec, pageable);
		List<Employee> employees = employeePage.getContent();
		if (employees.size() > 0) {
			for (Employee employee : employees) {
				EmployeeDTO dto = new EmployeeDTO();
				EmployeeDTO.EntityToDTO(employee,dto);
				dtos.add(dto);
			}
		}
		
		return new PageImpl<>(dtos, pageable, employeePage.getTotalElements());
	}
	
	@Override
	public List<EmployeeDTO> findByDepartmentId(Long departmentId){
		List<Employee> employees = employeeRepository.findByDepartment_id(departmentId);
		List<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDTO dto = new EmployeeDTO();
			EmployeeDTO.EntityToDTO(employee, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public EmployeeDTO findByAccount(String username) {
		EmployeeDTO dto = new EmployeeDTO();
		EmployeeDTO.EntityToDTO(employeeRepository.findByAccount(username), dto);
		return dto;
	}

	@Override
	public String findRoleByIdAccount(String username) {
		Employee employee = employeeRepository.findByAccount(username);
		String role = employee.getGroupId().getRole().getRoleName();
		return role;
	}

}
