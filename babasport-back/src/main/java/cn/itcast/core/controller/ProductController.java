package cn.itcast.core.controller;

import java.util.List;

import org.apache.solr.common.util.RetryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Type;
import cn.itcast.core.service.product.BrandService;
import cn.itcast.core.service.product.ColorService;
import cn.itcast.core.service.product.ProductService;
import cn.itcast.core.service.product.TypeService;

/**
 * 商品管理
 * 列表
 * 添加
 * 上架
 * @author lx
 *
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private ColorService colorService;
	
	//查询
	@RequestMapping(value = "/product/list.do")
	public String list(Integer pageNo,String name,Long brandId,Boolean isShow,Model model){
		//查询品牌结果集
		List<Brand> brands = brandService.selectBrandListByQuery(null, 1);
		model.addAttribute("brands", brands);
		
		Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("brandId", brandId);
		model.addAttribute("isShow", isShow);
		
		return "product/list";
	}
	//去添加页面
	@RequestMapping(value = "/product/toAdd.do")
	public String toAdd(Model model){
		//品牌
		//查询品牌结果集
		List<Brand> brands = brandService.selectBrandListByQuery(null, 1);
		model.addAttribute("brands", brands);
		//类型
		List<Type> types = typeService.selectTypeList();
		model.addAttribute("types", types);
		//颜色
		List<Color> colors = colorService.selectColorList();
		model.addAttribute("colors", colors);
		
		return "product/add";
	}
	//添加页面提交
	@RequestMapping(value = "/product/add.do")
	public String add(Product product,Model model){
		//保存
		productService.insertProduct(product);
		return "redirect:/product/list.do";
	}
	//上架
	@RequestMapping(value = "/product/isShow.do")
	public String isShow(Long[] ids){
		
		//上架 Service
		productService.isShow(ids);
		
		return "redirect:/product/list.do"; 
	}
	
	
}
