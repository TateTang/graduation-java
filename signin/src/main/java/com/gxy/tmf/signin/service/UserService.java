package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.User;
import com.gxy.tmf.signin.util.MessageBean;

public interface UserService {
	/**
	 * 查询全部用户信息
	 * @param account 用户名
	 * @param name	姓名
	 * @param roleId 角色id
	 * @param openId 小程序openId
	 * @return
	 */
	MessageBean<User> findAll(String account,  String name,	Integer roleId,String openId);
	
	/**
	 * 用户信息保存
	 * @param user
	 * @return
	 */
	MessageBean<User> save(User user);
	
	/**
	 * 查询openId存在与否
	 * @param openId
	 * @return
	 */
	MessageBean<User> findByOpenId(String openId);
	
	
	/**
	 * 根据用户Id更新用户信息
	 * @param user
	 * @param openId
	 * @return
	 */
	MessageBean<User> update(User user, String openId);
}
