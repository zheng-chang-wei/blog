package com.zcw.blog.config.securityhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.model.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest httpServletRequest,
      HttpServletResponse resp,
      Authentication authentication)
      throws IOException, ServletException {
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    SysUser user = (SysUser) authentication.getPrincipal();
    user.setPassword(null);
    ResponseBo ok = ResponseBo.ok(user);
    String s = new ObjectMapper().writeValueAsString(ok);
    out.write(s);
    out.flush();
    out.close();
  }
}
