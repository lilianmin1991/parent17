package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.core.bean.product.Color;

public interface ColorService {

	
	//查询所有颜色 
	public List<Color> selectColorList();
}
