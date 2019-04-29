package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxy.tmf.signin.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
	/**
	 * 不带分页全查询 班级信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<Grade> findAll(Specification<Grade> spec);
	
	/**
	 * 查询单个的班级信息
	 * @param gradeId 班级id
	 * @return
	 */
	@Query("select g from Grade g where g.id = ?1")
	Grade findByGradeId(Integer gradeId);
	
	/**
	 * 查询所有班级的名称
	 * @return
	 */
	@Query("select g from Grade g where g.deleteflag = false")
	List<Grade> findName();
}
