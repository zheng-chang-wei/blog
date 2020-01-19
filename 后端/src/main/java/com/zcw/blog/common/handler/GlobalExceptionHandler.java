package com.zcw.blog.common.handler;

import com.zcw.blog.common.model.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  //  @ExceptionHandler(value = UnauthenticatedException.class)
  //  public Object handleException(HttpServletRequest request, UnauthenticatedException ex) {
  //    ex.printStackTrace();
  //    log.error(ex.getMessage());
  //    return ResponseBo.error("NotLogin");
  //  }

  @ExceptionHandler(value = Exception.class)
  public Object handleException(HttpServletRequest request, Exception ex) {
    log.error(ex.getMessage(), ex);
    return ResponseBo.error("系统异常");
  }
}
