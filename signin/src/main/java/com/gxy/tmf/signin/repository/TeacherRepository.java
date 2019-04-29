package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxy.tmf.signin.model.Teacher;


public interface TeacherRepository extends JpaRepository <Teacher, Integer>{
	/**
	 * 不带分页全查询 教师信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<Teacher> findAll(Specification<Teacher> spec);
	
	/**
	 * 根据openId查询教师信息
	 * @param openId
	 * @return
	 */
	@Query("select t from Teacher t where t.openid=?1")
	Teacher findByOpenId(String openId);
}
