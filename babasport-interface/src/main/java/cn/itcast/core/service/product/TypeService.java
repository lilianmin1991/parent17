package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.core.bean.product.Type;

public interface TypeService {
	
	//查询所有可用的类型
	public List<Type> selectTypeList();

}
