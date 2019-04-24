package com.gxy.tmf.signin.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: tangmf
 * @Description: 检查一个对象是否为空的 判断工具类
 * @Data: 2019年3月20日 下午2:56:57
 */
public class Util {

	/**
	 * 检查一个对象是否为空串。<br><br>
	 * 1.在进行空检查中，大多数为串，先检查是否为串，可以检查字符串类的对象
	 * <li> String
	 * <li> StringBuffer
	 * <li> StringBuilder
	 * 2.检查集合对象Collection子类，如Set，List等类<br>
	 * 3.检查Map类如HashMap，Hashtable
	 * 4.检查数组<br>
	 * 数组检查规则是，检查长度为，返回空，如果数组中所有对象为空，返回为空。
	 * 
	 * @param object 输入检查的对象
	 * @return 如果为空是真，非常空为假
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof CharSequence) {
			return ((CharSequence)o).length() == 0;
		}
		
		if (o instanceof Collection) {
			return ((Collection<?>)o).size() == 0;
		}
		
		if (o instanceof Map) {
			return ((Map<?,?>)o).size() == 0;
		}
		
		if (o.getClass().isArray()) {
			int len = Array.getLength(o);
			for (int i=0; i<len; i++) {
				if (Array.get(o, i) != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof CharSequence) {
			return ((CharSequence)o).length() > 0;
		}
		
		if (o instanceof Collection) {
			return ((Collection<?>)o).size() > 0;
		}
		
		if (o instanceof Map) {
			return ((Map<?,?>)o).size() > 0;
		}
		
		if (o.getClass().isArray()) {
			return Array.getLength(o)>0;
		}
		return true;
	}
}
