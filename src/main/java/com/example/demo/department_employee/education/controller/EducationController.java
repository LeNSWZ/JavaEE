package com.example.demo.department_employee.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.department_employee.education.domain.EducationDTO;
import com.example.demo.department_employee.education.domain.EducationQueryDTO;
import com.example.demo.department_employee.education.service.IEducationService;

@RestController
@RequestMapping("/education")
public class EducationController {
	@Autowired
	private IEducationService educationService;
	
	@PostMapping
	public ExtAjaxResponse save(@RequestBody EducationDTO dto) {
		try {
			if (dto.getEducationName() != null) {
				educationService.save(dto);
			}else {
				return new ExtAjaxResponse(false, "保存失败！");
			}
			return new ExtAjaxResponse(true, "保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "保存失败！");
		}
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse deleteById(@PathVariable("id") Long id) {
		try {
			educationService.deleteById(id);
			return new ExtAjaxResponse(true, "删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "删除失败！");
		}
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id")Long id,@RequestBody EducationDTO dto) {
		try {
			educationService.update(dto);
			return new ExtAjaxResponse(true, "修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败！");
		}
	}
	
	@GetMapping(value="{id}")
	public EducationDTO findById(@PathVariable("id") Long id) {
		return educationService.findById(id);
	}
	
	@GetMapping
	public Page<EducationDTO> findAll(EducationQueryDTO queryDTO,ExtjsPageRequest pageRequest) {
		return educationService.findAll(EducationQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
	}
	
	@RequestMapping("/getEducationName")
	public List<EducationDTO> getEducationName(){
		return educationService.findAll();
	}
}
