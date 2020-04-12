package com.zcw.blog.config.securityhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcw.blog.common.model.ResponseBo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(
      HttpServletRequest httpServletRequest,
      HttpServletResponse resp,
      Authentication authentication)
      throws IOException, ServletException {
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.write(new ObjectMapper().writeValueAsString(ResponseBo.ok("注销成功!")));
    out.flush();
    out.close();
  }
}
