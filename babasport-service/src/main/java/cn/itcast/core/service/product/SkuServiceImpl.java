package cn.itcast.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.bean.BuyerCart;
import cn.itcast.core.bean.BuyerItem;
import cn.itcast.core.bean.product.Img;
import cn.itcast.core.bean.product.ImgQuery;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.bean.product.SkuQuery;
import cn.itcast.core.dao.product.ColorDao;
import cn.itcast.core.dao.product.ImgDao;
import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.dao.product.SkuDao;
import redis.clients.jedis.Jedis;

/**
 * 库存管理
 * @author lx
 *
 */
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ImgDao imgDao;
	
	@Autowired
	private Jedis jedis;
	//查询Sku结果集 商品ID
	public List<Sku> selectSkuListByProductId(Long productId){
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}
	//查询Sku结果集 商品ID  库存 大于0
	public List<Sku> selectSkuListByProductIdWithStock(Long productId){
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId).andStockGreaterThan(0);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}
	
	@Override
	public void updateSkuById(Sku sku) {
		// TODO Auto-generated method stub
		skuDao.updateByPrimaryKeySelective(sku);
	}
	//通过SKuID查询一个Sku对象（商品对象（图片对象）、颜色对象）
	public Sku selectSkuById(Long id){
		//Sku对象
		Sku sku = skuDao.selectByPrimaryKey(id);
		//颜色对象
		sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		Product product = productDao.selectByPrimaryKey(sku.getProductId());
		ImgQuery imgQuery = new ImgQuery();
		imgQuery.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(true);
		List<Img> imgs = imgDao.selectByExample(imgQuery);
		product.setImg(imgs.get(0));
		
		sku.setProduct(product);
		
		return sku;
	}
	//放购物车中数据到Redis服务器
	public void insertBuyerCartToRedis(BuyerCart buyerCart,String username){
		//遍历
		for (BuyerItem buyerItem : buyerCart.getItems()) {
			//判断添加的商品是否在Redis服务器已经存在了
			if(jedis.hexists("buyerItem:" + username, String.valueOf(buyerItem.getSku().getId()))){
				//存在就追加数量
				jedis.hincrBy("buyerItem:" + username,  String.valueOf(buyerItem.getSku().getId())
						, buyerItem.getAmount());
			}else{
				//购物车
				jedis.lpush("buyerCart:" + username, String.valueOf(buyerItem.getSku().getId()));
				//购物项
				jedis.hset("buyerItem:" + username, String.valueOf(buyerItem.getSku().getId())
						, String.valueOf(buyerItem.getAmount()));
			}
		}
		
	}
	//取出所有商品从Redis
	public BuyerCart selectBuyerCartFromRedis(String username){
		BuyerCart buyerCart = new BuyerCart();
		//获redis 遍历所有商品
		List<String> skuIds = jedis.lrange("buyerCart:" + username, 0, -1);
		
		for (String skuId : skuIds) {
			Sku sku  = new Sku();
			sku.setId(Long.parseLong(skuId));
			BuyerItem buyerItem = new BuyerItem();
			buyerItem.setSku(sku);
			buyerItem.setAmount(Integer.parseInt(jedis.hget("buyerItem:" + username, skuId)));
			//购物车添加购物项
			buyerCart.addItem(buyerItem);
		}
		
		
		return buyerCart;
	}
}
