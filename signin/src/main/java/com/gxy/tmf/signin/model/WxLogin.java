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
 * 
 * @Author : tangmf
 * @Desription : 微信登录信息保存
 * @Date : 2019年4月24日 下午4:52:44
 */
@Entity
@Table(name="wxlogin_info")
public class WxLogin extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识 主键 用户id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="login_id",nullable=true,length=32)
	private Integer id;  
	
	/**
	 * 登录openid
	 */
	@Column(name="login_openid",nullable = true, length = 32)
	private String openid;
	
	/**
	 * 登录会话信息
	 */
	@Column(name="login_session_key",nullable = true, length = 32)
	private String session_key;
	
	/**
	 * 登录角色编号
	 * 与role表一一对应
	 */
	@JoinColumn(name = "role_id")
	@OneToOne
	private Role roleobj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public Role getRoleobj() {
		return roleobj;
	}

	public void setRoleobj(Role roleobj) {
		this.roleobj = roleobj;
	}

	@Override
	public String toString() {
		return "WxLogin [id=" + id + ", openid=" + openid + ", session_key=" + session_key + ", roleobj=" + roleobj
				+ "]";
	}

	
}
