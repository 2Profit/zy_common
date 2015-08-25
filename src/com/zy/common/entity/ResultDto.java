package com.zy.common.entity;

import java.util.List;

/**
 * 用于web页面返回ajax结果
 * 
 * @author pingan
 * @since  2015-06-03
 * @param <T>
 */

public class ResultDto<T>{
	
	public static final String NO_SESSION_CODE = "100";
	public static final String NO_SESSION_MESSAGE = "session不存在,重新登录";

	private boolean success;
	private String code;
	private String message;
	private List<T> list;//不分页集合
	private PageModel<T> pageModel;//分页集合
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageModel<T> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<T> pageModel) {
		this.pageModel = pageModel;
	}
	
	
}
