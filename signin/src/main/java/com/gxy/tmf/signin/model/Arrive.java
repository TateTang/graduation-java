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
 * @Desription :签到信息实体类 
 * @Date : 2019年4月28日 上午11:06:20
 */
@Entity
@Table(name = "arrive_info")
public class Arrive extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识 主键 签到id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="arrive_id",nullable=true,length=32)
	private Integer id;
	
	/**
	 * 签到时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="arrive_time",nullable=true)
	private Date arrivetime; 
	
	/**
	 * 签到对应的课程
	 */
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course courseobj;
	
	/**
	 * 签到人
	 */
	@OneToOne
	@JoinColumn(name = "student_openid",referencedColumnName="student_openid")
	private Student studentobj;
	
	/**
	 * 签到状态  0迟到 1签到  2 旷课  
	 */
	@Column(name="arrive_status",columnDefinition = "int default 0")
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(Date arrivetime) {
		this.arrivetime = arrivetime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Arrive [id=" + id + ", arrivetime=" + arrivetime + ", courseobj=" + courseobj + ", studentobj="
				+ studentobj + ", status=" + status + "]";
	}
}
