package com.naver.cafe.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.naver.cafe.common.Auth;
import com.naver.cafe.menu.biz.MenuBiz;
import com.naver.cafe.menu.vo.MenuSearchVO;
import com.naver.cafe.menu.vo.MenuVO;

// 권한 체크하는 Interceptor
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private MenuBiz menuBiz;

	public void setMenuBiz(MenuBiz menuBiz) {
		this.menuBiz = menuBiz;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Controller 수행 여부 반환

		HandlerMethod controller = null;
		if (handler instanceof HandlerMethod) {

			controller = (HandlerMethod) handler;
			
			Auth auth = controller.getMethodAnnotation(Auth.class);

			if (auth != null) {

				String authValue = auth.value();
				String myAuth = "ADM";

				if (myAuth.equals("ADM")) {
					return true;
				}
				if (myAuth.equals(authValue)) {
					return true;
				}
				else {
					throw new RuntimeException("접근 권한이 없다");
				}
			}
		}

		MenuSearchVO menuSearch = new MenuSearchVO();

		List<MenuVO> menuList = menuBiz.getAllMenu(menuSearch);

		for (MenuVO menuVO : menuList) {
			if (request.getRequestURI().equals("/cafe" + menuVO.getMenuUrl())) {
				if ("ADM".equals(menuVO.getAuth())) {
					return true;
				} else if ("USR".equals(menuVO.getAuth())){
					return true;
				}
				else {
					throw new RuntimeException("페이지에 접근할 권한이 없단다");
				}
			}
		}
		return true;
	}
}
