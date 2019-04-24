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

import com.gxy.tmf.signin.model.Course;
import com.gxy.tmf.signin.repository.CourseRepository;
import com.gxy.tmf.signin.service.CourseService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.TimeUtils;
import com.gxy.tmf.signin.util.Util;

/**
 * @Author : tangmf
 * @Desription : 课程信息实现类
 * @Data : 2019年3月31日 下午3:42:28
 */
@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;
	@Override
	public MessageBean<Course> findAll(String name, String week, String startTime, String endTime, Integer gradeId) {
		// TODO Auto-generated method stub
		Specification<Course> spec = this.getCourseSpec(name, week,startTime,endTime,gradeId);
		List<Course> courseList = courseRepository.findAll(spec);
		return new MessageBean<Course>("200","课程信息查询成功",courseList);
	}

	@Override
	public MessageBean<Course> save(Course course) {
		// TODO Auto-generated method stub
		if (Util.isNotEmpty(course.getName())) {
			course.setCreateDate(new Date());
			course = courseRepository.saveAndFlush(course);
			return new MessageBean<Course>("200", "插入课程信息成功", course);
		} else {
			return new MessageBean<Course>("error", "插入课程信息失败");
		}
	}

	@Override
	public MessageBean<Void> delete(Integer courseId) {
		// TODO Auto-generated method stub
		Course grade = courseRepository.findById(courseId).get();
		if(Util.isEmpty(grade)) {
			return new MessageBean<Void>("error", "要删除的课程信息不存在");
		}else {
			grade.setDeleteflag(true);
			grade = courseRepository.saveAndFlush(grade);
			return new MessageBean<Void>("200", "删除课程信息成功");
		}
	}

	@Override
	public MessageBean<Course> findOne(Integer courseId) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findByCourseId(courseId);
		return new  MessageBean<Course>("200","查询单个课程信息成功",course);
	}

	@Override
	public MessageBean<Course> update(Course course, Integer courseId) {
		// TODO Auto-generated method stub
		Course course_db = courseRepository.findByCourseId(courseId);
		if(Util.isNotEmpty(course_db)) {
			if(Util.isNotEmpty(course.getName())) {
				course_db.setName(course.getName());
			}
			if(Util.isNotEmpty(course.getWeek())) {
				course_db.setWeek(course.getWeek());
			}
			if(Util.isNotEmpty(course.getStartTime())) {
				course_db.setStartTime(course.getStartTime());
			}
			if(Util.isNotEmpty(course.getEndTime())) {
				course_db.setEndTime(course.getEndTime());
			}
			if(Util.isNotEmpty(course.getGradeobj())) {
				course_db.setGradeobj(course.getGradeobj());
			}
			course_db = courseRepository.saveAndFlush(course_db);
			return new  MessageBean<Course>("200","更新课程信息成功",course_db);
		}else {
			return new  MessageBean<Course>("error","未找到需要修改的班级信息",course_db);
		}
	}
	
	/**
	 * 条件查询构造器
	 * @param name 课程名称
	 * @param week 星期
	 * @param startTime 开始时间
	 * @param endTime	结束时间	
	 * @param gradeId	班级id
	 * @return
	 */
	public Specification<Course> getCourseSpec(String name, String week, String startTime, String endTime, Integer gradeId) {
		return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			// 未被删除的
			predicates.add(cb.equal(root.get("deleteflag").as(Boolean.class), false));
			if (Util.isNotEmpty(name)) {// 课程名称
				predicates.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
			}
			if (Util.isNotEmpty(week)) {// 星期
				predicates.add(cb.equal(root.get("week").as(Integer.class), "%"+week+"%"));
			}
			if (Util.isNotEmpty(startTime)) { // 开始时间
				predicates.add(cb.greaterThanOrEqualTo(root.get("startTime").as(Date.class),  TimeUtils.parseDate("yyyy-MM-dd", startTime)));
			}
			if (Util.isNotEmpty(endTime)) {// 结束时间
				predicates.add(cb.lessThanOrEqualTo(root.get("endTime").as(Date.class), TimeUtils.parseDate("yyyy-MM-dd", endTime)) );
			}
			if (Util.isNotEmpty(gradeId)) {//班级id
				predicates.add(cb.equal(root.get("gradeobj").get("id").as(Integer.class), gradeId));
			}
			// 添加排序功能 降序 根据id降序
			query.orderBy(cb.asc(root.get("id").as(Integer.class)));
			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();
		};
	}

}
