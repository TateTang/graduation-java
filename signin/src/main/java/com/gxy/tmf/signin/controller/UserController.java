package com.gxy.tmf.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.User;
import com.gxy.tmf.signin.service.UserService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: tangmf
 * @Description: 用户信息 访问类
 * @Data: 2019年3月20日 下午2:57:56
 */
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "不带分页查询全部用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "account", value = "用户名",  dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码",  dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "姓名", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "mobile", value = "手机",  dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "roleId", value = "角色id", dataType = "int", paramType = "query")
			})
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<MessageBean<User>> getAll(
			@RequestParam(defaultValue = "", value = "account", required = false) String account,
			//@RequestParam(defaultValue = "", value = "password", required = false) String password,
			@RequestParam(defaultValue = "", value = "name", required = false) String name,
//			@RequestParam(defaultValue = "", value = "mobile", required = false) String mobile,
//			@RequestParam(defaultValue = "", value = "email", required = false) String email,
			@RequestParam(defaultValue = "", value = "roleId", required = false) Integer roleId,
			@RequestParam(defaultValue = "", value = "email", required = false) String openId) {
		try {
			MessageBean<User> response = userService.findAll(account, name,  roleId,openId);
			return new ResponseEntity<MessageBean<User>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "利用实体添加用户信息")
	@ApiImplicitParam(name = "user", value = "用户实体类", required = true, dataType = "User", paramType = "body")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<User>> create(@RequestBody User user) {
		try {
			if (Util.isEmpty(user)) {
				return new ResponseEntity<MessageBean<User>>(new MessageBean<User>("error", "未获取到用户信息"), HttpStatus.OK);
			}
			MessageBean<User> response = userService.save(user);
			return new ResponseEntity<MessageBean<User>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("根据openId查询user对象")
	@ApiImplicitParam(name = "openId", value = "微信小程序openId", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value="/getUserByOpenId",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<User>> getUserByOpenId(String openId
			) {
		try {
			MessageBean<User> response = userService.findByOpenId(openId);
			return new ResponseEntity<MessageBean<User>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation(value="根据用户openid和用户实体类，更新用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="user",value="用户实体类",required=true,dataType="User",paramType="body"),
			@ApiImplicitParam(name="openId",value="用户openid",required=true,dataType="String",paramType="query")
	})
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<MessageBean<User>> update(@RequestBody User user,@RequestParam("openId") String openId
			) {
		try {
			MessageBean<User> response = userService.update(user, openId);
			return new ResponseEntity<MessageBean<User>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
