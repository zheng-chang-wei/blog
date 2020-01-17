/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.zcw.blog.service.impl;

import com.zcw.blog.service.FlowService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Version 1.0
 * @Author jianwen.xin@hirain.com
 * @Created 2019年5月28日 下午5:27:20
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2019年5月28日 jianwen.xin@hirain.com 1.0 create file
 */
@Service
public class FlowServiceImpl implements FlowService {

	// @Value("${file.upload.dir}")
	private String uploadRoot = System.getProperty("user.dir") + "//imgs";


	@Override
	public String upload(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			throw new IOException("文件为空");
		}
		File root = new File(uploadRoot);
		if (!root.exists()) {
			root.mkdirs();
		}
		String extension = getExtension(file.getOriginalFilename());
		String filename = UUID.randomUUID().toString() + extension;
		File serverFile = new File(root, filename);
		file.transferTo(serverFile);
		return filename;
	}

	/**
	 */
	@Override
	public String delete(String filepaths) {
		String[] pathArray = filepaths.split(",");
		File root = new File(uploadRoot);
		for (int i = 0; i < pathArray.length; i++) {
			String path = pathArray[i];
			File file = new File(root, path);
			if (file.exists()) {
				file.delete();
			}
		}
		return "删除成功";
	}
	/**
	 * @param filename
	 * @return
	 */
	private String getExtension(String filename) {
		int lastIndexOf = filename.lastIndexOf(".");
		String extension = filename.substring(lastIndexOf);
		return extension;
	}

}
