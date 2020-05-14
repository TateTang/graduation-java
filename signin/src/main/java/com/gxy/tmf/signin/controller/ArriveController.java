package com.gxy.tmf.signin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.Arrive;
import com.gxy.tmf.signin.service.ArriveService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @Author : tangmf
 * @Desription : 签到信息
 * @Date : 2019年4月28日 下午2:49:11
 */
@RestController
@RequestMapping("arrive")
public class ArriveController {
	@Autowired
	private ArriveService arriveService;
	
	@ApiOperation(value="不带分页查询全部签到信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "arrvieTime", value = "签到时间",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "courseId", value = "对应课程id",  dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "stuopenId", value = "对应小程序openId",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "签到状态",  dataType = "Integer", paramType = "query")
		})
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Arrive>> getAll(
			@RequestParam(defaultValue = "", value = "arrvieTime", required = false) String arrvieTime,
			@RequestParam(defaultValue = "", value = "courseId", required = false) Integer courseId,
			@RequestParam(defaultValue = "", value = "stuopenId", required = false) String stuopenId,
			@RequestParam(defaultValue = "", value = "status", required = false) Integer status
			){
		try {
			MessageBean<Arrive> response = arriveService.findAll(arrvieTime, courseId, stuopenId, status);
			return new ResponseEntity<MessageBean<Arrive>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Arrive>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "利用实体添加签到信息")
	@ApiImplicitParam(name = "arrive", value = "签到实体类", required = true, dataType = "Arrive", paramType = "body")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<Arrive>> create(@RequestBody Arrive arrive) {
		try {
			if (Util.isEmpty(arrive)) {
				return new ResponseEntity<MessageBean<Arrive>>(new MessageBean<Arrive>("error", "未获取到签到信息"), HttpStatus.OK);
			}
			MessageBean<Arrive> response = arriveService.save(arrive);
			return new ResponseEntity<MessageBean<Arrive>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Arrive>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="查询全部签到信息根据stuopenid和courseIds")
	@RequestMapping(value="/getAllByStuopenId",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Arrive>> getAllByStuopenId(@RequestParam("stuopenId")String stuopenId,@RequestParam("courseIds")List<Integer> courseIds){
		try {
			MessageBean<Arrive> response = arriveService.findByStuopenIdAndCourse(stuopenId, courseIds);
			return new ResponseEntity<MessageBean<Arrive>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Arrive>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
