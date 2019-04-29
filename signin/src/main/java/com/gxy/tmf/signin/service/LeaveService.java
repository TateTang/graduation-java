package com.gxy.tmf.signin.service;

import com.gxy.tmf.signin.model.Leave;
import com.gxy.tmf.signin.util.MessageBean;

public interface LeaveService {
	/**
	 * 查询全部请假信息
	 * @param leaveTime 请假时间
	 * @param leaveContent 请假内容
	 * @param courseId 请假对应的课程
	 * @param stuopneId 请假人对应的opneId
	 * @param status 请假的状态
	 * @param teaopenId 请假老师对应openId
	 * @return
	 */
	MessageBean<Leave> findAll(String leaveTime,String leaveContent,Integer courseId, 
			Integer status,String stuopneId,  String teaopenId);
	
	
	/**
	 * 请假信息保存
	 * @param leave 请假信息实体类
	 * @return
	 */
	MessageBean<Leave> save(Leave leave);
	
	/**
	 * 请假信息删除
	 * @param LeaveId 请假id
	 * @return
	 */
	MessageBean<Void> delete(Integer leaveId);
	
	/**
	 * 单个请假信息查询 根据id
	 * @param leaveId 请假id
	 * @return
	 */
	MessageBean<Leave> findOne(Integer leaveId);
	
	/**
	 * 更新请假信息
	 * @param leave  请假信息实体类
	 * @param leaveId 请假id
	 * @return
	 */
	MessageBean<Leave> update(Leave Leave,Integer leaveId);
}
