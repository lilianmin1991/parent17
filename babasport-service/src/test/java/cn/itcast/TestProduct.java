package cn.itcast;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.common.junit.SpringJunitTest;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.ProductQuery;
import cn.itcast.core.dao.product.ProductDao;

/**
 * 测试商品
 * @author lx
 *
 */
public class TestProduct extends SpringJunitTest{

	@Autowired
	private ProductDao productDao;
	
	//测试
	@Test
	public void testProduct() throws Exception {
		//添加  删除 修改 查询
		//查询  通过ID 或条件 查询商品   排序   分页   指定字段
		
	/*	Product product = productDao.selectByPrimaryKey(252L);
		System.out.println(product);*/
		// selct * from bbs_product <where> and is_show = 0 and brand_id = 3 and ...
		ProductQuery productQuery = new ProductQuery();
		//下架  条件
		//productQuery.createCriteria().andIsShowEqualTo(false).andBrandIdEqualTo(3L);
		
		//排序
		productQuery.setOrderByClause("id desc");
		//分页
		productQuery.setPageNo(1);
		productQuery.setPageSize(4);
		
		//指定字段
		productQuery.setFields("id,name");
		
		List<Product> products = productDao.selectByExample(productQuery);
		
		for (Product product : products) {
			System.out.println(product);
		}
		
		//productDao.c
		
		
	}
}
