package cn.itcast;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.common.junit.SpringJunitTest;
import redis.clients.jedis.Jedis;

/**
 * Jedis测试
 * @author lx
 *
 */
public class TestJedis extends SpringJunitTest {

	@Autowired
	Jedis jedis;
	//交由Spring管理Jedis
	@Test
	public void testSpringJedis() throws Exception {
		Long incr = jedis.incr("pno");
		System.out.println(incr);
	}
	
	//单机版
	@Test
	public void testJedis() throws Exception {
		Jedis jedis = new Jedis("39.105.85.33",6379);
		String pno = jedis.get("pno");
		System.out.println(pno);
		Long incr = jedis.incr("pno");
		System.out.println(incr);
		Long incrBy = jedis.incrBy("pno", 5);
		System.out.println(incrBy);
		jedis.close();
	}
}
