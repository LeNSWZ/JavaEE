package com.example.demo.department.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.common.beans.Status;
import com.example.demo.department.domain.Department;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>,JpaSpecificationExecutor<Department> {
	/**
	 * 查询根节点
	 * 		departmentParent.id = null时 默认返回根节点
	 * @return 节点集合 List<Department>
	 */
	@Query("from Department dept where dept.departmentParent.id = null")
	public List<Department> findParentNodes();
	
	/**
	 * 根据父节点ID查询出子节点
	 * @param parentId
	 * 		parentId != null时 默认返回子节点
	 * @return 节点集合 List<Department>
	 */
	@Query("from Department dept where dept.departmentParent.id = ?1")
	public List<Department> findChildNodes(Long parentId);//null
	
	/**
	 * 根据父节点ID查询出子节点
	 * @param parentId
	 * 		parentId != null时 默认返回子节点
	 * @return 节点集合 List<Department>
	 */
	@Query("from Department dept where dept.departmentParent.id = ?1")
	public Page<Department> findChildNodes(Long parentId,Pageable pageable);//null

	/**
	 * 查询根节点并排除已关闭的部门
	 * 		departmentParent.id = null时 默认返回根节点
	 * @return 节点集合 List<Department>
	 */
	@Query("from Department dept where dept.departmentParent.id = null and dept.deptStatus = ?1")
	public List<Department> findParentNodesExcludeClose(Status status);
	
	/**
	 * 根据父节点ID查询出子节点并排除已关闭的部门
	 * @param parentId
	 * 		parentId != null时 默认返回子节点
	 * @return 节点集合 List<Department>
	 */
	@Query("from Department dept where dept.departmentParent.id = ?1 and dept.deptStatus = ?2")
	public List<Department> findChildNodesExcludeClose(Long parentId,Status status);//null
}
