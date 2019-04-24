package com.gxy.tmf.signin.model;

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
 * @Description: 用户信息实体类
 * @Data: 2019年3月20日 下午1:56:32
 */
@Entity
@Table(name="user_info")
public class User extends BaseEntity{
	/**
	 * 唯一标识 主键 用户id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id",nullable=true,length=32)
	private Integer id;  
	
	/**
	 * 用户名 也就是工号，学号
	 */
	@Column(name="user_account",nullable = true, length = 32)
	private String account; 
	
	/**
	 * 用户密码
	 */
//	@Column(name="user_password",nullable = true, length = 64)
//	private String password;
	
	/**
	 * 姓名
	 */
	@Column(name="user_name",nullable = true, length = 64)
	private String name;
	
	/**
	 * 手机号
	 */
//	@Column(name="user_mobile",length = 32)
//	private String mobile;
	
	/**
	 * 邮箱
	 */
//	@Column(name="user_email",length = 32)
//	private String email;
	
	/**
	 * 角色编号
	 * 与role表一一对应
	 */
	@JoinColumn(name = "role_id")
	@OneToOne
	private Role roleobj;
	
	
	@Column(name="user_openid",length = 32)
	private String openid;
	/**
	 * 删除标志
	 */
	@Column(name = "user_deleteflag", length = 4,columnDefinition="bool default false")
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

	public boolean isDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", name=" + name + ", roleobj=" + roleobj + ", openid="
				+ openid + ", deleteflag=" + deleteflag + "]";
	}
	
}
