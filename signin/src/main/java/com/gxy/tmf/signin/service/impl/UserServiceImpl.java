package com.gxy.tmf.signin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gxy.tmf.signin.model.User;
import com.gxy.tmf.signin.repository.UserRepository;
import com.gxy.tmf.signin.service.UserService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 用户信息管理实现类
 * @Data: 2019年3月20日 下午5:19:12
 */
@Service
public class UserServiceImpl implements UserService {
	// 注册
	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageBean<User> findAll(String account,  String name, Integer roleId, String openId) {
		// TODO Auto-generated method stub
		Specification<User> spec = this.getUserSpec(account,  name, roleId, openId);
		List<User> userList = userRepository.findAll(spec);
		return new MessageBean<User>("200","查询成功",userList);
	}
	
	
	@Override
	public MessageBean<User> save(User user) {
		// TODO Auto-generated method stub
		if(Util.isNotEmpty(user.getAccount())) {
			user.setCreateDate(new Date());
			user = userRepository.saveAndFlush(user);
			return new  MessageBean<User>("200","插入用户信息成功",user);
		}else {
			return new  MessageBean<User>("error","插入用户信息失败");
		}
	}

	/**
	 * 条件构造器 动态查询用户信息
	 * 
	 * @param account  用户名
	 * @param password 密码
	 * @param name     姓名
	 * @param mobile   手机
	 * @param email    邮箱
	 * @param roleId   角色id
	 * @return
	 */
	public Specification<User> getUserSpec(String account, String name,Integer roleId,String openId) {
		return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(root.get("deleteflag").as(Boolean.class), false));
			if (Util.isNotEmpty(account)) {// 用户名
				predicates.add(cb.equal(root.get("account").as(String.class),account));
			}
			if (Util.isNotEmpty(name)) {// 姓名
				predicates.add(cb.equal(root.get("name").as(String.class),name));
			}
			if (Util.isNotEmpty(roleId)) {// 角色名
				predicates.add(cb.equal(root.get("roleobj").get("id").as(Integer.class),roleId));
			}
			if (Util.isNotEmpty(openId)) {// 小程序openId
				predicates.add(cb.equal(root.get("openid").as(String.class),openId));
			}
			//添加排序功能 降序 根据id降序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}


	@Override
	public MessageBean<User> findByOpenId(String openId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByOpenId(openId);
		return new  MessageBean<User>("200","查询单个用户信息成功",user);
	}


	@Override
	public MessageBean<User> update(User user, String openId) {
		// TODO Auto-generated method stub
		User user_db = userRepository.findByOpenId(openId);
		if(Util.isNotEmpty(user_db)) {
			if(Util.isNotEmpty(user.getAccount())) {
				user_db.setAccount(user.getAccount());
			}
			if(Util.isNotEmpty(user.getName())) {
				user_db.setName(user.getName());
			}
			user_db = userRepository.saveAndFlush(user_db);
			return new  MessageBean<User>("200","更新用户信息成功",user_db);
		}else {
			return new  MessageBean<User>("error","未找到需要修改的用户信息",user_db);
		}
	}	
}
