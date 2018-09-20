package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.core.bean.BuyerCart;
import cn.itcast.core.bean.product.Sku;

public interface SkuService {
	
	//查询Sku结果集 商品ID
	public List<Sku> selectSkuListByProductId(Long productId);
	
	//保存
	public void updateSkuById(Sku sku);
	
	//查询Sku结果集 商品ID  库存 大于0
	public List<Sku> selectSkuListByProductIdWithStock(Long productId);
	
	//通过SKuID查询一个Sku对象（商品对象（图片对象）、颜色对象）
	public Sku selectSkuById(Long id);
	
	//放购物车中数据到Redis服务器
	public void insertBuyerCartToRedis(BuyerCart buyerCart,String username);
	
	//取出所有商品从Redis
	public BuyerCart selectBuyerCartFromRedis(String username);

}
