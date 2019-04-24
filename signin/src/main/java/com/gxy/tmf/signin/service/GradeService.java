package com.gxy.tmf.signin.service;

import com.gxy.tmf.signin.model.Grade;
import com.gxy.tmf.signin.util.MessageBean;

public interface GradeService {
	/**
	 *  查询全部班级信息 
	 * @param name 班级名称
	 * @param count 班级人数
	 * @param teacherName 班级老师姓名
	 * @param countNow 班级当前人数
	 * @return
	 */
	MessageBean<Grade> findAll(String name,Integer count,String teacherName, Integer countNow);
	
	
	/**
	 * 班级信息保存
	 * @param grade 班级信息实体类
	 * @param openid 用户openid
	 * @return
	 */
	MessageBean<Grade> save(Grade grade,String openId);
	
	/**
	 * 班级信息删除
	 * @param gradeId 班级id
	 * @return
	 */
	MessageBean<Void> delete(Integer gradeId);
	
	/**
	 * 单个班级信息查询 根据id
	 * @param gradeId 班级id
	 * @return
	 */
	MessageBean<Grade> findOne(Integer gradeId);
	
	/**
	 * 更新班级信息
	 * @param grade  班级信息实体类
	 * @param gradeId 班级id
	 * @return
	 */
	MessageBean<Grade> update(Grade grade,Integer gradeId);
	
	/**
	 * 查询班级的名称
	 * @return
	 */
	MessageBean<Grade> findName();
}
