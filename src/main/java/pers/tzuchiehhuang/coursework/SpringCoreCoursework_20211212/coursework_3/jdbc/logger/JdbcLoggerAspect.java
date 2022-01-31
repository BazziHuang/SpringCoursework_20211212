package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@Component
@Aspect
@Order(1)
public class JdbcLoggerAspect {

	@Autowired
	private JsonDB jsonDB;

	@Pointcut(value = "execution(* pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.template.EmpDaoImpl.queryAll(..))")
	public void pt1() {
	}

	@Before(value = "pt1()")
	public void before(JoinPoint joinPoint) throws IOException {
		//Get data from log.json
		List<MethodLog> log= jsonDB.getMethodLog();
		//Record new log
		log.add(new MethodLog(joinPoint.getSignature().getName(), new Date()));
		//write new log into log.json
		jsonDB.writeLogJson(log);
	}

}
