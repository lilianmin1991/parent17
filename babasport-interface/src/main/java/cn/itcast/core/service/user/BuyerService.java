package cn.itcast.core.service.user;

import cn.itcast.core.bean.user.Buyer;

public interface BuyerService {

	//查询
	public Buyer selectBuyerByUsername(String username);
}
