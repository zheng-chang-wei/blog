package com.zcw.blog.service.impl;

import com.zcw.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

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

  /** */
  @Override
  public String delete(String fileName) {
    File file = new File(uploadRoot, fileName);
    if (file.exists()) {
      file.delete();
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
