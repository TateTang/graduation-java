package com.gxy.tmf.signin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @Author: tangmf
 * @Description: 班级信息实体类
 * @Data: 2019年3月20日 下午6:03:49
 */
@Entity
@Table(name = "grade_info")
public class Grade extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grade_id",nullable=true,length=32)
	private Integer id;
	
	/**
	 * 班级名称
	 */
	@Column(name="grade_name",nullable=true,length=64)
	private String name;
	
	/**
	 * 班级人数
	 */
	@Column(name="grade_counttotal",nullable=true,length=32)
	private Integer counttotal;
	
	/**
	 * 班级当前人数
	 */
	@Column(name="grade_countnow",length = 32)
	private Integer countnow;
	
	/**
	 * 教师openId 
	 * 属性referencedColumnName标注的是所关联表中的字段名，若不指定则使用的所关联表的主键字段名作为外键。
	 * 一个老师可以创建多个班级
	 */
	@ManyToOne
	@JoinColumn(name = "teacher_openid",referencedColumnName="teacher_openid")
	private Teacher teacherobj; 
	
	/**
	 * 删除标志
	 */
	@Column(name = "grade_deleteflag", length = 4,columnDefinition="bool default false")
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

	public Integer getCounttotal() {
		return counttotal;
	}

	public void setCounttotal(Integer counttotal) {
		this.counttotal = counttotal;
	}

	public Integer getCountnow() {
		return countnow;
	}

	public void setCountnow(Integer countnow) {
		this.countnow = countnow;
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
		return "Grade [id=" + id + ", name=" + name + ", counttotal=" + counttotal + ", countnow=" + countnow
				+ ", teacherobj=" + teacherobj + ", deleteflag=" + deleteflag + "]";
	}
}
