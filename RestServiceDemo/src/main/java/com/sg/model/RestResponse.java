package com.sg.model;

import lombok.Data;

@Data
public class RestResponse {
	
	private final long id ;
	private final String content;
	private final Helper helper;
}
