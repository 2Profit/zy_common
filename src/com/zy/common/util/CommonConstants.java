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
	public static final ConstantEnity cardTypeSfz = new ConstantEnity(0, "中国居民身份证");
	public static final ConstantEnity cardTypeHz = new ConstantEnity(1, "护照");
	
	public static List<ConstantEnity> getCardTypes(){
		List<ConstantEnity> list = new ArrayList<ConstantEnity>();
		
		list.add(cardTypeSfz);
		list.add(cardTypeHz);
		
		return list;
	}
	
	
	//支付类型
	public static final ConstantEnity payTypeAlipay = new ConstantEnity(0, "支付宝");
	public static final ConstantEnity payTypeWeChat = new ConstantEnity(1, "微信");
	public static final ConstantEnity payTypeBank = new ConstantEnity(2, "银行");
	public static final ConstantEnity payTypePerson = new ConstantEnity(3, "人工添加");
	
	
	//币种
	public static final ConstantEnity curTypeVirtual = new ConstantEnity(0, "虚拟币");
	public static final ConstantEnity curTypeTrue = new ConstantEnity(1, "真实币");
	
	public static List<ConstantEnity> getCurTypes(){
		List<ConstantEnity> list = new ArrayList<ConstantEnity>();
		
		list.add(curTypeVirtual);
		list.add(curTypeTrue);
		
		return list;
	}
	
	//提案状态
	public static final ConstantEnity proposalStatusDefault = new ConstantEnity(0, "待审批");
	public static final ConstantEnity proposalStatusPass = new ConstantEnity(1, "通过");
	public static final ConstantEnity proposalStausCancel = new ConstantEnity(2, "拒绝");
	
	public static final List<ConstantEnity> getProposalStatus(){
		List<ConstantEnity> list = new ArrayList<ConstantEnity>();
		
		list.add(proposalStatusDefault);
		list.add(proposalStatusPass);
		list.add(proposalStausCancel);
		
		return list;
	}
	
}
