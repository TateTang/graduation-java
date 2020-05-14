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

import com.gxy.tmf.signin.model.Leave;
import com.gxy.tmf.signin.model.Student;
import com.gxy.tmf.signin.model.Teacher;
import com.gxy.tmf.signin.repository.LeaveRepository;
import com.gxy.tmf.signin.repository.StudentRepository;
import com.gxy.tmf.signin.repository.TeacherRepository;
import com.gxy.tmf.signin.service.LeaveService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.TimeUtils;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author : tangmf
 * @Desription : 请假实现类
 * @Date : 2019年4月25日 下午4:10:20
 */
@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public MessageBean<Leave> findAll(String leaveTime,String leaveContent,Integer courseId, 
			Integer status, Integer status2,String stuopneId, String teaopenId) {
		// TODO Auto-generated method stub
		Specification<Leave> spec = this.getLeaveSpec(leaveTime, leaveContent, courseId, status, status2, stuopneId, teaopenId);
		List<Leave> leaveList = leaveRepository.findAll(spec);
		return new MessageBean<Leave>("200", "请假信息查询成功", leaveList);
	}

	@Override
	public MessageBean<Leave> save(Leave leave) {
		// TODO Auto-generated method stub
		Student student_db = studentRepository.findByOpenId(leave.getStudentobj().getOpenid());
		Teacher teacher_db = teacherRepository.findByOpenId(leave.getTeacherobj().getOpenid());
		if (Util.isNotEmpty(leave)&&Util.isNotEmpty(student_db)&&Util.isNotEmpty(teacher_db)) {
			leave.setCreateDate(new Date());
			leave.setStatus(0);// 请假状态已提交
			leave.setStudentobj(student_db);
			leave.setTeacherobj(teacher_db);
			leave = leaveRepository.saveAndFlush(leave);
			return new MessageBean<Leave>("200", "插入请假信息成功", leave);
		} else {
			return new MessageBean<Leave>("error", "插入请假信息失败");
		}
	}

	@Override
	public MessageBean<Void> delete(Integer leaveId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageBean<Leave> findOne(Integer leaveId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageBean<Leave> update(Leave leave, Integer leaveId) {
		// TODO Auto-generated method stub
		Leave leave_db = leaveRepository.findById(leaveId).get();
		if(Util.isNotEmpty(leave_db)) {//修改请假的内容
			if(Util.isNotEmpty(leave.getRejectreason())) {
				leave_db.setRejectreason(leave.getRejectreason());
			}
			if(Util.isNotEmpty(leave.getStatus())) {
				leave_db.setStatus(leave.getStatus());
			}
			leave_db = leaveRepository.saveAndFlush(leave_db);
			return new  MessageBean<Leave>("200","更新请假信息成功",leave_db);
		}else {
			return new  MessageBean<Leave>("error","没有获取到请假信息成功",leave_db);
		}
	}
	
	/**
	 * 条件查询构造器
	 * @param leaveTime 离开时间
	 * @param leaveContent 请假内容
	 * @param courseId 对应课程id
	 * @param status 请假状态
	 * @param stuopneId 对应请假人id
	 * @param teaopenId 对应请假教师openId
	 * @return
	 */
	public Specification<Leave> getLeaveSpec(String leaveTime,String leaveContent,Integer courseId, 
			 Integer status,Integer status2,String stuopenId, String teaopenId) {
		return (Root<Leave> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			if(Util.isNotEmpty(leaveTime)) {//离开时间
				predicates.add(cb.equal(root.get("leavetime").as(Date.class), TimeUtils.parseDate("yyyy-MM-dd", leaveTime)));
			}
			if (Util.isNotEmpty(leaveContent)) {// 请假内容
				predicates.add(cb.equal(root.get("leavecontent").as(String.class), leaveContent));
			}
			if (Util.isNotEmpty(courseId)) {// 课程id
				predicates.add(cb.equal(root.get("courseobj").get("id").as(Integer.class), courseId));
			}
			if (Util.isNotEmpty(status)) {// 请假状态
				predicates.add(cb.or(cb.equal(root.get("status").as(Integer.class), status),
						cb.equal(root.get("status").as(Integer.class), status2)));
			}
			if (Util.isNotEmpty(stuopenId)) {// 请假人openId
				predicates.add(cb.equal(root.get("studentobj").get("openid").as(String.class), stuopenId));
			}
			if (Util.isNotEmpty(teaopenId)) {// 请假老师openId
				predicates.add(cb.equal(root.get("teacherobj").get("openid").as(String.class), teaopenId));
			}
			// 添加排序功能，根据id排序 升序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}

	@Override
	public MessageBean<Leave> updateStatus(Integer leaveId, Integer status) {
		// TODO Auto-generated method stub
		Leave leave_db = leaveRepository.findById(leaveId).get();
		if(Util.isNotEmpty(leave_db)) {
			if(Util.isNotEmpty(status)) {
				leave_db.setStatus(status);
			}
			leave_db = leaveRepository.saveAndFlush(leave_db);
			return new  MessageBean<Leave>("200","更新请假信息成功",leave_db);
		}else {
			return new  MessageBean<Leave>("error","未找到需要修改的请假信息",leave_db);
		}
	}
}
