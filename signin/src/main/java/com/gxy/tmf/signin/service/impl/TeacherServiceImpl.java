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

import com.gxy.tmf.signin.model.Teacher;
import com.gxy.tmf.signin.repository.TeacherRepository;
import com.gxy.tmf.signin.service.TeacherService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 教师信息管理实现类
 * @Data: 2019年3月20日 下午5:19:12
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	// 注册
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public MessageBean<Teacher> findAll(String account,  String name, Integer roleId, String teaopenId) {
		// TODO Auto-generated method stub
		Specification<Teacher> spec = this.getTeacherSpec(account,  name, roleId, teaopenId);
		List<Teacher> teacherList = teacherRepository.findAll(spec);
		return new MessageBean<Teacher>("200","查询成功",teacherList);
	}
	
	
	@Override
	public MessageBean<Teacher> save(Teacher teacher) {
		// TODO Auto-generated method stub
		if(Util.isNotEmpty(teacher.getAccount())) {
			teacher.setCreateDate(new Date());
//			Role roleobj = new Role();
//			roleobj.setId(1);
//			teacher.setRoleobj(roleobj);
			teacher = teacherRepository.saveAndFlush(teacher);
			return new  MessageBean<Teacher>("200","插入教师信息成功",teacher);
		}else {
			return new  MessageBean<Teacher>("error","插入教师信息失败");
		}
	}

	/**
	 * 动态查询教师信息
	 * @param account 工号
	 * @param name 姓名
	 * @param roleId 角色
	 * @param teaopenId 教师openId
	 * @return
	 */
	public Specification<Teacher> getTeacherSpec(String account,  String name, Integer roleId, String teaopenId) {
		return (Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(root.get("deleteflag").as(Boolean.class), false));
			if (Util.isNotEmpty(account)) {// 工号
				predicates.add(cb.equal(root.get("account").as(String.class),account));
			}
			if (Util.isNotEmpty(name)) {// 姓名
				predicates.add(cb.equal(root.get("name").as(String.class),name));
			}
			if (Util.isNotEmpty(roleId)) {// 角色名
				predicates.add(cb.equal(root.get("roleobj").get("id").as(Integer.class),roleId));
			}
			if (Util.isNotEmpty(teaopenId)) {// 小程序openId
				predicates.add(cb.equal(root.get("openid").as(String.class),teaopenId));
			}
			//添加排序功能 降序 根据id降序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}


	@Override
	public MessageBean<Teacher> findByOpenId(String openId) {
		// TODO Auto-generated method stub
		Teacher teacher = teacherRepository.findByOpenId(openId);
		return new  MessageBean<Teacher>("200","查询单个教师信息成功",teacher);
	}


	@Override
	public MessageBean<Teacher> update(Teacher teacher, String openId) {
		// TODO Auto-generated method stub
		Teacher teacher_db = teacherRepository.findByOpenId(openId);
		if(Util.isNotEmpty(teacher_db)) {
			if(Util.isNotEmpty(teacher.getAccount())) {
				teacher_db.setAccount(teacher.getAccount());
			}
			if(Util.isNotEmpty(teacher.getName())) {
				teacher_db.setName(teacher.getName());
			}
			teacher_db = teacherRepository.saveAndFlush(teacher_db);
			return new  MessageBean<Teacher>("200","更新教师信息成功",teacher_db);
		}else {
			return new  MessageBean<Teacher>("error","未找到需要修改的教师信息",teacher_db);
		}
	}	
}
