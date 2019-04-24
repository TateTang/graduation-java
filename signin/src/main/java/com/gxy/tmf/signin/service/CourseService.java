package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.Course;
import com.gxy.tmf.signin.util.MessageBean;

public interface CourseService {
	
	/**
	 * 查询全部课程信息
	 * @param name 课程名称
	 * @param week 星期
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @param gradeId	班级名称
	 * @return
	 */
	MessageBean<Course> findAll(String name,String week,String startTime,String endTime,Integer gradeId);
	
	
	/**
	 * 课程信息保存
	 * @param Course 课程信息实体类
	 * @return
	 */
	MessageBean<Course> save(Course course);
	
	/**
	 * 课程信息删除
	 * @param CourseId 课程id
	 * @return
	 */
	MessageBean<Void> delete(Integer courseId);
	
	/**
	 * 单个课程信息查询 根据id
	 * @param CourseId 课程id
	 * @return
	 */
	MessageBean<Course> findOne(Integer courseId);
	
	/**
	 * 更新课程信息
	 * @param Course  课程信息实体类
	 * @param CourseId 课程id
	 * @return
	 */
	MessageBean<Course> update(Course course,Integer courseId);
}
