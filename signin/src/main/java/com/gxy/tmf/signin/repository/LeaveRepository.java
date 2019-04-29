package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gxy.tmf.signin.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer>{
	/**
	 * 不带分页全查询  请假信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<Leave> findAll(Specification<Leave> spec);
}
