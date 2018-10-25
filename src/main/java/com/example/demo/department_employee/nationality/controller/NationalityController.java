package com.example.demo.department_employee.nationality.controller;

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
import com.example.demo.department_employee.nationality.domain.NationalityDTO;
import com.example.demo.department_employee.nationality.domain.NationalityQueryDTO;
import com.example.demo.department_employee.nationality.service.INationalityService;

@RestController
@RequestMapping("/nationality")
public class NationalityController {
	@Autowired
	private INationalityService nationalityService;
	
	@PostMapping
	public ExtAjaxResponse save(@RequestBody NationalityDTO dto) {
		try {
			if (dto.getNationName() != null) {
				nationalityService.save(dto);
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
			nationalityService.deleteById(id);
			return new ExtAjaxResponse(true, "删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "删除失败！");
		}
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id")Long id,@RequestBody NationalityDTO dto) {
		try {
			nationalityService.update(dto);
			return new ExtAjaxResponse(true, "修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败！");
		}
	}
	
	@GetMapping(value="{id}")
	public NationalityDTO findById(@PathVariable("id") Long id) {
		return nationalityService.findById(id);
	}
	
	@GetMapping
	public Page<NationalityDTO> findAll(NationalityQueryDTO queryDTO,ExtjsPageRequest pageRequest) {
		return nationalityService.findAll(NationalityQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
	}
}
