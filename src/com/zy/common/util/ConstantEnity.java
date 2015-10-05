package com.zy.common.util;

/** 
 * 常量实体
 * @author LL 
 * 
 */
public class ConstantEnity {

	public Object key;
	
	public Object value;
	
	public ConstantEnity(){
	}

	public ConstantEnity(Object key, Object value){
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
