package com.gxy.tmf.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.Teacher;
import com.gxy.tmf.signin.service.TeacherService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: tangmf
 * @Description: 教师信息 访问类
 * @Data: 2019年3月20日 下午2:57:56
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@ApiOperation(value = "不带分页查询全部教师信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "account", value = "教师名",  dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "姓名", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "roleId", value = "角色id", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "teaopenId", value = "教师openid", dataType = "String", paramType = "query"),
	})
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<MessageBean<Teacher>> getAll(
			@RequestParam(defaultValue = "", value = "account", required = false) String account,
			@RequestParam(defaultValue = "", value = "name", required = false) String name,
			@RequestParam(defaultValue = "", value = "roleId", required = false) Integer roleId,
			@RequestParam(defaultValue = "", value = "teaopenId", required = false) String teaopenId
			) {
		try {
			MessageBean<Teacher> response = teacherService.findAll(account,  name, roleId, teaopenId);
			return new ResponseEntity<MessageBean<Teacher>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Teacher>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "利用实体添加教师信息")
	@ApiImplicitParam(name = "teacher", value = "教师实体类", required = true, dataType = "Teacher", paramType = "body")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<Teacher>> create(@RequestBody Teacher teacher) {
		try {
			if (Util.isEmpty(teacher)) {
				return new ResponseEntity<MessageBean<Teacher>>(new MessageBean<Teacher>("error", "未获取到教师信息"), HttpStatus.OK);
			}
			MessageBean<Teacher> response = teacherService.save(teacher);
			return new ResponseEntity<MessageBean<Teacher>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Teacher>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("根据openId查询teacher对象")
	@ApiImplicitParam(name = "openId", value = "微信小程序openId", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value="/getTeacherByOpenId",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Teacher>> getTeacherByOpenId(String openId
			) {
		try {
			MessageBean<Teacher> response = teacherService.findByOpenId(openId);
			return new ResponseEntity<MessageBean<Teacher>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Teacher>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation(value="根据教师openid和教师实体类，更新教师信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="teacher",value="教师实体类",required=true,dataType="Teacher",paramType="body"),
			@ApiImplicitParam(name="openId",value="教师openid",required=true,dataType="String",paramType="query")
	})
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<MessageBean<Teacher>> update(@RequestBody Teacher teacher,@RequestParam("openId") String openId
			) {
		try {
			MessageBean<Teacher> response = teacherService.update(teacher, openId);
			return new ResponseEntity<MessageBean<Teacher>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Teacher>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
