package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Product;

public interface ProductService {
	
	
	//查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow);
	
	//保存商品
	public void insertProduct(Product product);
	
	
	//上架
	public void isShow(Long[] ids);
	
	//查询  返回分页对象   查询数据库 SOlr服务器
	public Pagination selectPaginationbyQueryFromSolr(Integer pageNo,String keyword,Long brandId,String price);
	
	//从Redis中查询品牌结果集
	public List<Brand> selectBrandListFormRedis();
	
	//w从Redis中取品牌名称  通过品牌ID
	public String selectBrandNameById(Long brandId);
	
	
	//通过商品ID 查询商品一个对象
	public Product selectProdutById(Long id);

}
