package cn.itcast.core.dao.product;

import java.util.List;

import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.BrandQuery;

public interface BrandDao {

	//查询
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);
	
	//查询总条件（符合条件)
	public Integer selectCount(BrandQuery brandQuery);
	
	//保存
	public void insertBrand(Brand brand);
	
	//删除
	public void deletes(Long[] ids);
	
	//查询通过ID
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrand(Brand brand);
	
}
