package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxy.tmf.signin.model.Student;


public interface StudentRepository extends JpaRepository <Student, Integer>{
	/**
	 * 不带分页全查询 学生信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<Student> findAll(Specification<Student> spec);
	
	/**
	 * 根据openId查询学生信息
	 * @param openId
	 * @return
	 */
	@Query("select s from Student s where s.openid=?1")
	Student findByOpenId(String openId);
}
