package com.example.demo.department.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
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
import com.example.demo.common.web.TreeNode;
import com.example.demo.department.domain.DepartmentBasicInfoDTO;
import com.example.demo.department.domain.DepartmentQueryDTO;
import com.example.demo.department.service.IDepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private IDepartmentService deptService;
	
	
	/**
	 * 增加一个部门
	 */
	@PostMapping
	public ExtAjaxResponse save(@RequestBody DepartmentBasicInfoDTO dto) {
		try {
			if (StringUtils.isNoneBlank(dto.getDepartmentName(), dto.getDepartmentNumber())) {
				deptService.save(dto);
			}else {
				return new ExtAjaxResponse(false, "保存失败！");
			}
			return new ExtAjaxResponse(true, "保存成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "保存失败!");
		}
	}
	/**
	 * 删除一个部门
	 */
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			deptService.deleteById(id);
			return new ExtAjaxResponse(true, "删除成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "删除失败!");
		}
	}
	/**
	 * 批量删除部门
	 */
/*	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		try {
			if (ids != null) {
				deptService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true, "批量删除成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "批量删除失败!");
		}
	}*/
	
	/**
	 * 修改部门信息
	 */
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id") Long id,@RequestBody DepartmentBasicInfoDTO dto) {
		try {
			//Department department = deptService.findById(id).get();
			/*BeanUtils.copyProperties(dept, entity);*/
			//DepartmentBasicInfoDTO.DtoToEntity(dto, department);
			deptService.update(dto);
			
			return new ExtAjaxResponse(true, "修改成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "修改失败!");
		}
	}
	/**
	 * 查找一个部门
	 */
/*	@GetMapping(value="{id}")
	public Department findById(@PathVariable("id") Long id) {
		return deptService.findById(id).get();
	}*/
	/**
	 * 分页查询所有部门
	 */
	@GetMapping
	public Page<DepartmentBasicInfoDTO> findPage(@Nullable Long departmentId,DepartmentQueryDTO deptQueryDTO,ExtjsPageRequest pageRequest) {
		if (departmentId != null) {
			if(departmentId > 0) {
				return deptService.findByDepartment(departmentId,pageRequest.getPageable());			
			}else if (departmentId == 0) {
				return deptService.findAll(pageRequest.getPageable());
			} else {
				return deptService.findAll(DepartmentQueryDTO.getWhereClause(deptQueryDTO), pageRequest.getPageable());
			}
		}else {
			return deptService.findAll(DepartmentQueryDTO.getWhereClause(deptQueryDTO), pageRequest.getPageable());
		}
	}
	
	@RequestMapping("/getDepartmentName")
	public List<DepartmentBasicInfoDTO> getDepartmentName() {
		
		return deptService.findAllOfBasicInfoDTO();
	}
	
	
	/**
	 * 获取子部门
	 * @param node
	 * 		默认根节点：node=root
	 * 		       子节点：node=node.id
	 * @return
	 */
	@RequestMapping("/findNodes")
	public @ResponseBody List<TreeNode> findNodesByParentId(@RequestParam("node") String node)
	{
		String flag = "findNodes";
		if(node.equals("root")) {
			return deptService.findNodes(null,flag);
		}else {
			return deptService.findNodes(Long.parseLong(node),flag);
		}
	}
	
	/**
	 * 获取子部门并排除已关闭的部门
	 * @param node
	 * 		默认根节点：node=root
	 * 		       子节点：node=node.id
	 * @return
	 */
	@RequestMapping("/findNodesExcludeClose")
	public @ResponseBody List<TreeNode> findNodesExcludeClose(@RequestParam("node") String node)
	{
		String flag = "findNodesExcludeClose";
		if(node.equals("root")) {
			return deptService.findNodes(null,flag);
		}else {
			return deptService.findNodes(Long.parseLong(node),flag);
		}
	}
	
	
}
