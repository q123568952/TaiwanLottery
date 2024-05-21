package com.morgan.TaiwanLottery;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.morgan.TaiwanLottery.dao.Lottery539Repository;
import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.utils.crawlertool;

@SpringBootApplication
public class TaiwanLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaiwanLotteryApplication.class, args);
	 Timer timer =new Timer();
	 TimerTask task1 = new TimerTask() {
		@Override
		public void run() {			
			crawlertool ct =new crawlertool();
			List<Lottery539> lottery539s;
			try {
				lottery539s = crawlertool.mapToLotteryObj(crawlertool.get539records("2024","05"));
				ct.lottery539Insert(lottery539s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		 
	 };
	 
	 timer.schedule(task1, 2000,2000);
		
	}

}
