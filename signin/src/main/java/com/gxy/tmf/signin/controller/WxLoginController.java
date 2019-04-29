package com.gxy.tmf.signin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxy.tmf.signin.model.WxLogin;
import com.gxy.tmf.signin.service.WxLoginService;
import com.gxy.tmf.signin.util.HttpClientUtil;
import com.gxy.tmf.signin.util.JsonUtils;
import com.gxy.tmf.signin.util.MessageBean;
import com.gxy.tmf.signin.util.Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * @Author : tangmf
 * @Desription : 用户登录访问类
 * @Date : 2019年4月24日 下午5:04:51
 */
@RestController
@RequestMapping("wxLogin")
public class WxLoginController {
	@Autowired
	private WxLoginService wxLoginService;
	
	@ApiOperation(value = "用户登录获取openId和sessionkey")
	@ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
	public ResponseEntity<MessageBean<WxLogin>> wxLogin(String code) {
		try {
			if (Util.isEmpty(code)) {
				return new ResponseEntity<MessageBean<WxLogin>>(new MessageBean<WxLogin>("error", "未获取到登录信息"), HttpStatus.OK);
			}
			String url = "https://api.weixin.qq.com/sns/jscode2session";
			Map<String,String> param = new HashMap<String, String>();
			param.put("appid", "wxc17bd0526af4ec36");
			param.put("secret", "ef8cae843d7f4b2b17b16da7e91eaa14");//秘钥 不要传到前端
			param.put("js_code", code);//临时凭证
			param.put("grant_type", "authorization_code");//授权类型
			String wxResult = HttpClientUtil.doPost(url,param);//不要传递到前端 json字符串对象
			System.out.println(wxResult);
			WxLogin wxLogin = JsonUtils.jsonToPojo(wxResult, WxLogin.class);
			
			MessageBean<WxLogin> response = wxLoginService.findByOpenId(wxLogin.getOpenid());
			if(Util.isEmpty(response.getData())) { //判断有没有 不存在就插入到数据中
				response = wxLoginService.save(wxLogin);
				return new ResponseEntity<MessageBean<WxLogin>>(response, HttpStatus.OK);
			}
			return new ResponseEntity<MessageBean<WxLogin>>(response,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<WxLogin>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation(value="根据登录openid和登录实体类，更新登录信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="wxLogin",value="登录实体类",required=true,dataType="WxLogin",paramType="body"),
			@ApiImplicitParam(name="openId",value="登录openid",required=true,dataType="String",paramType="query")
	})
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<MessageBean<WxLogin>> update(@RequestBody WxLogin wxLogin,@RequestParam("openId") String openId
			) {
		try {
			MessageBean<WxLogin> response = wxLoginService.update(wxLogin, openId);
			return new ResponseEntity<MessageBean<WxLogin>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<WxLogin>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("根据openId查询登录对象")
	@ApiImplicitParam(name = "openId", value = "微信小程序openId", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value="/getWxLoginByOpenId",method=RequestMethod.GET)
	public ResponseEntity<MessageBean<WxLogin>> getWxLoginByOpenId(String openId
			) {
		try {
			MessageBean<WxLogin> response = wxLoginService.findByOpenId(openId);
			return new ResponseEntity<MessageBean<WxLogin>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<WxLogin>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="根据登录openid和roleid，更新登录信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="roleId",value="登录roleid",required=true,dataType="int",paramType="query"),
			@ApiImplicitParam(name="openId",value="登录openid",required=true,dataType="String",paramType="query")
	})
	@RequestMapping(value="/updateRoleObj",method=RequestMethod.PUT)
	public ResponseEntity<MessageBean<WxLogin>> updateRoleObj(@RequestParam("roleId")Integer roleId,@RequestParam("openId")String openId
			) {
		try {
			MessageBean<WxLogin> response = wxLoginService.updateRoleObj(roleId, openId);
			return new ResponseEntity<MessageBean<WxLogin>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<MessageBean<WxLogin>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
