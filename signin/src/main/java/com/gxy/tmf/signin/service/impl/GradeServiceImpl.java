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
import com.gxy.tmf.signin.model.Teacher;
import com.gxy.tmf.signin.repository.GradeRepository;
import com.gxy.tmf.signin.repository.TeacherRepository;
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
	private TeacherRepository teacherRepository;
	
	@Override
	public MessageBean<Grade> findAll(String name,Integer counttotal,Integer countnow, String teaopenId) {
		// TODO Auto-generated method stub
		Specification<Grade> spec = this.getGradeSpec(name, counttotal, countnow, teaopenId);
		List<Grade> gradeList = gradeRepository.findAll(spec);
		return new MessageBean<Grade>("200","班级信息查询成功",gradeList);
	}

	@Override
	public MessageBean<Grade> save(Grade grade) {
		// TODO Auto-generated method stub
		Teacher teacher_db = teacherRepository.findByOpenId(grade.getTeacherobj().getOpenid());
		if (Util.isNotEmpty(grade) && Util.isNotEmpty(teacher_db)) {//这里主要需要进行转换
			grade.setCreateDate(new Date());
			grade.setCountnow(0);
			grade.setTeacherobj(teacher_db);
			grade = gradeRepository.saveAndFlush(grade);
			return new MessageBean<Grade>("200", "插入班级信息成功", grade);
		} else {
			return new MessageBean<Grade>("error", "插入班级信息失败");
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
	 * @param name 班级名称
	 * @param counttotal 班级总人数
	 * @param countnow 班级当前人数
	 * @param teaopenId 对应教师openId
	 * @return
	 */
	public Specification<Grade> getGradeSpec(String name,Integer counttotal,Integer countnow, String teaopenId) {
		return (Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			// 未被删除的
			predicates.add(cb.equal(root.get("deleteflag").as(Boolean.class), false));
			if (Util.isNotEmpty(name)) {//班级名称
				predicates.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
			}
			if (Util.isNotEmpty(counttotal)) {//总人数
				predicates.add(cb.equal(root.get("counttotal").as(Integer.class), counttotal));
			}
			if (Util.isNotEmpty(countnow)) {//班级当前人数
				predicates.add(cb.equal(root.get("countnow").as(Integer.class), countnow));
			}
			if (Util.isNotEmpty(teaopenId)) {// 小程序openId
				predicates.add(cb.equal(root.get("teacherobj").get("openid").as(String.class),teaopenId));
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
			if(Util.isNotEmpty(grade.getCounttotal())) {
				grade_db.setCounttotal(grade.getCounttotal());
			}
			if(Util.isNotEmpty(grade.getCountnow())) {
				grade_db.setCountnow(grade.getCountnow());
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
