package com.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sg.model.Helper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j // lombok
@RefreshScope  //refreshes config via actuator/refresh endpoint
public class HelperController {
	
	@Autowired
	private Environment environment;
	
	@Value("${helperservice.appId}")
	private String appId;
	
	@GetMapping("/help/{input}")
	public Helper test( @PathVariable String input) {
		log.info(String.format("Helper Rest controller /help/%s", input));
        String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		return new Helper(appId, input , host, port);
		
	}
	
	
	
	
	
}
