package cn.itcast.common.convertion;

import org.springframework.core.convert.converter.Converter;

/**
 * 全局去掉前后空格  转null   从页面 String  --> String
 * @author lx
 *
 */
public class CustomTrimConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		try {
			if(null != source){
				source = source.trim();
				//   sdfdsfdsf      "   "  ""
				if(!"".equals(source)){
					return source;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
