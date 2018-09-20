package cn.itcast.core.service.user;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.core.web.Constants;
import redis.clients.jedis.Jedis;

/**
 * Session提供类  远程Sesion 保存Session到Redis服务器
 * @author lx
 *
 */
public class CacheSessionProvider implements SessionProvider {

	@Autowired
	private Jedis jedis;
	
	//用户时间  单位分钟
	private Integer exp = 30;
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	//验证码
	private Integer expCode = 1;
	public void setExpCode(Integer expCode) {
		this.expCode = expCode;
	}



	//把用户名保存到Redis服务器中 
	public void setAttributeForUsername(String name,String value){
		//用户名
		// name  user_name
		//name  jsessionid
		jedis.set(name + ":" + Constants.USER_NAME, value);
		//设置存活时间
		jedis.expire(name + ":" + Constants.USER_NAME, 60*exp);
	}
	
	//把验证码保存Redis服务器
	public void setAttributeForCode(String name,String value){
		
		jedis.set(name + ":" + Constants.CODE_NAME, value);
		//设置存活时间
		jedis.expire(name + ":" + Constants.CODE_NAME, 60*expCode);
	}
	
	//获取Redis服务器中的用户名
	public String getAttibuteForUsername(String name){
		String value = jedis.get(name + ":" + Constants.USER_NAME);
		if(null != value){
			//设置存活时间
			jedis.expire(name + ":" + Constants.USER_NAME, 60*exp);
		}
		return value;
	}
	
	//获取Redis服务器中的验证码
	public String getAttibuteForCode(String name){
		String value = jedis.get(name + ":" + Constants.CODE_NAME);
		if(null != value){
			//设置存活时间
			jedis.del(name + ":" + Constants.CODE_NAME);
		}
		return value;
	}
}
