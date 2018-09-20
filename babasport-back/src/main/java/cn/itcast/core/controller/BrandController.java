package cn.itcast.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.service.product.BrandService;

/**
 * 品牌管理
 * 列表
 * 添加
 * 修改
 * 删除
 * @author lx
 *
 */
@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;
	//品牌列表查询
	@RequestMapping(value = "/brand/list.do")
	public String list(Integer pageNo,String name,Integer isDisplay,Model model){
		//默认值
		if(null == isDisplay){
			isDisplay = 1;
		}
		Pagination pagination = brandService.selectPaginationByQuery(pageNo, name, isDisplay);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("isDisplay", isDisplay);
		
		return "brand/list";
	}
	//去添加页面
	@RequestMapping(value = "/brand/toAdd.do")
	public String toAdd(){
		return "brand/add";
	}
	//添加 提交
	@RequestMapping(value = "/brand/add.do")
	public String add(Brand brand){
		//保存
		brandService.insertBrand(brand);
		
		return "redirect:/brand/list.do";
	}
	//批量删除
	@RequestMapping(value = "/brand/deletes.do")
	public String deletes(Long[] ids,String name,Integer isDisplay,Integer pageNo,Model model){
		//删除
		brandService.deletes(ids);
		
		if(null != name){
			model.addAttribute("name", name);
		}
		if(null != isDisplay){
			model.addAttribute("isDisplay", isDisplay);
		}
		if(null != pageNo){
			model.addAttribute("pageNo", pageNo);
		}
		
		return "redirect:/brand/list.do";
	}
	//去修改页面
	@RequestMapping(value = "/brand/toEdit.do")
	public String toEdit(Long id,Model model){
		//查询一个品牌通过ID
		Brand brand = brandService.selectBrandById(id);
		
		model.addAttribute("brand", brand);
		
		return "brand/edit";
	}
	//修改
	@RequestMapping(value = "/brand/edit.do")
	public String edit(Brand brand){
		//修改
		brandService.updateBrand(brand);
		
		return "redirect:/brand/list.do";
	}
}
