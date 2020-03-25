package com.zcw.blog.aspect;

import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.model.Article;
import com.zcw.blog.service.ArticleService;
import com.zcw.blog.utils.HttpContextUtils;
import com.zcw.blog.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/** AOP 统计文章访问次数 */
@Aspect
@Component
@Slf4j
public class ArticleAspect {

  @Autowired ArticleService articleService;

  @Pointcut("execution(* com.zcw.blog.controller.ArticleController.getArticleById(int))")
  public void logPointcut() {}

  @AfterReturning(value = "logPointcut()", returning = "obj")
  public void afterReturning(Object obj) throws Throwable {
    HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
    String ip = IPUtils.getIpAddr(request);
    ResponseBo response = (ResponseBo) obj;
    Object msg = response.get("msg");
    Article article = (Article) msg;
    article.setShowCount(article.getShowCount() + 1);
    articleService.editArticle(article);
  }
}
