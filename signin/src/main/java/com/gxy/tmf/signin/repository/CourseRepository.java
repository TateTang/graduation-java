package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxy.tmf.signin.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	/**
	 * 不带分页全查询 班级信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<Course> findAll(Specification<Course> spec);
	
	/**
	 * 查询单个的课程信息
	 * @param gradeId 课程id
	 * @return
	 */
	@Query("select c from Course c where c.id = ?1")
	Course findByCourseId(Integer courseId);
	
	/**
	 * 查询课程信息
	 * @return
	 */
	@Query("select c from Course c where c.deleteflag = false")
	List<Course> findName();
}
