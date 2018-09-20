package cn.itcast.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.bean.TestTb;
import cn.itcast.core.service.TestTbService;

/**
 * 后台管理  Control
 * @author lx
 *
 */
@Controller
@RequestMapping(value = "/control")
public class CenterController {

	
	//入口
	@RequestMapping(value = "/index.do")
	public String index(Model model){
		//
		
		return "index";
	}
	//头   Springmvc 相对路径
	@RequestMapping(value = "/top.do")
	public String top(Model model){
		
		
		return "top";
	}
	//身体
	@RequestMapping(value = "/main.do")
	public String main(Model model){
		
		
		return "main";
	}
	//左
	@RequestMapping(value = "/left.do")
	public String left(Model model){
		
		
		return "left";
	}
	//右
	@RequestMapping(value = "/right.do")
	public String right(Model model){
		
		
		return "right";
	}
	
	
	
	
	@Autowired
	private TestTbService testTbService;
	
	//测试
	@RequestMapping(value = "/test/index.do")
	public String index1(Model model){
		
		//testTbService.insertTestTb(testTb);
		//Shift + alt + L
		List<TestTb> testTbs = testTbService.selectTestTbList();
		
		model.addAttribute("testTbs", testTbs);
		
		return "index";
	}
}
