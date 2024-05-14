package com.morgan.TaiwanLottery.utils;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.morgan.TaiwanLottery.model.Lottery539;

public class crawlertool {
	//https://api.taiwanlottery.com/TLCAPIWeB/Lottery/Daily539Result?period&month=${startyear}-${startmonth}&pageSize=31`;
	public static String bsURL = "https://api.taiwanlottery.com/TLCAPIWeB/Lottery/Daily539Result"; 
	
	public static String get539records (String year ,String month){
		String url = bsURL+"?period&month="+ year+"-"+month+"&pageSize=31";
		System.out.println(url);
		RestTemplate restTemplate=new RestTemplate();
		String rs =  restTemplate.getForObject(url, String.class);
	    
		
		return rs;
	}
}
