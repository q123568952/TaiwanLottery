package com.morgan.TaiwanLottery.utils;

import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.morgan.TaiwanLottery.dao.Lottery539Repository;
import com.morgan.TaiwanLottery.model.Lottery539;

@Component
public class crawlertool {	
	
	@Autowired
	private static Lottery539Repository lottery539Repository;
	
	
	//https://api.taiwanlottery.com/TLCAPIWeB/Lottery/Daily539Result?period&month=${startyear}-${startmonth}&pageSize=31`;
	public static String bsURL = "https://api.taiwanlottery.com/TLCAPIWeB/Lottery/Daily539Result"; 
	
	//取得資料
	public static String get539records (int year ,int month){
		
		
		String url = bsURL+"?period&month="+ year+"-"+(month<10?"0"+month:month)+"&pageSize=31";		
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
			JSONArray drawNumberAppear = daily539Res.getJSONObject(i). getJSONArray("drawNumberAppear");
			String alldrawnumber ="";
			for(int j=0 ;j<drawNumberAppear.length();j++) {
				String drawNumber = drawNumberAppear.get(j).toString();
				if(j==drawNumberAppear.length()-1) {
					alldrawnumber+=drawNumber.length()<2?"0"+drawNumber:drawNumber;
				}else {					
					alldrawnumber+=drawNumber.length()<2?"0"+drawNumber+",":drawNumber+",";
				}
			}
			lottery539.setLotterynumbers(alldrawnumber);
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
	public static List<Map<String, List<Integer>>> checkDate(Date dbdate) {		
		int startYear = 2007;
		int startMonth = 1;	
		int startDay = 1;			
		int nowYear= YearMonth.now().getYear();
		int nowMonth = YearMonth.now().getMonthValue();
		int nowDay = MonthDay.now().getDayOfMonth();
		int dbYear=0;
		int dbMonth=0;
		int dbDay=0;
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
		//產出需要處理的年月map	
		List<Map<String, List<Integer>>> allYearMonth = new ArrayList<>();
		for (int i = dbYear; i <= nowYear; i++) {
			Map<String, List<Integer>> targets =  new HashMap<>();
			if(i==dbYear && i!=nowYear) {	
				List<Integer> year = new ArrayList<>();
				year.add(i);
				List<Integer> months = new ArrayList<>();
				for (int j = dbMonth; j <= 12; j++) {
					months.add(j);					
				}
				targets.put("year", year);
				targets.put("month", months);
				
				allYearMonth.add(targets);
			}else if(i==nowYear && dbYear==nowYear) {				
				List<Integer> year = new ArrayList<>();
				year.add(i);
				List<Integer> months = new ArrayList<>();
				for (int j = dbMonth; j <= nowMonth; j++) {
					months.add(j);					
				}
				targets.put("year", year);
				targets.put("month", months);
				allYearMonth.add(targets);
			}else if (i==nowYear && dbYear!=nowYear) {				
				List<Integer> year = new ArrayList<>();
				year.add(i);
				List<Integer> months = new ArrayList<>();
				for (int j = 1; j <= nowMonth; j++) {
					months.add(j);		
				}
				targets.put("year", year);
				targets.put("month", months);
				allYearMonth.add(targets);
			}else {
				List<Integer> year = new ArrayList<>();
				year.add(i);
				List<Integer> months = new ArrayList<>();
				for (int j = 1; j <= 12; j++) {
					months.add(j);		
				}
				targets.put("year", year);
				targets.put("month", months);
				allYearMonth.add(targets);
			}			
		}
		return allYearMonth;
	}
	
		
		
	
}
