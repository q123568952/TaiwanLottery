package com.morgan.TaiwanLottery.utils;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.morgan.TaiwanLottery.model.Lottery539;

@Component
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
	public static List<Lottery539> mapToLotteryObj(String jsonResult) throws Exception {
		
		JSONObject jObj =  new JSONObject(jsonResult);
		//資料
		JSONArray daily539Res  = jObj.getJSONObject("content").getJSONArray("daily539Res");
		List<Lottery539> lottery539s = new ArrayList<Lottery539>();
		for (int i = 0; i < daily539Res.length(); i++) {
			Lottery539 lottery539 =new Lottery539();
			Integer period = daily539Res.getJSONObject(i).getInt("period");
			String lotteryDate = daily539Res.getJSONObject(i).getString("lotteryDate");
			String drawNumberAppear = daily539Res.getJSONObject(i). getJSONArray("drawNumberAppear").toString();			
			lottery539.setLotterynumbers(drawNumberAppear);
			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(lotteryDate);			
			lottery539.setLotterytime(date);
			lottery539.setLotteryperiod(period);
			lottery539.setCreatetime(new Date());
			lottery539s.add(lottery539);
		}
		
		return lottery539s;
	}
	//比較日期資料到現在有多少期別需要補資料
	//發行日期2007/01/01
	public static void checkDate(Date dbdate) {
		Integer startYear = 2007;
		Integer startMonth = 1;	
		Integer startDay = 1;			
		Integer nowYear= YearMonth.now().getYear();
		Integer nowMonth = YearMonth.now().getMonthValue();
		Integer nowDay = MonthDay.now().getDayOfMonth();
		Integer dbYear=0;
		Integer dbMonth=0;
		Integer dbDay=0;
		if(dbdate!=null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dbdate);
			dbYear=calendar.get(calendar.YEAR);
			//0起算
			dbMonth=calendar.get(calendar.MONTH)+1;
			dbDay=calendar.get(calendar.DAY_OF_MONTH);
		}else {
			dbYear=startYear;
			dbMonth=startMonth;
			dbDay=startDay;
		}	
		
		
		
		System.out.println(dbYear);
		System.out.println(dbMonth);
		System.out.println(dbDay);
		System.out.println(nowYear);
		System.out.println(nowMonth);
		System.out.println(nowDay);
	}
		
}
