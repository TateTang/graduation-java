package com.gxy.tmf.signin.service;

import com.gxy.tmf.signin.model.Arrive;
import com.gxy.tmf.signin.util.MessageBean;

public interface ArriveService {
	/**
	 * 查询全部签到信息
	 * @param arrvieTime 签到时间
	 * @param courseId 签到对应的课程
	 * @param stuopenId 签到对应的openId
	 * @param status  签到的状态
	 * @return
	 */
	MessageBean<Arrive> findAll(String arrvieTime, Integer courseId,String stuopenId, Integer status);
	
	/**
	 * 签到信息保存
	 * @param arrive 签到信息实体类
	 * @return
	 */
	MessageBean<Arrive> save(Arrive arrive);
	
	/**
	 * 签到信息删除
	 * @param arriveId 签到id
	 * @return
	 */
	MessageBean<Void> delete(Integer arriveId);
	
	/**
	 * 单个签到信息查询 根据id
	 * @param arriveId 签到id
	 * @return
	 */
	MessageBean<Arrive> findOne(Integer arriveId);
	
	/**
	 * 更新签到信息
	 * @param arrive  签到信息实体类
	 * @param arriveId 签到id
	 * @return
	 */
	MessageBean<Arrive> update(Arrive arrive,Integer arriveId);
}
