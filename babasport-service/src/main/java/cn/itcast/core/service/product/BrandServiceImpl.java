package cn.itcast.core.service.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.BrandQuery;
import cn.itcast.core.dao.product.BrandDao;
import redis.clients.jedis.Jedis;

/**
 * 品牌
 * @author lx
 *
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandDao brandDao;

	
	//查询结果信
	//查询
	public List<Brand> selectBrandListByQuery(String name,Integer isDisplay){
		BrandQuery brandQuery = new BrandQuery();
		if(null != name){
			brandQuery.setName(name);
		}
		if(null != isDisplay){
			brandQuery.setIsDisplay(isDisplay);
		}
		
		return brandDao.selectBrandListByQuery(brandQuery);
	}
	//查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay){
		StringBuilder params = new StringBuilder();
		BrandQuery brandQuery = new BrandQuery();
		if(null != name){
			brandQuery.setName(name);
			params.append("name=").append(name);
		}
		if(null != isDisplay){
			brandQuery.setIsDisplay(isDisplay);
			params.append("&isDisplay=").append(isDisplay);
		}else{
			params.append("&isDisplay=").append(1);
		}
		//设置当前页
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		brandQuery.setPageSize(3);
		//实例化分页对象
		Pagination pagination = new Pagination(
				brandQuery.getPageNo(),
				brandQuery.getPageSize(),
				brandDao.selectCount(brandQuery)
				);
		pagination.setList(brandDao.selectBrandListByQuery(brandQuery));
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	@Override
	public void insertBrand(Brand brand) {
		// TODO Auto-generated method stub
		brandDao.insertBrand(brand);
	}
	//删除
	public void deletes(Long[] ids){
		brandDao.deletes(ids);
	}
	//查询通过ID
	public Brand selectBrandById(Long id){
		return brandDao.selectBrandById(id);
	}
	@Autowired
	private Jedis jedis;
	//修改
	public void updateBrand(Brand brand){
		//保存Redis一份
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("id", String.valueOf(brand.getId()));
		hash.put("name", brand.getName());
		jedis.hmset("brand:" + brand.getId(), hash);
		brandDao.updateBrand(brand);
	}
}
