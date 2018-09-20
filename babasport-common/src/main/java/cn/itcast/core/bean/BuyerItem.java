package cn.itcast.core.bean;

import java.io.Serializable;

import cn.itcast.core.bean.product.Sku;

/**
 * 购物项
 * @author lx
 *
 */
public class BuyerItem implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//sku
	private Sku sku;
	
	
	//数量
	private Integer amount = 1;
	//是否有货
	private Boolean isHave = true;
	
	
	
	public Sku getSku() {
		return sku;
	}
	public void setSku(Sku sku) {
		this.sku = sku;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Boolean getIsHave() {
		return isHave;
	}
	public void setIsHave(Boolean isHave) {
		this.isHave = isHave;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)  //对象地址 @123133
			return true;
		if (obj == null)
			return false;//Shift + Ctrl + I
		if (getClass() != obj.getClass()) //class cn.itcast.core.bean.BuyerItem 
			return false;
		BuyerItem other = (BuyerItem) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.getId().equals(other.sku.getId()))
			return false;
		return true;
	}
	
	
	
	
}
