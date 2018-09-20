package cn.itcast.core.service.product;

import org.springframework.stereotype.Service;

import cn.itcast.common.fastdfs.FastDFSUtils;

/**
 * 上传图片
 * @author lx
 *
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	//上传图片
	public String uploadPic(byte[] pic,String name,Long size) throws Exception{
		return FastDFSUtils.uploadPic(pic, name, size);
	}
}
