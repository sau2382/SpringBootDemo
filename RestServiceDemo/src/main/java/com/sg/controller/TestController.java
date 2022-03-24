package com.sg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.model.Name;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j // lombok
public class TestController {
	
	private static final String template = "Hello, %s!";
	
	@GetMapping("/test")
	public Name test(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.info("Rest controller /test");
		return new Name((long)1, String.format(template, name));
		
	}

}
