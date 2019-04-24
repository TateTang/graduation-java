package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxy.tmf.signin.model.User;


public interface UserRepository extends JpaRepository <User, Integer>{
	/**
	 * 不带分页全查询 用户信息
	 * @param spec 条件构造器
	 * @return
	 */
	List<User> findAll(Specification<User> spec);
	
	/**
	 * 根据openId查询用户信息
	 * @param openId
	 * @return
	 */
	@Query("select u from User u where u.openid=?1")
	User findByOpenId(String openId);
}
