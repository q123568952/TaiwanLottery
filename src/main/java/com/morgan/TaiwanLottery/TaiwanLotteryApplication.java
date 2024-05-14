package com.morgan.TaiwanLottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.morgan.TaiwanLottery.utils.crawlertool;

@SpringBootApplication
public class TaiwanLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaiwanLotteryApplication.class, args);
		System.out.println(crawlertool.get539records("2024","05")); 
	}

}
