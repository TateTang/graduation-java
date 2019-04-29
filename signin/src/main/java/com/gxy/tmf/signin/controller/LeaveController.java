package com.gxy.tmf.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.Leave;
import com.gxy.tmf.signin.service.LeaveService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @Author : tangmf
 * @Desription : 请假访问类
 * @Date : 2019年4月25日 下午4:20:00
 */
@RestController
@RequestMapping("leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	@ApiOperation("不带分页查询全部班级信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "leaveTime", value = "请假时间",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "leaveContent", value = "请假内容",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "courseId", value = "课程id",  dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "请假状态",  dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "stuopenId", value = "请假人openId",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "teaopenId", value = "请假老师对应id",  dataType = "String", paramType = "query"),
	})
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Leave>> getAll(
			@RequestParam(defaultValue = "", value = "leaveTime", required = false) String leaveTime,
			@RequestParam(defaultValue = "", value = "leaveContent", required = false) String leaveContent,
			@RequestParam(defaultValue = "", value = "courseId", required = false) Integer courseId,
			@RequestParam(defaultValue = "", value = "status", required = false) Integer status,
			@RequestParam(defaultValue = "", value = "stuopenId", required = false) String stuopenId,
			@RequestParam(defaultValue = "", value = "teaopenId", required = false) String teaopenId
			) {
		try {
			MessageBean<Leave> response = leaveService.findAll(leaveTime, leaveContent, courseId, status,stuopenId,  teaopenId);
			return new ResponseEntity<MessageBean<Leave>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Leave>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "利用实体添加请假信息")
	@ApiImplicitParam(name = "leave", value = "请假实体类", required = true, dataType = "Leave", paramType = "body")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<Leave>> create(@RequestBody Leave leave) {
		try {
			if (Util.isEmpty(leave)) {
				return new ResponseEntity<MessageBean<Leave>>(new MessageBean<Leave>("error", "未获取到请假信息"), HttpStatus.OK);
			}
			MessageBean<Leave> response = leaveService.save(leave);
			return new ResponseEntity<MessageBean<Leave>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Leave>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
