package com.morgan.TaiwanLottery;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.morgan.TaiwanLottery.dao.Lottery539Repository;
import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.utils.crawlertool;

@SpringBootTest
class TaiwanLotteryApplicationTests {
	
	@Autowired
	Lottery539Repository lottery539Repository;

	/*@Test
	void contextLoads() throws Exception {
		List<Lottery539> lottery539s=crawlertool.mapToLotteryObj(crawlertool.get539records("2024","05"));
		
		lottery539Repository.saveAll(lottery539s);
	}*/

}
