package com.zcw.blog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcw.blog.model.SysLog;
import com.zcw.blog.service.LogService;

import lombok.extern.slf4j.Slf4j;

/**
 * AOP 记录用户操作日志
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

	@Autowired
	LogService logService;

	@Pointcut("@annotation(com.zcw.blog.annotation.Log)")
	public void logPointcut() {
	}

	@Around("logPointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = null;
		try {
			// 执行方法
			result = joinPoint.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		SysLog sysLog = new SysLog();
		// User user = (User) SecurityUtils.getSubject().getPrincipal();
		// if (user != null) {
		// sysLog.setUsername(user.getUsername());
		// sysLog.setTime(time);
		// HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// String ip = IPUtils.getIpAddr(request);
		// sysLog.setIp(ip);
		// logService.saveLog(joinPoint, sysLog);
		// }
		return result;
	}

	// @After("deleteRedisCachePointcut()")
	// public void deleteRedisCacheAround() throws Throwable {
	// redisCacheService.deleteBypPttern("DataService*");
	// redisCacheService.deleteBypPttern("StatisticsService*");
	// }
}
