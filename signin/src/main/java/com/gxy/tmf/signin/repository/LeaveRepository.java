package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gxy.tmf.signin.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer>{
	/**
	 * 不带分页全查询  请假信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<Leave> findAll(Specification<Leave> spec);
	
	/**
	 * 根据leaveid更新状态
	 * @param roleId
	 * @param openId
	 * @return
	 */
	@Query("update Leave l set l.status = ?2 where l.id=?1")
	@Modifying
	@Transactional
	Leave updateRoleObj(Integer leaveId, Integer status);
}
