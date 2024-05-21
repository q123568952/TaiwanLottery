package com.morgan.TaiwanLottery;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.morgan.TaiwanLottery.POJO.LotteryObj;
import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.utils.crawlertool;

@SpringBootApplication
public class TaiwanLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaiwanLotteryApplication.class, args);
		try {
			List<LotteryObj> lottery539s=crawlertool.mapToLotteryObj(crawlertool.get539records("2024","05"));
			System.out.println(((Lottery539)lottery539s.get(1)).getLotterytime());
			System.out.println(((Lottery539)lottery539s.get(1)).getLotterynumbers());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
