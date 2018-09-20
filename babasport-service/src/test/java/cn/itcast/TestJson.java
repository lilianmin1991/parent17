package cn.itcast;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.core.bean.user.Buyer;

public class TestJson {

	@Test
	public void testJson() throws Exception {
		
		Buyer buyer = new Buyer();
		buyer.setUsername("范冰冰");
		//类
		ObjectMapper om = new ObjectMapper();
		//不要转null
		om.setSerializationInclusion(Include.NON_NULL);
		//JSON 串
		StringWriter w = new StringWriter();
		
		om.writeValue(w, buyer);
		
		System.out.println(w.toString());
		
		//json 转回对象
		Buyer r = om.readValue(w.toString(), Buyer.class);
		System.out.println(r);
		
		
	}
}
