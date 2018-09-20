package cn.itcast.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.common.utils.RequestUtils;
import cn.itcast.core.service.user.SessionProvider;

/**
 * 自定义拦截器      前端控制器  处理器映射器  处理器适配器   处理器 Controller（handler)
 * @author lx
 *
 */
public class SpringmvcInterceptor implements  HandlerInterceptor{

	@Autowired
	private SessionProvider sessionProvider;
	//Handler之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String username = sessionProvider.getAttibuteForUsername(RequestUtils.getCSESSIONID(response, request));
		if(null != username){
			//已经登陆
			//isLogin true false
			request.setAttribute("isLogin", true);
		}else{
			//未登陆
			request.setAttribute("isLogin", false);
		}
		//放行
		return true;
	}
	//Handler之后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//页面渲染后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
