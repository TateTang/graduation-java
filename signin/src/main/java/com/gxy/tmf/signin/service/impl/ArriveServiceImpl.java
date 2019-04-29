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

import com.gxy.tmf.signin.model.Arrive;
import com.gxy.tmf.signin.model.Student;
import com.gxy.tmf.signin.repository.ArriveRepository;
import com.gxy.tmf.signin.repository.StudentRepository;
import com.gxy.tmf.signin.service.ArriveService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.TimeUtils;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author: tangmf
 * @Description: 角色信息实现类
 * @Data: 2019年3月25日 上午9:43:42
 */
@Service
public class ArriveServiceImpl implements ArriveService {

	@Autowired
	private ArriveRepository arriveRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public MessageBean<Arrive> findAll(String arrvieTime, Integer courseId, String stuopenId, Integer status) {
		// TODO Auto-generated method stub
		Specification<Arrive> spec = this.getArriveSpec(arrvieTime, courseId, stuopenId, status);
		List<Arrive> arriveList = arriveRepository.findAll(spec);
		return new MessageBean<Arrive>("200", "查询成功", arriveList);
	}

	/**
	 * 条件查询构造器
	 * 
	 * @param arrvieTime 签到时间
	 * @param courseId   对应课程id
	 * @param stuopenId  对应签到人id
	 * @param status     签到状态
	 * @return
	 */
	public Specification<Arrive> getArriveSpec(String arrvieTime, Integer courseId, String stuopenId, Integer status) {
		return (Root<Arrive> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (Util.isNotEmpty(arrvieTime)) {// 签到时间
				predicates.add(
						cb.equal(root.get("arrivetime").as(Date.class), TimeUtils.parseDate("yyyy-MM-dd", arrvieTime)));
			}
			if (Util.isNotEmpty(courseId)) {// 对应课程id
				predicates.add(cb.equal(root.get("courseobj").get("id").as(Integer.class), courseId));
			}
			if (Util.isNotEmpty(stuopenId)) {// 对应签到人id
				predicates.add(cb.equal(root.get("studentobj").get("openid").as(String.class), stuopenId));
			}
			if (Util.isNotEmpty(status)) {// 签到状态
				predicates.add(cb.equal(root.get("status").as(Integer.class), status));
			}
			// 添加排序功能 降序 根据id升序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}

	@Override
	public MessageBean<Arrive> save(Arrive arrive) {
		// TODO Auto-generated method stub
		Student student_db = studentRepository.findByOpenId(arrive.getStudentobj().getOpenid());
		if (Util.isNotEmpty(arrive) && Util.isNotEmpty(student_db) ) {
			arrive.setCreateDate(new Date());
			arrive.setStatus(0);// 签到状态迟到
			arrive.setStudentobj(student_db);
			arrive = arriveRepository.saveAndFlush(arrive);
			return new MessageBean<Arrive>("200", "插入签到信息成功", arrive);
		} else {
			return new MessageBean<Arrive>("error", "插入签到信息失败");
		}
	}

	@Override
	public MessageBean<Void> delete(Integer arriveId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageBean<Arrive> findOne(Integer arriveId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageBean<Arrive> update(Arrive arrive, Integer arriveId) {
		// TODO Auto-generated method stub
		return null;
	}
}
