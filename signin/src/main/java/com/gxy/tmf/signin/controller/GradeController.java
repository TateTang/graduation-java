package com.gxy.tmf.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.Grade;
import com.gxy.tmf.signin.service.GradeService;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("grade")
public class GradeController {
	@Autowired
	private GradeService gradeService;	
	
	@ApiOperation("不带分页查询全部班级信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "班级名称",  dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "counttotal", value = "班级总人数",  dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "countnow", value = "班级当前人数",  dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "teaopenId", value = "创建班级的老师openId",  dataType = "String", paramType = "query"),
	})
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Grade>> getAll(
			@RequestParam(defaultValue = "", value = "name", required = false) String name,
			@RequestParam(defaultValue = "", value = "counttotal", required = false) Integer counttotal,
			@RequestParam(defaultValue = "", value = "countnow", required = false) Integer countnow,
			@RequestParam(defaultValue = "", value = "teaopenId", required = false) String teaopenId
			) {
		try {
			MessageBean<Grade> response = gradeService.findAll( name, counttotal, countnow,  teaopenId);
			return new ResponseEntity<MessageBean<Grade>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Grade>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "利用实体添加班级信息")
	@ApiImplicitParam(name = "grade", value = "班级实体类", required = true, dataType = "Grade", paramType = "body")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<Grade>> create(@RequestBody Grade grade) {
		try {
			if (Util.isEmpty(grade)) {
				return new ResponseEntity<MessageBean<Grade>>(new MessageBean<Grade>("error", "未获取到班级信息"), HttpStatus.OK);
			}
			MessageBean<Grade> response = gradeService.save(grade);
			return new ResponseEntity<MessageBean<Grade>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Grade>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="根据班级id删除班级信息")
	@ApiImplicitParam(name = "gradeId", value = "班级信息id", required = true, dataType = "int", paramType = "path")
	@RequestMapping(value="/delete/{gradeId}",method=RequestMethod.DELETE)
	public ResponseEntity<MessageBean<Void>> delete(@PathVariable("gradeId")Integer gradeId){
		try {
			if (Util.isEmpty(gradeId)) {
				return new ResponseEntity<MessageBean<Void>>(new MessageBean<Void>("error", "未获取到班级信息"), HttpStatus.OK);
			}
			MessageBean<Void> response = gradeService.delete(gradeId);
			return new ResponseEntity<MessageBean<Void>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Void>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("查询单个班级信息")
	@ApiImplicitParam(name = "gradeId", value = "班级Id",  dataType = "int", paramType = "query")
	@RequestMapping(value="/getOne",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Grade>> getOne(
			@RequestParam(defaultValue = "", value = "gradeId", required = false) Integer gradeId
			) {
		try {
			MessageBean<Grade> response = gradeService.findOne(gradeId);
			return new ResponseEntity<MessageBean<Grade>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Grade>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@ApiOperation(value="根据班级id和班级实体类，更新班级信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="grade",value="班级实体类",required=true,dataType="Grade",paramType="body"),
			@ApiImplicitParam(name="gradeId",value="班级id",required=true,dataType="int",paramType="query")
	})
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<MessageBean<Grade>> update(@RequestBody Grade grade,@RequestParam("gradeId") Integer gradeId
			) {
		try {
			MessageBean<Grade> response = gradeService.update(grade, gradeId);
			return new ResponseEntity<MessageBean<Grade>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Grade>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	
	@ApiOperation("查询全部班级名称信息")
	@RequestMapping(value="/getName",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<Grade>> getName(
			) {
		try {
			MessageBean<Grade> response = gradeService.findName();
			return new ResponseEntity<MessageBean<Grade>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<Grade>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
