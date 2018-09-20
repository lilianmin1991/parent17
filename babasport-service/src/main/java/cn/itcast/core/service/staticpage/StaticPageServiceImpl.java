package cn.itcast.core.service.staticpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.dubbo.remoting.exchange.Request;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 静态化服务类
 * @author lx
 *
 */
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{

	//声明
	private Configuration conf;
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.conf = freeMarkerConfigurer.getConfiguration();
	}



	//静态化
	public void index(Map<String,Object> root,Long id){
		//Freemarker程序
		//模板 +  数据模型 = 输出
		// "/html/product/" + id + ".html"     (商品ID)
		String path = getPath("/html/product/" + id + ".html");
		File f = new File(path);
		//   "/html/product/"
		File parentFile = f.getParentFile();
		if(!parentFile.exists()){
			parentFile.mkdirs();
		}
		Writer out = null;
		try {
			//模板名称   读UTF-8
			Template template = conf.getTemplate("productDetail.html");
			//路径绝对路径    写utf-8
			out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			
			template.process(root, out);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//获取全路径   name == /html/product/
	public String getPath(String name){
		return servletContext.getRealPath(name);
	}
	//声明
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
		
	}
}
