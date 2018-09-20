package cn.itcast.core.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.service.product.ProductService;
import cn.itcast.core.service.product.SkuService;

/**
 * 前台商品
 * @author lx
 *
 */
@Controller
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	@Autowired
	private SkuService skuService;

	//去列表页面
	@RequestMapping(value = "/product/display/list.shtml")
	public String list(Integer pageNo,String keyword,Long brandId,String price,Model model){
		//查询品牌结果集
		//List<Brand> brands = productService.selectBrandListFormRedis();
		Brand brand = new Brand();
		model.addAttribute("brands", brand);
		
		//标识
		Boolean flag = false;
		Map<String,String> map = new HashMap<String,String>();
		//判断
		if(null != brandId){
			flag = true;
			model.addAttribute("brandId", brandId);
			
			map.put("品牌", productService.selectBrandNameById(brandId));
		}
		if(null != price){
			
			flag = true;
			model.addAttribute("price", price);
			map.put("价格", price);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("map", map);
		
		
		
		//productService.
		Pagination pagination = productService.selectPaginationbyQueryFromSolr(pageNo, keyword,brandId,price);
		
		model.addAttribute("pagination", pagination);
		
		
		return "product/product";
	}
	//去商品详情页面
	@RequestMapping(value = "/product/detail.shtml")
	public String detail(Long id,Model model){
		//商品 对象   图片
		Product product = productService.selectProdutById(id);
		model.addAttribute("product", product);
		//库存  颜色 
		List<Sku> skus = skuService.selectSkuListByProductIdWithStock(id);
		model.addAttribute("skus", skus);
		
		Set<Color> colors = new HashSet<Color>();
		for (Sku sku : skus) {
			colors.add(sku.getColor());
		}
		model.addAttribute("colors", colors);
		
		
		
		return "product/productDetail";
	}
}
