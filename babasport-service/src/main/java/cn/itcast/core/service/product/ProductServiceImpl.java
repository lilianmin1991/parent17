package cn.itcast.core.service.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Img;
import cn.itcast.core.bean.product.ImgQuery;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.ProductQuery;
import cn.itcast.core.bean.product.ProductQuery.Criteria;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.bean.product.SkuQuery;
import cn.itcast.core.dao.product.ImgDao;
import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.dao.product.SkuDao;
import cn.itcast.core.service.staticpage.StaticPageService;
import redis.clients.jedis.Jedis;

/**
 * 商品
 * @author lx
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ImgDao imgDao;
	//查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow){
		ProductQuery productQuery = new ProductQuery();
		//当前页
		productQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		productQuery.setPageSize(3);
		
		//排序
		productQuery.setOrderByClause("id desc");
		//条件对象
		Criteria createCriteria = productQuery.createCriteria();
		
		StringBuilder params = new StringBuilder();
		//判断 
		if(null != name){
			createCriteria.andNameLike("%" + name + "%");
			
			params.append("name=").append(name);
		}
		if(null != brandId){
			createCriteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		if(null != isShow){
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{
			createCriteria.andIsShowEqualTo(false);
			params.append("&isShow=").append(false);
		}
		//分页对象
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				productDao.countByExample(productQuery)
				);
		//防止当前页超过最大页数
		productQuery.setPageNo(pagination.getPageNo());
		//商品结果集
		List<Product> products = productDao.selectByExample(productQuery);
		for (Product product : products) {
			ImgQuery imgQuery = new ImgQuery();
			imgQuery.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(true);
			List<Img> imgs = imgDao.selectByExample(imgQuery);
			product.setImg(imgs.get(0));
		}
		//设置结果集
		pagination.setList(products);
		//页面分页展示
		String url = "/product/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	
	//通过商品ID 查询商品一个对象
	public Product selectProdutById(Long id){
		//商品
		Product product = productDao.selectByPrimaryKey(id);
		ImgQuery imgQuery = new ImgQuery();
		imgQuery.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(true);
		List<Img> imgs = imgDao.selectByExample(imgQuery);
		product.setImg(imgs.get(0));
		return product;
	}
	
	
	//查询  返回分页对象   查询数据库 SOlr服务器
	public Pagination selectPaginationbyQueryFromSolr(Integer pageNo,String keyword,Long brandId,String price){
		ProductQuery productQuery = new ProductQuery();
		//当前页
		productQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		productQuery.setPageSize(Product.PAGESIZE);
		//Solr服务器
		SolrQuery solrQuery = new SolrQuery();
		
		StringBuilder params = new StringBuilder();
		
		//关键词
		solrQuery.set("q", "name_ik:" + keyword);
		
		params.append("keyword=").append(keyword);
		//过滤条件
		if(null != brandId){
			solrQuery.addFilterQuery("brandId:" + brandId);
			params.append("&brandId=").append(brandId);
		}
		// 0-79  600
		if(null != price){
			String[] p = price.split("-");
			if(p.length == 2){
				Float startP = new Float(p[0]);
				Float endP = new Float(p[1]);// solr 0.0  79.0
				solrQuery.addFilterQuery("price:[" + startP + " TO " + endP + "]");
			}else{
				
				Float startP = new Float(p[0]);
				//Float endP = new Float("*");// solr 0.0  79.0
				solrQuery.addFilterQuery("price:[" + startP + " TO *]");
			}
			params.append("&price=").append(price);
			
		}
		//排序
		solrQuery.addSort("price", ORDER.asc);
		//高亮
		//1:开启高亮
		solrQuery.setHighlight(true);
		//2:设置高亮字段    <span style='color:red'>name_ik</span>
		solrQuery.addHighlightField("name_ik");
		//3:设置高亮简单的前缀 及后缀
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		
		//分页  开始行  每页数
		solrQuery.setStart(productQuery.getStartRow());
		solrQuery.setRows(Product.PAGESIZE);
		
		//结果集
		SolrDocumentList docs = null;
		QueryResponse response = null;
		try {
			response = solrServer.query(solrQuery);
			
			// k : 277  V  Map
			// k : name_ik  V list
			// List.get(0) 17期卖的<span style='color:red'>瑜伽服</span>新款修身显瘦<span style='color:red'>瑜伽服</span>套装女款健身舞蹈服玫红中袖+长裤) M
			//结果集
			docs = response.getResults();
			
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//分页对象
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				(int)docs.getNumFound()
				);
		//创建商品结果集
		List<Product> products = new ArrayList<Product>();
		for (SolrDocument doc : docs) {
			Product product = new Product();
			//商品ID
			String id = (String) doc.get("id");
			product.setId(Long.parseLong(id));
			
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("name_ik");
			//商品名称
			//String name = (String) doc.get("name_ik");
			product.setName(list.get(0));
			//价格
			Sku sku = new Sku();
			sku.setPrice((Float) doc.get("price"));
			product.setSku(sku);
			//图片Url
			String url = (String) doc.get("url");
			Img img = new Img();
			img.setUrl(url);
			product.setImg(img);
			//品牌ID 
			product.setBrandId(Long.parseLong(String.valueOf((Integer) doc.get("brandId"))));
			//时间
			//结果集
			products.add(product);
			
		}
		//结果集
		pagination.setList(products);
		//页面展示
		String url = "/product/display/list.shtml";
		pagination.pageView(url, params.toString());
		
		return pagination;
	}
	//Jedis生成商品编号
	@Autowired
	private Jedis jedis;
	//保存商品
	public void insertProduct(Product product){
		//保存商品  商品ID自增长
		Long id = jedis.incr("pno");
		product.setId(id);
		//下架
		product.setIsShow(false);
		//不删除
		product.setIsDel(true);
		//时间
		product.setCreateTime(new Date());
		//保存
		productDao.insertSelective(product);
		//保存图片
		Img img = product.getImg();
		//设置默认图片
		img.setIsDef(true);
		
		//商品ID
		img.setProductId(product.getId());
		//保存SKU 库存表 1,2,3,4
		for(String color : product.getColors().split(",")){
			for(String size : product.getSizes().split(",")){
				//创建Sku对象
				Sku sku = new Sku();
				//商品ID
				sku.setProductId(product.getId());
				//颜色ID
				sku.setColorId(Long.parseLong(color));
				//尺码
				sku.setSize(size);
				//运费 10
				sku.setDeliveFee(10f);
				//价格
				sku.setPrice(0f);
				//库存
				sku.setStock(0);
				//
				sku.setUpperLimit(200);
				//时间
				sku.setCreateTime(new Date());
				//市场价
				sku.setMarketPrice(0f);
				//保存Sku
				skuDao.insertSelective(sku);
				
			}
		}
		imgDao.insertSelective(img);
		
		
	}
	//上架
	public void isShow(Long[] ids){
		
		for (Long id : ids) {
			Product product = new Product();
			//上架
			product.setIsShow(true);
			//商品ID
			product.setId(id);
			productDao.updateByPrimaryKeySelective(product);
			
			//TODO 2:保存商品信息到Solr服务器
			SolrInputDocument doc = new SolrInputDocument();
			//商品ID
			doc.setField("id", id);
			//商品名称
			Product p = productDao.selectByPrimaryKey(id);
			doc.setField("name_ik", p.getName());
			//价格
			SkuQuery skuQuery = new SkuQuery();
			skuQuery.createCriteria().andProductIdEqualTo(id);
			skuQuery.setOrderByClause("price asc");
			skuQuery.setPageNo(1);
			skuQuery.setPageSize(1);
			skuQuery.setFields("price");
			List<Sku> skus = skuDao.selectByExample(skuQuery);
			// select price from bbs_sku where product_id = 277 order by price asc limit 0,1
			doc.setField("price", skus.get(0).getPrice());
			//图片Url
			ImgQuery imgQuery = new ImgQuery();
			imgQuery.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(true);
			List<Img> imgs = imgDao.selectByExample(imgQuery);
			doc.setField("url",imgs.get(0).getUrl());
			//品牌
			doc.setField("brandId",p.getBrandId());
			//时间
			doc.setField("last_modified",new Date());
			
			try {
				solrServer.add(doc);
				solrServer.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//数据
			Map<String, Object> root = new HashMap<String,Object>();
			
			//商品 对象   图片
			Product pp = selectProdutById(id);
			root.put("product", pp);
			//库存  颜色 
			List<Sku> ss = skuService.selectSkuListByProductIdWithStock(id);
			root.put("skus", ss);
			
			Set<Color> colors = new HashSet<Color>();
			for (Sku sku : ss) {
				colors.add(sku.getColor());
			}
			root.put("colors", colors);
			
			//TODO 3:静态化
			staticPageService.index(root, id);
		}
		
	}
	
	@Autowired
	private StaticPageService staticPageService;
	@Autowired
	private SkuService skuService;
	
	//从Redis中查询品牌结果集
	public List<Brand> selectBrandListFormRedis(){
		List<Brand> brands = new ArrayList<Brand>();
		Set<String> keys = jedis.keys("brand:*");
		for (String key : keys) {
			Brand brand = new Brand();
			brand.setId(Long.parseLong(jedis.hget(key, "id")));
			brand.setName(jedis.hget(key, "name"));
			brands.add(brand);
		}
		return brands;
	}
	//w从Redis中取品牌名称  通过品牌ID
	public String selectBrandNameById(Long brandId){
		return jedis.hget("brand:" + brandId, "name");
	}
	@Autowired
	private SolrServer solrServer;

}
