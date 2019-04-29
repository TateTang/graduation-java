package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.Student;
import com.gxy.tmf.signin.util.MessageBean;

public interface StudentService {
	/**
	 * 查询全部学生信息
	 * @param account 学生名
	 * @param name	姓名
	 * @param roleId 角色id
	 * @param openId 小程序登录openId
	 * @param gradeId 班级IdgradeId
	 * @return
	 */
	MessageBean<Student> findAll(String account,  String name,Integer roleId, String stuopenId, Integer gradeId);
	
	/**
	 * 学生信息保存
	 * @param student
	 * @return
	 */
	MessageBean<Student> save(Student student);
	
	/**
	 * 查询openId存在与否
	 * @param openId
	 * @return
	 */
	MessageBean<Student> findByOpenId(String openId);
	
	
	/**
	 * 根据学生Id更新学生信息
	 * @param student
	 * @param openId
	 * @return
	 */
	MessageBean<Student> update(Student student, String openId);
}
