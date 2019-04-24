package com.gxy.tmf.signin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gxy.tmf.signin.model.Role;
import com.gxy.tmf.signin.repository.RoleRepository;
import com.gxy.tmf.signin.service.RoleService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 角色信息实现类
 * @Data: 2019年3月25日 上午9:43:42
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public MessageBean<Role> findAll(String name, String description) {
		// TODO Auto-generated method stub
		Specification<Role> spec = this.getRoleSpec(name, description);
		List<Role> roleList = roleRepository.findAll(spec);
		return new MessageBean<Role>("200","查询成功",roleList);
	}
	
	/**
	 * 条件构造器
	 * @param name 角色名
	 * @param description 角色描述
	 * @return
	 */
	public Specification<Role> getRoleSpec(String name,String description){
		return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			if(Util.isNotEmpty(name)) {//角色名
				predicates.add(cb.equal(root.get("name").as(String.class), name));
			}
			if(Util.isNotEmpty(description)) {//描述
				predicates.add(cb.like(root.get("description").as(String.class),"%"+description+"%"));
			}
			//添加排序功能 降序 根据id升序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}
}
