package cn.itcast;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.common.junit.SpringJunitTest;

/**
 * Solr服务的Java接口
 * @author lx
 *
 */
public class TestSolrJ extends SpringJunitTest{

	
	@Autowired
	private SolrServer solrServer;
	@Test
	public void testSolrJForSpring() throws Exception {
		
		SolrInputDocument doc = new SolrInputDocument();
		//ID
		doc.setField("id", "3");
		//名称
		doc.setField("name", "何灵");
		
		//添加
		solrServer.add(doc);
		//提交
		solrServer.commit();
	}
	//查询
	@Test
	public void testSolrForSearch() throws Exception {
		SolrQuery params = new SolrQuery();
		
		params.set("q", "*:*");
		
		
		QueryResponse response = solrServer.query(params);
		//结果集
		SolrDocumentList docs = response.getResults();
		//总条数
		long numFound = docs.getNumFound();
		
		System.out.println(numFound);
		
		for (SolrDocument doc : docs) {
			String id = (String) doc.get("id");
			
			System.out.println(id);
			
			String name = (String) doc.get("name");
			System.out.println(name);
		}
		
		
	}
	
	@Test
	public void testSolrJForAdd() throws Exception {
		String baseURL = "http://192.168.200.128:8080/solr";
		//创建连接服务器客户端
		SolrServer solrServer = new HttpSolrServer(baseURL);
		
		SolrInputDocument doc = new SolrInputDocument();
		//ID
		doc.setField("id", "2");
		//名称
		doc.setField("name", "范冰冰");
		
		//添加
		solrServer.add(doc);
		//提交
		solrServer.commit();
	}
}
