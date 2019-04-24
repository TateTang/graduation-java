package com.gxy.tmf.signin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @Author: tangmf
 * @Description: 班级信息实体类
 * @Data: 2019年3月20日 下午6:03:49
 */
@Entity
@Table(name = "grade_info")
public class Grade extends BaseEntity{
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
	@Column(name="grade_count",nullable=true,length=32)
	private Integer count;
	
	/**
	 * 班级的老师姓名
	 */
	@Column(name="grade_teachername",length = 32)
	private String teacherName;
	
	/**
	 * 班级当前人数
	 */
	@Column(name="grade_countNow",length = 32)
	private Integer countNow;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getCountNow() {
		return countNow;
	}
	public void setCountNow(Integer countNow) {
		this.countNow = countNow;
	}
	public boolean isDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}
	
}
