package com.gxy.tmf.signin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @Author: tangmf
 * @Description: 抽象实体类，这个类将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中
 * @Data: 2019年3月20日 下午12:00:38
 */
@MappedSuperclass
public abstract class BaseEntity {
	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
}
