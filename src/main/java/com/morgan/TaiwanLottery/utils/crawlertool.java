package com.morgan.TaiwanLottery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.morgan.TaiwanLottery.POJO.LotteryObj;
import com.morgan.TaiwanLottery.model.Lottery539;

public class crawlertool {
	//https://api.taiwanlottery.com/TLCAPIWeB/Lottery/Daily539Result?period&month=${startyear}-${startmonth}&pageSize=31`;
	public static String bsURL = "https://api.taiwanlottery.com/TLCAPIWeB/Lottery/Daily539Result"; 
	
	//取得資料
	public static String get539records (String year ,String month){
		String url = bsURL+"?period&month="+ year+"-"+month+"&pageSize=31";		
		RestTemplate restTemplate=new RestTemplate();
		String rs =  restTemplate.getForObject(url, String.class);
	    
		
		return rs;
	}
	//將資料處理成Lottery物件
	public static List<LotteryObj> mapToLotteryObj(String jsonResult) throws Exception {
		
		JSONObject jObj =  new JSONObject(jsonResult);
		//資料
		JSONArray daily539Res  = jObj.getJSONObject("content").getJSONArray("daily539Res");
		List<LotteryObj> lottery539s = new ArrayList<LotteryObj>();
		for (int i = 0; i < daily539Res.length(); i++) {
			Lottery539 lottery539 =new Lottery539();
			Integer period = daily539Res.getJSONObject(i).getInt("period");
			String lotteryDate = daily539Res.getJSONObject(i).getString("lotteryDate");
			String drawNumberAppear = daily539Res.getJSONObject(i). getJSONArray("drawNumberAppear").toString();			
			lottery539.setLotterynumbers(drawNumberAppear);
			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(lotteryDate);			
			lottery539.setLotterytime(date);
			lottery539.setLotteryperiod(period);
			lottery539s.add(lottery539);
		}
		
		return lottery539s;
	}
	
}
