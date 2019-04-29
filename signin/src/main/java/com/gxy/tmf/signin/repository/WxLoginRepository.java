package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gxy.tmf.signin.model.WxLogin;


public interface WxLoginRepository extends JpaRepository <WxLogin, Integer>{
	/**
	 * 不带分页全查询 用户信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<WxLogin> findAll(Specification<WxLogin> spec);
	
	/**
	 * 根据openId查询登录信息
	 * @param openId
	 * @return
	 */
	@Query("select w from WxLogin w where w.openid=?1")
	WxLogin findByOpenId(String openId);
	
	/**
	 * 根据用户openId更新roleId
	 * @param roleId
	 * @param openId
	 * @return
	 */
	@Query("update WxLogin w set w.roleobj = ?1 where w.openid=?2")
	@Modifying
	@Transactional
	WxLogin updateRoleObj(Integer roleId, String openId);
}
