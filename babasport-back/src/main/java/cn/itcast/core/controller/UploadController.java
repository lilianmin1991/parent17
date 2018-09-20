package cn.itcast.core.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.itcast.core.service.product.UploadService;
import cn.itcast.core.web.Constants;
import net.fckeditor.response.UploadResponse;

/**
 * 上传图片
 * @author lx
 *
 */
@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	//上传图片   file
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic,HttpServletResponse response) throws Exception{
		//上传图片
		String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
		JSONObject jo = new JSONObject();
		jo.put("path", path);
		jo.put("url", Constants.IMG_URL + path);
		//设置成json
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
		
	}
	//上传Fck图片
	@RequestMapping(value = "/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//无敌版接收  
		//Springmvc MultipartRequest  进行强转
		MultipartRequest  mr = (MultipartRequest)request;
		//只有图片   一张  认为多张
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		//遍历Map
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		
		for (Entry<String, MultipartFile> entry : entrySet) {
			//图片到手
			MultipartFile pic = entry.getValue();
			
			//上传图片
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			//显示图片的路径
			String fileUrl = Constants.IMG_URL + path;
			//Fck接收  url 采用Fck自已的Jar包   fck-core.jar  --->  java-core.jar
			//Fck 对象
			UploadResponse ok = UploadResponse.getOK(fileUrl);
			//字符流 与 字节流
			response.getWriter().print(ok);
			
		}
		
		
		
	}
}
