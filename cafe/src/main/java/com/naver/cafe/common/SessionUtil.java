package com.naver.cafe.common;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 세션 아이디가 달라 Null Pointer Exception 발생. 통일시키는 작업 
public class SessionUtil {
	public static <T> void set(HttpServletRequest request, String key, T value) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
		
		String ip = request.getRemoteAddr();
		
		EhCacheHelper.getCache().put(ip + "_" + key, value);
	}
	
	public static Object get(HttpServletRequest request, String key) {
		String ip = request.getRemoteAddr();
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(key);
		
		if ( obj == null ) {
			try {
				session.setAttribute(key, EhCacheHelper.getCache().get(ip + "_" + key));
				return EhCacheHelper.getCache().get(ip + "_" + key);
			}
			catch(NullPointerException e) {
				session.removeAttribute(key);
			}
		}
		
		return obj;
	}
	
	public static void remove(HttpServletRequest request, String key) {
		String ip = request.getRemoteAddr();
		
		HttpSession session = request.getSession();
		session.removeAttribute(key);
		
		try {
			EhCacheHelper.getCache().remove(ip + "_" + key);
		}
		catch(NullPointerException e) {}
	}
	
	public static void invalidate(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		HttpSession session = request.getSession();
		
		EhCacheHelper.getCache().invalidate(ip);
		session.invalidate();
	}
	
}
