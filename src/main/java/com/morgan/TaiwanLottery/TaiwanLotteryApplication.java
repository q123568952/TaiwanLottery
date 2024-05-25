package com.morgan.TaiwanLottery;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
			List<Lottery539> lottery539s;
			try {				
				Lottery539 lastLottery539 = lottery539Repository.findFirstByOrderByLotterytimeDesc();
				
				
				if(lastLottery539!=null) {
					crawlertool.checkDate(lastLottery539.getLotterytime());
				}else {
					crawlertool.checkDate(null);					
				}	
				//撈資料存資料庫
				//lottery539s = crawlertool.mapToLotteryObj(crawlertool.get539records("2024","04"));
				//lottery539Repository.saveAll(lottery539s);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		 
	 };
	 
	 timer.schedule(task1, 2000,10000);
		
	}

}
