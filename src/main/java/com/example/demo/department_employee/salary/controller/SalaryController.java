package com.example.demo.department_employee.salary.controller;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.department_employee.salary.domain.Salary;
import com.example.demo.department_employee.salary.domain.SalaryDTO;
import com.example.demo.department_employee.salary.domain.SalaryQueryDTO;
import com.example.demo.department_employee.salary.service.ISalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	
	@Autowired
	private ISalaryService salaryService;

	@GetMapping
	public Page<SalaryDTO> findAll(SalaryQueryDTO salaryQueryDTO,ExtjsPageRequest pageRequest) {
		return salaryService.findAll(SalaryQueryDTO.getWhereClause(salaryQueryDTO), pageRequest.getPageable());
	}
	

	@GetMapping(value="{id}")
	public Optional<Salary> findById(@PathVariable("id") Long id) {
		return salaryService.findById(id);
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("id")Long id,@RequestBody SalaryDTO dto) {
		try {
			salaryService.update(dto);
			return new ExtAjaxResponse(true, "修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败！");
		}
	}

	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody SalaryDTO dto) 
	{
		try {
			if(StringUtils.isNoneBlank(dto.getUserId(),dto.getUserName()))
				salaryService.save(dto);
			else {
				return new ExtAjaxResponse(true,"保存失败！");
			}
			return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}	

	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) 
	{
		try {
			if(id!=null) {
				salaryService.deleteById(id);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				salaryService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
}

