package cn.itcast.core.dao;

import java.util.List;

import cn.itcast.core.bean.TestTb;

public interface TestTbDao {

	
	//保存
	public void insertTestTb(TestTb testTb);
	
	//查询
	public List<TestTb> selectTestTbList();
}
