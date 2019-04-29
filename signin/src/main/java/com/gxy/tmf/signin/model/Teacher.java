package com.gxy.tmf.signin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Author: tangmf
 * @Description: 教师信息实体类
 * @Data: 2019年3月20日 下午1:56:32
 */
@Entity
@Table(name="teacher_info")
public class Teacher extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识 主键 教师id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="teacher_id",nullable=true,length=32)
	private Integer id;  
	
	/**
	 * 教师 工号
	 */
	@Column(name="teacher_account",nullable = true, length = 32)
	private String account; 
	
	/**
	 * 姓名
	 */
	@Column(name="teacher_name",nullable = true, length = 64)
	private String name;

	/**
	 * 角色编号
	 * 与role表一一对应
	 */
	@JoinColumn(name = "role_id")
	@OneToOne
	private Role roleobj;
	
	/**
	 * 教师openId
	 */
	@Column(name="teacher_openid",length = 32)
	private String openid;
	
	/**
	 * 删除标志
	 */
	@Column(name = "teacher_deleteflag", length = 4,columnDefinition="bool default false")
	private boolean deleteflag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRoleobj() {
		return roleobj;
	}

	public void setRoleobj(Role roleobj) {
		this.roleobj = roleobj;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public boolean isDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", account=" + account + ", name=" + name + ", roleobj=" + roleobj + ", openid="
				+ openid + ", deleteflag=" + deleteflag + "]";
	}
}
