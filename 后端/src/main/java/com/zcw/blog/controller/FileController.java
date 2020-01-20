package com.zcw.blog.controller;

import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

  @Autowired FileService fileService;

  /**
   * 上传图片文件
   *
   * @param file
   * @return 文件路径
   * @throws IOException
   */
  @PostMapping("/uploadImg")
  public ResponseBo upload(MultipartFile file) {
    try {
      return ResponseBo.ok(fileService.upload(file));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("上传失败");
    }
  }
}
