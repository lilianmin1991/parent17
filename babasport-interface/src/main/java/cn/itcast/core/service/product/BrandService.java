package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;

public interface BrandService {

	// 查询结果信
	// 查询
	public List<Brand> selectBrandListByQuery(String name, Integer isDisplay);
	
	
	//查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay);
	
	//保存
	public void insertBrand(Brand brand);
	
	//删除
	public void deletes(Long[] ids);
	
	//查询通过ID
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrand(Brand brand);

}
