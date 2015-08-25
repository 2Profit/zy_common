package com.zy.common.util;

import javax.servlet.http.HttpSession;

/**
 * UserSession 工具类
 * @author Pingan
 *
 * @since  2015年6月5日
 */

public class UserSessionUtil {

	public final static String COOKIE_LOGIN_USER_DATA = "_loginUser_";
	
	/**
	 * 从session里面取到用户信息
	 * 
	 * @param session
	 * @return
	 */
	public static UserDto getSessionUser(HttpSession session) {
		if (session == null) {
			throw new IllegalArgumentException("UserSessionUtil Exception : cann't get user without session");
		}
		UserDto user = (UserDto) session.getAttribute(COOKIE_LOGIN_USER_DATA);
		
		return user;
	}
	
	public static String getSessionUserId(HttpSession session){
		if (session == null) {
			throw new IllegalArgumentException("UserSessionUtil Exception : cann't get user without session");
		}
		UserDto user = (UserDto) session.getAttribute(COOKIE_LOGIN_USER_DATA);
		try {
			return user.getId();
		} catch (Exception e) {
			throw new NoSessionException("UserSessionUtil No Session Exception", e);
		}
	}
	
	/**
	 * 设置用户信息到session
	 * 
	 * @param session
	 * @param user
	 */
	public static void setUserDto(HttpSession session, UserDto user) {
		if (session == null) {
			throw new IllegalArgumentException("UserSessionUtil Exception : cann't set user without session");
		}
		session.setAttribute(COOKIE_LOGIN_USER_DATA, user);
	}

	/**
	 * 删除session里面的用户信息
	 * 
	 * @param session
	 */
	public static void removeUserDto(HttpSession session) {
		if (session == null) {
			throw new IllegalArgumentException("UserSessionUtil Exception : cann't remove user without session");
		}
		session.removeAttribute(COOKIE_LOGIN_USER_DATA);
		session.invalidate();
	}
}
