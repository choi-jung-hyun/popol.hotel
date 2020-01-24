package com.web.test.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@RequestMapping("/main.do")
	public String home() {
		return "/main/main";
	}
	
	@RequestMapping("/about.do")
	public String about() {
		return "/main/about";
	}
	
	@RequestMapping("/places.do")
	public String places() {
		return "/main/places";
	}
	
	@RequestMapping("/hotels.do")
	public String hotel() {
		return "/main/hotels";
	}
	
	@RequestMapping("/blog.do")
	public String blog() {
		return "/main/blog";
	}
	
	@RequestMapping("/contact.do")
	public String contact() {
		return "/main/contact";
	}
}
