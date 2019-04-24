package com.gxy.tmf.signin.util;

import java.util.List;


/**
 * @Author: tangmf 
 * @Description: 系统反馈工具类
 * @Data: 2019年3月20日 下午2:55:56
 * @param <T>
 */
public class MessageBean<T> { 
	private String code;//状态码
	
	private String msg;//返回的消息
	
	private List<T> dataList; //datalist
	  
	private T data;//data
	
	public MessageBean(){
		
	}
	
	public MessageBean(String code,String msg,List<T> dataList,T data){
		this.code = code;
		this.msg = msg;
		this.dataList = dataList;
		this.data = data;
	}
		
	public MessageBean(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public MessageBean(String code,String msg,T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public MessageBean(String code,String msg,List<T> dataList){
		this.code = code;
		this.msg = msg;
		this.dataList = dataList;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MessageBean [code=" + code + ", msg=" + msg + ", dataList=" + dataList + ", data=" + data + "]";
	}
	
}
