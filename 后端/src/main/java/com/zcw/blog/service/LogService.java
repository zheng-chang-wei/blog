package com.zcw.blog.service;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zcw.blog.model.SysLog;

public interface LogService {

	List<SysLog> findAllLogs(SysLog log);

	void deleteLogs(String logIds);

	@Async("asyncServiceExecutor")
	void saveLog(ProceedingJoinPoint point, SysLog log) throws JsonProcessingException;

	void deleteLogsByTime(Date time);

}
