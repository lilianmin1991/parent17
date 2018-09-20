package cn.itcast.core.service.user;

/**
 * Session提供类 远程Sesion 保存Session到Redis服务器
 * 
 * @author lx
 *
 */
public interface SessionProvider {

	// 把用户名保存到Redis服务器中
	public void setAttributeForUsername(String name, String value);

	// 把验证码保存Redis服务器
	public void setAttributeForCode(String name, String value);

	// 获取Redis服务器中的用户名
	public String getAttibuteForUsername(String name);

	// 获取Redis服务器中的验证码
	public String getAttibuteForCode(String name);

}
