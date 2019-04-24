package com.gxy.tmf.signin.service;

import com.gxy.tmf.signin.model.Role;
import com.gxy.tmf.signin.util.MessageBean;

public interface RoleService {
	/**
	 * 查询角色信息 全部
	 * @param name
	 * @param description
	 * @return
	 */
	MessageBean<Role> findAll(String name, String description);
}
