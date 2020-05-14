package com.gxy.tmf.signin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @Author: tangmf
 * @Description: 课程信息实体类
 * @Data: 2019年3月20日 下午6:04:23
 */
@Entity
@Table(name = "course_info")
public class Course extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id",nullable=true,length=32)
	private Integer id;
	
	/**
	 * 课程名称
	 */
	@Column(name="course_name",nullable=true,length=64)
	private String name;
	
	/**
	 * 签到码
	 */
	@Column(name="course_yzm",nullable=true,length=32)
	private String yzm;
	
	/**
	 * 开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="course_starttime",nullable=true)
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="course_endtime",nullable=true)
	private Date endTime;
	
	/**
	 * 班级编号 与班级一一对应
	 */
	@JoinColumn(name = "grade_id")
	@ManyToOne
	private Grade gradeobj;
	
	/**
	 * 添加课程的老师 一个老师可以添加多个课程
	 */
	@ManyToOne
	@JoinColumn(name = "teacher_openid",referencedColumnName="teacher_openid")
	private Teacher teacherobj;
	
	/**
	 * 删除标志
	 */
	@Column(name = "course_deleteflag", length = 4,columnDefinition="bool default false")
	private boolean deleteflag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYzm() {
		return yzm;
	}

	public void setWeek(String yzm) {
		this.yzm = yzm;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Grade getGradeobj() {
		return gradeobj;
	}

	public void setGradeobj(Grade gradeobj) {
		this.gradeobj = gradeobj;
	}

	public Teacher getTeacherobj() {
		return teacherobj;
	}

	public void setTeacherobj(Teacher teacherobj) {
		this.teacherobj = teacherobj;
	}

	public boolean isDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", yzm=" + yzm + ", startTime=" + startTime + ", endTime="
				+ endTime + ", gradeobj=" + gradeobj + ", teacherobj=" + teacherobj + ", deleteflag=" + deleteflag
				+ "]";
	}
}
