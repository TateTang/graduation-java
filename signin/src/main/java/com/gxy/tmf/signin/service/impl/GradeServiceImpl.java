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

import com.gxy.tmf.signin.model.Grade;
import com.gxy.tmf.signin.repository.GradeRepository;
import com.gxy.tmf.signin.repository.UserRepository;
import com.gxy.tmf.signin.service.GradeService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 班级信息实现类
 * @Data: 2019年3月25日 上午9:44:07
 */
@Service
public class GradeServiceImpl implements GradeService {

	// 注册
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public MessageBean<Grade> findAll(String name, Integer count, String teacherName, Integer countNow) {
		// TODO Auto-generated method stub
		Specification<Grade> spec = this.getGradeSpec(name, count, teacherName, countNow);
		List<Grade> gradeList = gradeRepository.findAll(spec);
		return new MessageBean<Grade>("200","班级信息查询成功",gradeList);
	}

	@Override
	public MessageBean<Grade> save(Grade grade,String openId) {
		// TODO Auto-generated method stub
		if (Util.isNotEmpty(grade.getName())) {
			grade.setCreateDate(new Date());
			grade.setCountNow(0);
			grade.setTeacherName(userRepository.findByOpenId(openId).getName());
			grade = gradeRepository.saveAndFlush(grade);
			return new MessageBean<Grade>("200", "插入班级信息成功", grade);
		} else {
			return new MessageBean<Grade>("error", "插入用户信息失败");
		}
	}
	
	@Override
	public MessageBean<Void> delete(Integer gradeId) {
		// TODO Auto-generated method stub
		Grade grade = gradeRepository.findById(gradeId).get();
		if(Util.isEmpty(grade)) {
			return new MessageBean<Void>("error", "要删除的班级信息不存在");
		}else {
			grade.setDeleteflag(true);
			grade = gradeRepository.saveAndFlush(grade);
			return new MessageBean<Void>("200", "删除班级信息成功");
		}
	}
	/**
	 * 条件查询构造器 动态查询班级信息
	 * @param name  班级名称
	 * @param count 班级编号
	 * @return
	 */
	public Specification<Grade> getGradeSpec(String name, Integer count,String teacherName,Integer countNow) {
		return (Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			// 未被删除的
			predicates.add(cb.equal(root.get("deleteflag").as(Boolean.class), false));
			if (Util.isNotEmpty(name)) {//班级名称
				predicates.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
			}
			if (Util.isNotEmpty(count)) {//班级人数
				predicates.add(cb.equal(root.get("count").as(Integer.class), count));
			}
			if (Util.isNotEmpty(teacherName)) {// 小程序openId
				predicates.add(cb.equal(root.get("teacherName").as(String.class),"%"+teacherName+"%"));
			}
			if (Util.isNotEmpty(countNow)) {//班级人数
				predicates.add(cb.equal(root.get("countNow").as(Integer.class), countNow));
			}
			//添加排序功能，根据id排序 升序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}

	@Override
	public MessageBean<Grade> findOne(Integer gradeId) {
		// TODO Auto-generated method stub
		Grade grade = gradeRepository.findByGradeId(gradeId);
		return new  MessageBean<Grade>("200","查询单个班级信息成功",grade);
	}

	@Override
	public MessageBean<Grade> update(Grade grade, Integer gradeId) {
		// TODO Auto-generated method stub
		Grade grade_db = gradeRepository.findByGradeId(gradeId);
		if(Util.isNotEmpty(grade_db)) {
			if(Util.isNotEmpty(grade.getName())) {
				grade_db.setName(grade.getName());
			}
			if(Util.isNotEmpty(grade.getCount())) {
				grade_db.setCount(grade.getCount());
			}
			grade_db = gradeRepository.saveAndFlush(grade_db);
			return new  MessageBean<Grade>("200","更新班级信息成功",grade_db);
		}else {
			return new  MessageBean<Grade>("error","未找到需要修改的班级信息",grade_db);
		}
	}

	@Override
	public MessageBean<Grade> findName() {
		// TODO Auto-generated method stub
		List<Grade> gradeList = gradeRepository.findName();
		return new MessageBean<Grade>("200","班级名称信息查询成功",gradeList);
	}
}
