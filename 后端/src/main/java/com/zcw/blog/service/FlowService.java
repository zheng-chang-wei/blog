/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.zcw.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FlowService {

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	String upload(MultipartFile file) throws IOException;

	/**
	 * @param filepaths
	 * @return
	 */
	String delete(String filepaths);
}
