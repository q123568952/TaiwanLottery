package com.morgan.TaiwanLottery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.morgan.TaiwanLottery.dao.Lottery539Repository;
import com.morgan.TaiwanLottery.model.Lottery539;
import com.morgan.TaiwanLottery.service.Lottery539Service;

@Service
public class Lottery539ServiceImpl implements Lottery539Service {

	@Autowired
	private Lottery539Repository lottery539Repository;

	@Override
	public List<Lottery539> findAll(Integer page, Integer uploadamount) {
		Page<Lottery539> pageLottery539 =  lottery539Repository.findAll(PageRequest.of(page, uploadamount,Sort.by("lotterytime").descending()));
		return pageLottery539.getContent();
	}

	

	

}
