package com.gxy.tmf.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.Role;
import com.gxy.tmf.signin.service.RoleService;
import com.gxy.tmf.signin.util.MessageBean;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: tangmf
 * @Description: 角色信息 访问类
 * @Data: 2019年3月20日 下午5:42:16
 */
@RestController
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value="不带分页查询全部角色信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "角色名",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "description", value = "角色描述",  dataType = "String", paramType = "query")
		})
	@RequestMapping(value="/getRoleAll",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Role>> getAll(
			@RequestParam(defaultValue = "", value = "name", required = false) String name,
			@RequestParam(defaultValue = "", value = "description", required = false) String description
			){
		try {
			MessageBean<Role> response = roleService.findAll(name, description);
			return new ResponseEntity<MessageBean<Role>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Role>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
