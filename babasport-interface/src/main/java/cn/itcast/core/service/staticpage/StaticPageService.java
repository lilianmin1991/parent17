package cn.itcast.core.service.staticpage;

import java.util.Map;

public interface StaticPageService {
	
	//静态化
	public void index(Map<String,Object> root,Long id);

}
