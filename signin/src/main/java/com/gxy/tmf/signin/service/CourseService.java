package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.Course;
import com.gxy.tmf.signin.util.MessageBean;

public interface CourseService {
	/**
	 * 查询全部课程信息
	 * @param name 课程名
	 * @param yzm 签到码
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param gradeId 对应的班级id
	 * @param teaopenId 对应的教师的openId
	 * @return
	 */
	MessageBean<Course> findAll(String name,String yzm,String startTime,String endTime,Integer gradeId,String teaopenId);
	
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
	
	/**
	 * 查询课程的名称
	 * @return
	 */
	MessageBean<Course> findName();
	
	/**
	 * 查询该学生未签到的课程信息
	 * @param stuopenId
	 * @return
	 */
	MessageBean<Course> findNoQianDaoCourse(String stuopenId,Integer gradeId);
	
	/**
	 * 根据验证码和课程id查询签到信息
	 * @param courseId 课程id
	 * @param yzm	签到码
	 * @return
	 */
	MessageBean<Course> findByIdAndYzm(Integer courseId,String yzm);
}
