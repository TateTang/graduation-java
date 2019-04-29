package com.gxy.tmf.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.Student;
import com.gxy.tmf.signin.service.StudentService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: tangmf
 * @Description: 学生信息 访问类
 * @Data: 2019年3月20日 下午2:57:56
 */
@RestController
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@ApiOperation(value = "不带分页查询全部学生信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "account", value = "学生名",  dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "姓名", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "roleId", value = "角色id", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "stuopenId", value = "学生openid", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "gradeId", value = "班级id", dataType = "int", paramType = "query")
	})
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<MessageBean<Student>> getAll(
			@RequestParam(defaultValue = "", value = "account", required = false) String account,
			@RequestParam(defaultValue = "", value = "name", required = false) String name,
			@RequestParam(defaultValue = "", value = "roleId", required = false) Integer roleId,
			@RequestParam(defaultValue = "", value = "stuopenId", required = false) String stuopenId,
			@RequestParam(defaultValue = "", value = "gradeId", required = false) Integer gradeId
			) {
		try {
			MessageBean<Student> response = studentService.findAll(account,  name, roleId, stuopenId, gradeId);
			return new ResponseEntity<MessageBean<Student>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "利用实体添加学生信息")
	@ApiImplicitParam(name = "student", value = "学生实体类", required = true, dataType = "Student", paramType = "body")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<Student>> create(@RequestBody Student student) {
		try {
			if (Util.isEmpty(student)) {
				return new ResponseEntity<MessageBean<Student>>(new MessageBean<Student>("error", "未获取到学生信息"), HttpStatus.OK);
			}
			MessageBean<Student> response = studentService.save(student);
			return new ResponseEntity<MessageBean<Student>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("根据openId查询学生对象")
	@ApiImplicitParam(name = "openId", value = "微信小程序openId", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value="/getStudentByOpenId",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Student>> getStudentByOpenId(String openId
			) {
		try {
			MessageBean<Student> response = studentService.findByOpenId(openId);
			return new ResponseEntity<MessageBean<Student>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation(value="根据学生openid和学生实体类，更新学生信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="student",value="学生实体类",required=true,dataType="Student",paramType="body"),
			@ApiImplicitParam(name="openId",value="学生openid",required=true,dataType="String",paramType="query")
	})
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<MessageBean<Student>> update(@RequestBody Student student,@RequestParam("openId") String openId
			) {
		try {
			MessageBean<Student> response = studentService.update(student, openId);
			return new ResponseEntity<MessageBean<Student>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
