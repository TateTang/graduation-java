package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.Teacher;
import com.gxy.tmf.signin.util.MessageBean;

public interface TeacherService {
	/**
	 * 查询全部教师信息
	 * @param account 工号
	 * @param name	姓名
	 * @param roleId 角色id
	 * @param teaopenId 小程序openId
	 * @return
	 */
	MessageBean<Teacher> findAll(String account,  String name, Integer roleId, String teaopenId);
	
	/**
	 * 教师信息保存
	 * @param teacher
	 * @return
	 */
	MessageBean<Teacher> save(Teacher teacher);
	
	/**
	 * 查询openId存在与否
	 * @param openId
	 * @return
	 */
	MessageBean<Teacher> findByOpenId(String openId);
	
	
	/**
	 * 根据教师Id更新教师信息
	 * @param teacher
	 * @param openId
	 * @return
	 */
	MessageBean<Teacher> update(Teacher teacher, String openId);
}
