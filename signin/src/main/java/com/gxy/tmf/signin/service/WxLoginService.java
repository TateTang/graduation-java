package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.WxLogin;
import com.gxy.tmf.signin.util.MessageBean;

public interface WxLoginService {
	/**
	 * 登录信息保存
	 * @param wxlogin
	 * @return
	 */
	MessageBean<WxLogin> save(WxLogin wxlogin);
	
	/**
	 * 查询openId存在与否
	 * @param openId
	 * @return
	 */
	MessageBean<WxLogin> findByOpenId(String openId);
	
	/**
	 * 根据登录openId更新登录 obj
	 * @param wxLogin
	 * @param openId
	 * @return
	 */
	MessageBean<WxLogin> update(WxLogin wxLogin, String openId);
	
	/**
	 * 根据登录openID更新roleId
	 * @param roleId
	 * @param openId
	 * @return
	 */
	MessageBean<WxLogin> updateRoleObj(Integer roleId, String openId);
}
