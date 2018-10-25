package com.example.demo.department_employee.job.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.department_employee.job.domain.JobDTO;
import com.example.demo.department_employee.job.domain.JobQueryDTO;
import com.example.demo.department_employee.job.service.IJboService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private IJboService jService;
	
	@PostMapping
	public ExtAjaxResponse save(@RequestBody JobDTO dto) {
		try {
			if (StringUtils.isNoneBlank(dto.getJobName()) && dto.getDepartmentId() > 0) {
				jService.save(dto);
			}else {
				return new ExtAjaxResponse(false, "保存失败！");
			}
			return new ExtAjaxResponse(true, "保存成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "保存失败!");
		}
	}
	
	@DeleteMapping(value= "{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			jService.deleteById(id);
			return new ExtAjaxResponse(true, "删除成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "删除失败!");
		}
	}
	
	@RequestMapping("/deletes")
	public ExtAjaxResponse deleteMore(@RequestParam(name="ids") Long[] ids) {
		try {
			if (ids != null) {
				jService.deleteAll(ids);
			}else {
				return new ExtAjaxResponse(false, "批量删除失败！");
			}
			return new ExtAjaxResponse(true, "批量删除成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "批量删除失败!");
		}
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id") Long id,@RequestBody JobDTO dto) {
		try {
			jService.update(dto);
			return new ExtAjaxResponse(true, "修改成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败!");
		}
	}
	
	@GetMapping(value="{id}")
	public JobDTO findById(@PathVariable("id") Long id) {
		return jService.findById(id);
	}
	
	@GetMapping
	public Page<JobDTO> findAll(JobQueryDTO dto,ExtjsPageRequest pageable){
		return jService.findAll(JobQueryDTO.getWhereClause(dto), pageable.getPageable());
	}
	
	@RequestMapping("/getByDepartmentId")
	public List<JobDTO> getByDepartmentId(@RequestParam("departmentId") Long departmentId) {
		if (departmentId == 0) {
			return jService.findAll();
		}else {
			return jService.findByDepartmentId(departmentId);
		}
	}

}
