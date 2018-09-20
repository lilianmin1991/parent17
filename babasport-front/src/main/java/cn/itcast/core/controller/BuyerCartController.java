package cn.itcast.core.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.support.spring.DruidNativeJdbcExtractor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.common.utils.RequestUtils;
import cn.itcast.core.bean.BuyerCart;
import cn.itcast.core.bean.BuyerItem;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.bean.user.Buyer;
import cn.itcast.core.service.product.SkuService;
import cn.itcast.core.service.user.SessionProvider;
import cn.itcast.core.web.Constants;

/**
 * 购物车
 * 去购物车页面
 * 清空购物车
 * 删除购物车里一款商品
 * +-
 * @author lx
 *
 */
@Controller
public class BuyerCartController {

	@Autowired
	private SkuService skuService;
	@Autowired
	private SessionProvider sessionProvider;
	
	//去购物车页面
	@RequestMapping(value = "/shopping/buyerCart.shtml")
	public String buyerCart(Long skuId ,Integer amount,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//声明购物车
		BuyerCart buyerCart = null;//对象转JSON   JSON转对象
		//类
		ObjectMapper om = new ObjectMapper();
		//不要转null
		om.setSerializationInclusion(Include.NON_NULL);
		//购物车对象
		//1：获取Cookie中的购物车
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(Constants.BUYER_CART)){
					buyerCart = om.readValue(cookie.getValue(), BuyerCart.class);
					break;
				}
			}
		}
		//2:没有--》创建一个购物车   
		if(null == buyerCart){
			buyerCart = new BuyerCart();
		}
		//判断skuId  添加当前款
		if(null != skuId){
			//当前商品追加到购物车中
			Sku sku  = new Sku();
			sku.setId(skuId);
			BuyerItem buyerItem = new BuyerItem();
			buyerItem.setSku(sku);
			buyerItem.setAmount(amount);
			//购物车添加购物项
			buyerCart.addItem(buyerItem);
		}
		
		//判断 是否登陆
		String username = sessionProvider.getAttibuteForUsername(RequestUtils.getCSESSIONID(response, request));
		//判断用户是否登陆
		if(null != username){
			//登陆
			skuService.insertBuyerCartToRedis(buyerCart, username);
			//清理购物车
			buyerCart.clearCart();
			//清理Cookie
			Cookie cookie = new Cookie(Constants.BUYER_CART, null);
			//设置时间
			cookie.setMaxAge(0);
			//设置路径 
			cookie.setPath("/");
			
			response.addCookie(cookie);
			
			//取出所有购物车中商品
			buyerCart = skuService.selectBuyerCartFromRedis(username);
			
		}else{
			//非登陆
			//3:当前商品追加到购物车中 再把购物车对象添加到Cookie中 覆盖原来的Cookie里购物车
			if(null != skuId){
				//再把购物车对象添加到Cookie中 覆盖原来的Cookie里购物车
				//JSON 串
				StringWriter w = new StringWriter();
				om.writeValue(w, buyerCart);
				Cookie cookie = new Cookie(Constants.BUYER_CART, w.toString());
				//设置时间
				cookie.setMaxAge(60*60*24);
				//设置路径 
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			//排序
			Collections.sort(buyerCart.getItems(), new Comparator<BuyerItem>() {

				@Override
				public int compare(BuyerItem o1, BuyerItem o2) {
					// TODO Auto-generated method stub
					//判断
					//  1 0 -1 
					return -1;
				}
				
			});
		}
		
		//4:购物车装满
		List<BuyerItem> items = buyerCart.getItems();
		
		
		if(items.size() > 0){
			for (BuyerItem buyerItem : items) {
				Sku sku = skuService.selectSkuById(buyerItem.getSku().getId());
				buyerItem.setSku(sku);
			}
		}
		//5:装满的购物车回显到购物车页面
		model.addAttribute("buyerCart", buyerCart);
		
		return "product/cart";
	}
}
