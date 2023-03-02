package com.smart.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
	@RequestMapping("/welcome")
	public String Welcome()
	{
		return "This is my email api";
	}

}
