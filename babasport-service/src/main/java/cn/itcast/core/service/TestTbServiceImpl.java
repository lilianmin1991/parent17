package cn.itcast.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.bean.TestTb;
import cn.itcast.core.dao.TestTbDao;

/**
 * 测试
 * @author lx
 *
 */
@Service("testTbService")
@Transactional
public class TestTbServiceImpl implements TestTbService {

	@Autowired
	private TestTbDao testTbDao;
	@Override
	public void insertTestTb(TestTb testTb) {
		// TODO Auto-generated method stub
		testTbDao.insertTestTb(testTb);
		
		throw new RuntimeException();
	}
	
	@Override
	public List<TestTb> selectTestTbList() {
		// TODO Auto-generated method stub
		return testTbDao.selectTestTbList();
	}

}
