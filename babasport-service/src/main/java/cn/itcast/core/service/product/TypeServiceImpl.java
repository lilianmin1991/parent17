package cn.itcast.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.core.bean.product.Type;
import cn.itcast.core.bean.product.TypeQuery;
import cn.itcast.core.dao.product.TypeDao;



/**
 * 类型
 * @author lx
 *
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeDao typeDao;
	//查询所有可用的类型
	public List<Type> selectTypeList(){
		TypeQuery typeQuery = new TypeQuery();
		typeQuery.createCriteria().andParentIdNotEqualTo(0L).andIsDisplayEqualTo(true);
		return typeDao.selectByExample(typeQuery);
	}

}
