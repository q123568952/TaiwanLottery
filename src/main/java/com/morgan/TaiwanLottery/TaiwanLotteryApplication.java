package com.morgan.TaiwanLottery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.morgan.TaiwanLottery.dao.Lottery539Repository;
import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.utils.crawlertool;

import jakarta.annotation.PostConstruct;

@SpringBootApplication

public class TaiwanLotteryApplication {
	
    private static Lottery539Repository lottery539Repository;
	
	@Autowired
	private Lottery539Repository autolLottery539Repository;
	
	@PostConstruct
	private void init() {
		TaiwanLotteryApplication.lottery539Repository=this.autolLottery539Repository;
	}
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(TaiwanLotteryApplication.class, args);		
	 Timer timer =new Timer();
	 TimerTask task1 = new TimerTask() {
		@Override
		public void run() {	
			
			try {				
				Lottery539 lastLottery539 = lottery539Repository.findFirstByOrderByLotterytimeDesc();				
				List<Map<String, List<Integer>>> allYearMonth;
				if(lastLottery539!=null) {
					allYearMonth = crawlertool.checkDate(lastLottery539.getLotterytime());
				}else {
					allYearMonth=crawlertool.checkDate(null);					
				}	
				//撈資料存資料庫
				System.out.println(allYearMonth);
				for (Map<String, List<Integer>> ele : allYearMonth) {
					List<Integer> year = ele.get("year");
					List<Integer> months = ele.get("month");					
					for (Integer month : months) {						
						try {	
							if(lastLottery539!=null) {
								Date dbdate = lastLottery539.getLotterytime();
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(dbdate);
								if(year.get(0)==calendar.get(calendar.YEAR)&&month==calendar.get(calendar.MONTH)+1) {
									List<Lottery539> lottery539s = crawlertool.mapToLotteryObj(crawlertool.get539records(year.get(0),month));
									List<Lottery539> filterlottery539s = new  ArrayList<>();
									for (Lottery539 lt539 : lottery539s) {
										if(lt539.getLotterytime().getDate()>dbdate.getDate()) {
											filterlottery539s.add(lt539);
										}
									}									
									lottery539Repository.saveAll(filterlottery539s);
								}else {
									
									List<Lottery539> lottery539s = crawlertool.mapToLotteryObj(crawlertool.get539records(year.get(0),month));
									
									lottery539Repository.saveAll(lottery539s);
								}
							}else {								
								List<Lottery539> lottery539s = crawlertool.mapToLotteryObj(crawlertool.get539records(year.get(0),month));
								lottery539Repository.saveAll(lottery539s);
							}	
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		 
	 };
	 
	 timer.schedule(task1, 2000,86400000);
		
	}

}
