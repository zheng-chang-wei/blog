package com.zcw.blog.config.securityhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcw.blog.common.model.ResponseBo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e)
      throws IOException, ServletException {
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.write(new ObjectMapper().writeValueAsString(ResponseBo.error("Not Login")));
    out.flush();
    out.close();
  }
}
