package com.example.demo.department_employee.employee.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.department_employee.employee.domain.Employee;
import com.example.demo.department_employee.employee.domain.EmployeeDTO;
import com.example.demo.department_employee.employee.domain.EmployeeQueryDTO;
import com.example.demo.department_employee.employee.service.IEmployeeService;
import com.example.demo.department_employee.mail.IMailService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IMailService mailService;
	
	/**
	 * 增加一个员工
	 */
	@PostMapping
	public ExtAjaxResponse save(@RequestBody EmployeeDTO dto) {
		try {
			if (StringUtils.isNoneBlank(dto.getAccount(),dto.getEmployeeName())) {
				dto.setPricture("imges/default.jpg");
				Employee employee = employeeService.save(dto);
				if (employee != null) {
					mailService.sendHtmlEmail(employee.getEmail(), employee.getAccount(), employee.getPassword(), employee.getEmployeeName());
					return new ExtAjaxResponse(true, "保存成功!");
				}else {
					return new ExtAjaxResponse(false, "保存失败!");
				}
			}else {
				return new ExtAjaxResponse(false, "保存失败！");
			}
			
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "保存失败!");
		}
	}
	/**
	 * 删除一个员工
	 */
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			employeeService.deleteById(id);
			return new ExtAjaxResponse(true, "删除成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "删除失败!");
		}
	}
	/**
	 * 批量删除员工
	 */
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		try {
			if (ids != null) {
				employeeService.deleteAll(ids);
			}else {
				return new ExtAjaxResponse(false, "批量删除失败！");
			}
			return new ExtAjaxResponse(true, "批量删除成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "批量删除失败!");
		}
	}
	
	/**
	 * 修改员工信息
	 */
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id") Long id,@RequestBody EmployeeDTO dto) {
		try {
			employeeService.update(dto);
			
			return new ExtAjaxResponse(true, "修改成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败!");
		}
	}

	/**
	 * 
	 * <p>
	 * Title: updateEmployee
	 * </p>
	 * 
	 * <p>
	 * Description: 修改个人信息
	 * </p>
	 * 
	 * @param dto
	 * @return
	 * 
	 */
	@PostMapping(value = "editPersionalInformation")
	public @ResponseBody ExtAjaxResponse updateEmployee(EmployeeDTO dto) {
		try {
			employeeService.updatePersonalInformation(dto);
			return new ExtAjaxResponse(true, "修改成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败!");
		}
//		return new ExtAjaxResponse(false, "修改失败!");
	}
	/**
	 * 查找一个员工
	 */
	@GetMapping(value="{id}")
	public EmployeeDTO findById(@PathVariable("id") Long id) {
		return employeeService.findById(id);
	}

	/**
	 * 
	 * <p>
	 * Title: findCurrentEmployeeBySession
	 * </p>
	 * 
	 * <p>
	 * Description: 通过session查找当前登录的员工
	 * </p>
	 * 
	 * @return
	 * 
	 */
	@GetMapping("/getCurrentEmployee")
	public EmployeeDTO findCurrentEmployeeBySession() {
//		System.out.println("进来了");
		// 通过session查找当前登录的员工
		return employeeService.findById(4L);
	}

	/**
	 * 分页查询所有员工
	 */
	@GetMapping
	public Page<EmployeeDTO> findPage(EmployeeQueryDTO employeeQueryDTO,ExtjsPageRequest pageRequest) {
			
		return employeeService.findAll(EmployeeQueryDTO.getWhereClause(employeeQueryDTO), pageRequest.getPageable());
	}
	
	@RequestMapping("/getByDepartmentId")
	public List<EmployeeDTO> getByDepartmentId(@RequestParam("departmentId") Long departmentId) {
		if (departmentId == 0) {
			return employeeService.findAll();
		}else {
			return employeeService.findByDepartmentId(departmentId);
		}
	}
}
