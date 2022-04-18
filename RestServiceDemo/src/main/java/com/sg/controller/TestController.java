package com.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.model.Helper;
import com.sg.model.RestResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j // lombok
@RefreshScope  //refreshes config via actuator/refresh endpoint
public class TestController {
	
	private static final String template = "Hello, %s from %s!  --  Host:%s  Port:%s";
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private HelperServiceProxy proxy;
	
	@Value("${demoservice.welcometext}")
	private String appName;
	
	@GetMapping("/test")
	public RestResponse test(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.info("Rest controller /test");
		log.info("Rest controller ENV host: " + environment.getProperty("demoservice-host"));
		log.info("Rest controller ENV welcometext: " + environment.getProperty("WELCOMETEXT"));

        String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		Helper helper = proxy.callHelperService(name);
		
		return new RestResponse((long)1, String.format(template, name, appName, host, port),helper);
		
	}
	
	
	
	
	
}
