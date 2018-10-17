package com.example.demo.role.controller;

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
import com.example.demo.role.domain.GroupDTO;
import com.example.demo.role.domain.GroupQueryDTO;
import com.example.demo.role.service.IGroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private IGroupService groupService;
	
	@PostMapping
	public ExtAjaxResponse save(@RequestBody GroupDTO dto) {
		try {
			if (dto.getRoleId() >0 && dto.getEmployeeId() >0 && dto.getEmployeeId() != null) {
				groupService.save(dto);
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
			groupService.deleteById(id);
			return new ExtAjaxResponse(true, "删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "删除失败！");
		}
	}
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id")Long id,@RequestBody GroupDTO dto) {
		try {
			groupService.update(dto);
			return new ExtAjaxResponse(true, "修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败！");
		}
	}
	
	@GetMapping(value="{id}")
	public GroupDTO findById(@PathVariable("id") Long id) {
		return groupService.findById(id);
	}
	
	@GetMapping
	public Page<GroupDTO> findAll(GroupQueryDTO queryDTO,ExtjsPageRequest pageRequest) {
		return groupService.findAll(GroupQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
	}
	
}
