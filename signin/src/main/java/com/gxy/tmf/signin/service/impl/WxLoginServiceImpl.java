package com.gxy.tmf.signin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxy.tmf.signin.model.Role;
import com.gxy.tmf.signin.model.WxLogin;
import com.gxy.tmf.signin.repository.RoleRepository;
import com.gxy.tmf.signin.repository.WxLoginRepository;
import com.gxy.tmf.signin.service.WxLoginService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 用户信息管理实现类
 * @Data: 2019年3月20日 下午5:19:12
 */
@Service
public class WxLoginServiceImpl implements WxLoginService {
	// 注册
	@Autowired
	private WxLoginRepository wxLoginRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public MessageBean<WxLogin> save(WxLogin wxLogin) {
		// TODO Auto-generated method stub
		if(Util.isNotEmpty(wxLogin.getOpenid())) {
			wxLogin.setCreateDate(new Date());
			wxLogin = wxLoginRepository.saveAndFlush(wxLogin);
			return new  MessageBean<WxLogin>("200","插入登录信息成功",wxLogin);
		}else {
			return new  MessageBean<WxLogin>("error","插入登录信息失败");
		}
	}
	@Override
	public MessageBean<WxLogin> findByOpenId(String openId) {
		// TODO Auto-generated method stub
		WxLogin wxLogin = wxLoginRepository.findByOpenId(openId);
		return new  MessageBean<WxLogin>("200","查询单个登录信息成功",wxLogin);
	}
	@Override
	public MessageBean<WxLogin> update(WxLogin wxLogin, String openId) {
		// TODO Auto-generated method stub
		WxLogin wxLogin_db = wxLoginRepository.findByOpenId(openId);
		if(Util.isNotEmpty(wxLogin_db)) {
			if(Util.isNotEmpty(wxLogin_db.getRoleobj())) {
				wxLogin_db.setRoleobj(wxLogin_db.getRoleobj());
			}
			wxLogin_db = wxLoginRepository.saveAndFlush(wxLogin_db);
			return new  MessageBean<WxLogin>("200","更新登录信息成功",wxLogin_db);
		}else {
			return new  MessageBean<WxLogin>("error","未找到需要修改的登录信息",wxLogin_db);
		}
	}
	@Override
	public MessageBean<WxLogin> updateRoleObj(Integer roleId, String openId) {
		// TODO Auto-generated method stub
		Role roleobj = roleRepository.findById(roleId).get();
		WxLogin wxLogin_db = wxLoginRepository.findByOpenId(openId);
		if(Util.isNotEmpty(wxLogin_db)) {
			if(Util.isNotEmpty(roleobj)) {
				wxLogin_db.setRoleobj(roleobj);
			}
			wxLogin_db = wxLoginRepository.saveAndFlush(wxLogin_db);
			return new  MessageBean<WxLogin>("200","更新登录信息成功",wxLogin_db);
		}else {
			return new  MessageBean<WxLogin>("error","未找到需要修改的登录信息",wxLogin_db);
		}
	}
}
