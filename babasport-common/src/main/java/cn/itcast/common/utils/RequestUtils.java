package cn.itcast.common.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求Request工具类
 * @author lx
 *
 */
public class RequestUtils {

	
	//获取Cookie中的CSESSIONID
	public static String getCSESSIONID(HttpServletResponse response,HttpServletRequest request){
		//1:从Request中取Cookie
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0){
			for (Cookie cookie : cookies) {
				//2:从Cookie中取CSESSIONID
				if("CSESSIONID".equals(cookie.getName())){
					//4:如果有  直接使用此CSESSIONID
					return cookie.getValue();
				}
			}
		}
		//3:如果第一次  没有 创建CSESSIONID 
		String csessionid = UUID.randomUUID().toString().replaceAll("-", "");
		//5:放到Cookie 写回浏览器
		Cookie cookie = new Cookie("CSESSIONID",csessionid);
		//设置路径 /
		cookie.setPath("/");
		//存活时间  -1  0  >0  60*60*24*7
		cookie.setMaxAge(-1);
		//写回浏览器
		response.addCookie(cookie);
		
		//返回CSESSIONID
		return csessionid;
	}
}
