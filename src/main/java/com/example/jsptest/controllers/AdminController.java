package com.example.jsptest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin/")
	public String mainPage() {
		return "main";
	}

}
