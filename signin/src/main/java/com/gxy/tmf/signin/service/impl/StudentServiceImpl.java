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

import com.gxy.tmf.signin.model.Student;
import com.gxy.tmf.signin.repository.StudentRepository;
import com.gxy.tmf.signin.service.StudentService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 学生信息管理实现类
 * @Data: 2019年3月20日 下午5:19:12
 */
@Service
public class StudentServiceImpl implements StudentService {
	// 注册
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public MessageBean<Student> findAll(String account,  String name, Integer roleId, String stuopenId, Integer gradeId) {
		// TODO Auto-generated method stub
		Specification<Student> spec = this.getStudentSpec(account,  name, roleId, stuopenId, gradeId);
		List<Student> studentList = studentRepository.findAll(spec);
		return new MessageBean<Student>("200","查询成功",studentList);
	}
	
	
	@Override
	public MessageBean<Student> save(Student student) {
		// TODO Auto-generated method stub
		if(Util.isNotEmpty(student.getAccount())) {
			student.setCreateDate(new Date());
//			Role roleobj = new Role();
//			roleobj.setId(2);
//			student.setRoleobj(roleobj); //设置角色为学生
			student = studentRepository.saveAndFlush(student);
			return new  MessageBean<Student>("200","插入学生信息成功",student);
		}else {
			return new  MessageBean<Student>("error","插入学生信息失败");
		}
	}

	/**
	 * 动态查询学生信息
	 * @param account 学号
	 * @param name 姓名
	 * @param roleId 角色
	 * @param openId opendid
	 * @param gradeId 班级id
	 * @return
	 */
	public Specification<Student> getStudentSpec(String account, String name,Integer roleId,String openId,Integer gradeId) {
		return (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(root.get("deleteflag").as(Boolean.class), false));
			if (Util.isNotEmpty(account)) {// 学生名
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
			if (Util.isNotEmpty(gradeId)) {// 小程序openId
				predicates.add(cb.equal(root.get("gradeobj").get("id").as(Integer.class),gradeId));
			}
			//添加排序功能 降序 根据id降序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}


	@Override
	public MessageBean<Student> findByOpenId(String openId) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findByOpenId(openId);
		return new  MessageBean<Student>("200","查询单个学生信息成功",student);
	}


	@Override
	public MessageBean<Student> update(Student student, String openId) {
		// TODO Auto-generated method stub
		Student student_db = studentRepository.findByOpenId(openId);
//		Grade grade_db = gradeRepository.findByGradeId(student.getGradeobj().getId());
		if(Util.isNotEmpty(student_db)) {
			if(Util.isNotEmpty(student.getAccount())) {
				student_db.setAccount(student.getAccount());
			}
			if(Util.isNotEmpty(student.getName())) {
				student_db.setName(student.getName());
			}
			if(Util.isNotEmpty(student.getGradeobj())) {
				student_db.setGradeobj(student.getGradeobj());
			}
			student_db = studentRepository.saveAndFlush(student_db);
			return new  MessageBean<Student>("200","更新学生信息成功",student_db);
		}else {
			return new  MessageBean<Student>("error","未找到需要修改的学生信息",student_db);
		}
	}	
}
