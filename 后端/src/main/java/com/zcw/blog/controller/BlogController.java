/**
 * ***************************************************************************** Copyright (c) 2019,
 * 2019 Hirain Technologies Corporation.
 * ****************************************************************************
 */
package com.zcw.blog.controller;

import com.zcw.blog.model.ResponseBo;
import com.zcw.blog.service.FlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/** */
@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {

  @Autowired FlowService flowService;

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
      return ResponseBo.ok(flowService.upload(file));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("发布失败");
    }
  }

  /**
   * 删除上传的图片
   *
   * @param filepaths
   * @return
   */
  @PostMapping("/deleteImg")
  public ResponseBo delete(@RequestParam("filepaths") String filepaths) {
    try {

      return ResponseBo.ok(flowService.delete(filepaths));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("发布失败");
    }
  }
}
