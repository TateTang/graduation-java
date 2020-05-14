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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @Author : tangmf
 * @Desription : 请假实体类
 * @Date : 2019年4月25日 下午3:41:19
 */
@Entity
@Table(name = "leave_info")
public class Leave extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="leave_id",nullable=true,length=32)
	private Integer id;
	
	/**
	 * 请假时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="leave_time",nullable=true)
	private Date leavetime;
	
	/**
	 * 请假内容
	 */
	@Column(name="leave_content")
	private String leavecontent;
	
	/**
	 * 请假对应的课程
	 */
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course courseobj;
	
	/**
	 * 请假人
	 */
	@OneToOne
	@JoinColumn(name = "student_openid",referencedColumnName="student_openid")
	private Student studentobj;
	
	/**
	 * 请假老师openid
	 */
	@ManyToOne
	@JoinColumn(name = "teacher_openid",referencedColumnName="teacher_openid")
	private Teacher teacherobj;

	/**
	 * 请假状态  0 已提交 1 通过  2不通过
	 */
	@Column(name="leave_status",columnDefinition = "int default 0")
	private Integer status;
	
	/**
	 * 请假单驳回原因
	 */
	@Column(name="leave_rejectreason")
	private String rejectreason;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}

	public String getLeavecontent() {
		return leavecontent;
	}

	public void setLeavecontent(String leavecontent) {
		this.leavecontent = leavecontent;
	}

	public Course getCourseobj() {
		return courseobj;
	}

	public void setCourseobj(Course courseobj) {
		this.courseobj = courseobj;
	}

	public Student getStudentobj() {
		return studentobj;
	}

	public void setStudentobj(Student studentobj) {
		this.studentobj = studentobj;
	}

	public Teacher getTeacherobj() {
		return teacherobj;
	}

	public void setTeacherobj(Teacher teacherobj) {
		this.teacherobj = teacherobj;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getRejectreason() {
		return rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", leavetime=" + leavetime + ", leavecontent=" + leavecontent + ", courseobj="
				+ courseobj + ", studentobj=" + studentobj + ", teacherobj=" + teacherobj + ", status=" + status
				+ ", rejectreason=" + rejectreason + "]";
	}

	
}
