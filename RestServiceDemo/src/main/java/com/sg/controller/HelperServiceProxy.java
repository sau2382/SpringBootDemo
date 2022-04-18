package com.sg.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sg.model.Helper;


@FeignClient(name="helper-service", url="${demoservice-host:http://localhost}:7002")
//@FeignClient(name="currency-exchange")
public interface  HelperServiceProxy {
	
	@GetMapping("/helperservice/help/{input}")
	public Helper callHelperService(
			@PathVariable String input);

}
