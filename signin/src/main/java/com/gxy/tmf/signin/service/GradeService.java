package com.gxy.tmf.signin.service;

import com.gxy.tmf.signin.model.Grade;
import com.gxy.tmf.signin.util.MessageBean;

public interface GradeService {
	/**
	 * 查询全部班级信息
	 * @param name 班级名称
	 * @param counttotal 班级总人数
	 * @param countnow 班级当前人数
	 * @param teaopenId 对应教师openId
	 * @return
	 */
	MessageBean<Grade> findAll(String name,Integer counttotal,Integer countnow, String teaopenId);
	
	
	/**
	 * 班级信息保存
	 * @param grade 班级信息实体类
	 * @return
	 */
	MessageBean<Grade> save(Grade grade);
	
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
