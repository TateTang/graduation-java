package com.gxy.tmf.signin.service;


import com.gxy.tmf.signin.model.WxLogin;
import com.gxy.tmf.signin.util.MessageBean;

public interface WxLoginService {
	/**
	 * 登录信息保存
	 * @param user
	 * @return
	 */
	MessageBean<WxLogin> save(WxLogin Wxlogin);
	
	/**
	 * 查询openId存在与否
	 * @param openId
	 * @return
	 */
	MessageBean<WxLogin> findByOpenId(String openId);
	
}
