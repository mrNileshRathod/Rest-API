package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping("/")
	public String main() {
		return "home page";
	}
	@RequestMapping("/account")
	public String account() {
		return "account page";
	}
	@RequestMapping("/balance")
	public String balance() {
		return "balance page";
	}
	@RequestMapping("/update")
	public String update() {
		return "update page";
	}
}
