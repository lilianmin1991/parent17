package cn.itcast.core.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.remoting.exchange.Request;

import cn.itcast.common.utils.RequestUtils;
import cn.itcast.core.bean.user.Buyer;
import cn.itcast.core.service.user.BuyerService;
import cn.itcast.core.service.user.SessionProvider;

/**
 * 单点登陆
 * 
 * @author lx
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private SessionProvider sessionProvider;

	// 去登陆页面
	@RequestMapping(value = "/shopping/login.aspx",method=RequestMethod.GET)
	public String login() {

		return "buyer/login";
	}
	//提交登陆表单
	@RequestMapping(value = "/shopping/login.aspx",method=RequestMethod.POST)
	public String login(String username,String password,String code,String returnUrl
			,HttpServletRequest request,HttpServletResponse response,Model model) {
		//1：验证码不能为空
		if(null != code){
			//2:验证码必须正确
			String c = sessionProvider.getAttibuteForCode(RequestUtils.getCSESSIONID(response, request));
			if(c.equalsIgnoreCase(code)){
				//3:用户名不能为空
				if(null != username){
					//4:密码不能为空
					if(null != password){
						//5:用户必须正确
						Buyer buyer = buyerService.selectBuyerByUsername(username);
						if(null != buyer){
							//6:密码必须正确
							if(encodePassword(password).equals(buyer.getPassword())){
								//7:放用户到Session中
								sessionProvider.setAttributeForUsername(RequestUtils.getCSESSIONID(response, request)
										, buyer.getUsername());
								//8:跳转回之前访问页面
								return "redirect:" + returnUrl;
								
							}else{
								model.addAttribute("error", "密码必须正确");
								return "buyer/login";
							}
							
						}else{
							model.addAttribute("error", "用户必须正确");
							return "buyer/login";
						}
					}else{
						model.addAttribute("error", "密码不能为空");
						return "buyer/login";
					}
					
				}else{
					model.addAttribute("error", "用户名不能为空");
					return "buyer/login";
				}
				
			}else{
				model.addAttribute("error", "验证码必须正确");
				return "buyer/login";
			}
			
		}else{
			model.addAttribute("error", "验证码不能为空");
			return "buyer/login";
		}
	}
	
	//加密 
	public String encodePassword(String passowrd){
		String algorithm = "MD5";
		
	//	passowrd = "sdfsafwefe" + passowrd + "fdhjghgkrth";
		
	//	passowrd = "sfasdfsafg123456weterytyrtyjrjfgjrfhhr";
		
		char[] encodeHex = null;
		try {
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			byte[] digest = instance.digest(passowrd.getBytes());
			//十六进制加密
			encodeHex = Hex.encodeHex(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(encodeHex);
	}
	
	public static void main(String[] args) {
		LoginController l = new LoginController();
		String e = l.encodePassword("123456");
		System.out.println(e);
	}

	// 验证码生成
	@RequestMapping(value = "/shopping/getCodeImage.aspx")
	public void getCodeImage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("#######################生成数字和字母的验证码#######################");
		BufferedImage img = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();

		Random r = new Random();

		Color c = new Color(200, 150, 255);

		g.setColor(c);

		// 填充整个图片的颜色

		g.fillRect(0, 0, 68, 22);

		// 向图片中输出数字和字母

		StringBuffer sb = new StringBuffer();

		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		int index, len = ch.length;

		for (int i = 0; i < 4; i++) {

			index = r.nextInt(len);

			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt

			(255)));

			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
			// 输出的 字体和大小

			g.drawString("" + ch[index], (i * 15) + 3, 18);
			// 写什么数字，在图片 的什么位置画

			sb.append(ch[index]);

		}
		// 把上面生成的验证码放到Session域中
		sessionProvider.setAttributeForCode(RequestUtils.getCSESSIONID(response, request), sb.toString());
		try {
			ImageIO.write(img, "JPG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
