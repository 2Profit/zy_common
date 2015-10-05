package com.zy.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 * @author Administrator
 *
 */
public class CommonConstants {

	//证件类型
	public static final ConstantEnity cardTypeSfz = new ConstantEnity(1, "身份证号");
	
	public static List<ConstantEnity> getCardTypes(){
		List<ConstantEnity> list = new ArrayList<ConstantEnity>();
		
		list.add(cardTypeSfz);
		
		return list;
	}
}
