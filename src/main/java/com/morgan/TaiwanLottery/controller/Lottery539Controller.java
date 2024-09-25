package com.morgan.TaiwanLottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.service.Lottery539Service;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class Lottery539Controller {
	
	@Autowired
	private  Lottery539Service lottery539Service;
	
	@GetMapping("/test/{page}/{uploadamount}")
	@CrossOrigin
	public String TEST(@PathVariable Integer page, @PathVariable Integer uploadamount
			, HttpServletRequest req) {
		System.out.println(page);
		return page+":"+uploadamount;
	}
	@GetMapping("/findAll/{page}/{uploadamount}")
	@CrossOrigin
	public List<Lottery539> findAll(@PathVariable Integer page, @PathVariable Integer uploadamount,HttpServletRequest req) {
		
		return lottery539Service.findAll(page,uploadamount);
	}

}
