package com.morgan.TaiwanLottery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morgan.TaiwanLottery.dao.Lottery539Repository;
import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.service.Lottery539Service;

@Service
public class Lottery539ServiceImpl implements Lottery539Service {

	@Autowired
	private Lottery539Repository lottery539Repository;

	@Override
	public List<Lottery539> findAll() {
		// TODO Auto-generated method stub
		return lottery539Repository.findAll();
	}

	

}
