package com.gxy.tmf.signin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: tangmf
 * @Description: 角色信息实体类
 * @Data: 2019年3月20日 下午1:56:12
 */
@Entity
@Table(name="role_info")
public class Role extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识 主键 角色id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id",nullable=true,length=32)
	private Integer id;
	
	/**
	 * 角色名
	 */
	@Column(name="role_name",nullable=true,length=32)
	private String name;
	
	/**
	 * 角色描述
	 */
	@Column(name="role_description",columnDefinition="Text")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
