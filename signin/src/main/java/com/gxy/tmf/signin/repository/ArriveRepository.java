package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxy.tmf.signin.model.Arrive;

public interface ArriveRepository extends JpaRepository<Arrive, Integer>{
	/**
	 * 不带分页全查询
	 * @param spec 条件构造器
	 * @return
	 */
	List<Arrive> findAll(Specification<Arrive> spec);
	
	/**
	 * 查询签到信息根据学生openId和课程id
	 * @param stuopenId
	 * @param courseIds
	 * @return
	 */
	@Query("select  a from Arrive a where a.studentobj.openid=?1 and a.courseobj.id in ?2")
	List<Arrive> findByStuIdAndCourseId(String stuopenId,List<Integer> courseIds);
}
