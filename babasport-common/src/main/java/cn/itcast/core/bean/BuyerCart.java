package cn.itcast.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 购物车
 * @author lx
 *
 */
public class BuyerCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//商品结果集
	private List<BuyerItem> items = new ArrayList<BuyerItem>();
	
	
	//清理
	public void clearCart(){
		items.clear();
	}
	
	//添加购物项
	public void addItem(BuyerItem buyerItem){
		//判断 传入的buyerItem 是否在上面的List集合已经存在 如果存在 追加数量
		if(items.contains(buyerItem)){
			for (BuyerItem item : items) {
				if(item.equals(buyerItem)){
					item.setAmount(item.getAmount() + buyerItem.getAmount());
				}
			}
		}else{
			items.add(buyerItem);
		}
		
	}

	public List<BuyerItem> getItems() {
		return items;
	}

	public void setItems(List<BuyerItem> items) {
		this.items = items;
	}
	
	//小计
	//商品数量
	@JsonIgnore
	public Integer getProductAmount(){
		Integer result = 0;
		for (BuyerItem item : items) {
			result += item.getAmount();
		}
		return result;
	}
	//商品金额
	@JsonIgnore
	public Float getProductPrice(){
		Float result = 0f;
		for (BuyerItem item : items) {
			result += item.getAmount()*item.getSku().getPrice();
		}
		return result;
	}
	//运费
	@JsonIgnore
	public Float getFee(){
		Float result = 0f;
		if(getProductPrice()<79){
			result = 5f;
		}
		return result;
	}
	//应付金额
	@JsonIgnore
	public Float getTotalPrice(){
		return getProductPrice() + getFee();
	}
	
	
	
	
	
	
	
}
