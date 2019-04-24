package com.gxy.tmf.signin.config;

import java.util.Objects;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @Author: tangmf
 * @Description: 自定义数据库字段的命名方式，驼峰转为_也就是如 userName --> user_name
 * @Data: 2019年3月20日 下午12:06:24
 */
public class MyPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//重载PhysicalColumnName方法，修改字段得物理名称
	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		String text = warp(name.getText());
		if(Objects.equals(text.charAt(0),'_')) {
			text = text.replaceFirst("_", "");
		}
		return super.toPhysicalColumnName(new Identifier(text,name.isQuoted()), context);
	}
	
	//将驼峰式命名转换成下划线分割的形式
	public static String warp(String text) {
		//$匹配字符串的结束
		//遇到大写字母替换成_大写字母 $获取刚才匹配到的大写字母
		text = text.replaceAll("([A-Z])", "_$1").toLowerCase();
		System.out.println(text);
		if(Objects.equals(text.charAt(0),'_')) {
			text = text.replaceFirst("_", "");
		}
		return text;
	}
}
