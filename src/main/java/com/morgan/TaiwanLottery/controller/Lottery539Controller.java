package com.morgan.TaiwanLottery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class Lottery539Controller {
	
	
	@GetMapping("/test/{page}/{uploadamount}")
	public String TEST(@PathVariable Integer page, @PathVariable Integer uploadamount
			, HttpServletRequest req) {
		System.out.println(page);
		return page+":"+uploadamount;
	}
	

}
