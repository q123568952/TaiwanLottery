package com.morgan.TaiwanLottery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morgan.TaiwanLottery.model.Lottery539;

@Repository
public interface Lottery539Repository extends JpaRepository<Lottery539, Integer>{

	Lottery539 findFirstByOrderByLotterytimeDesc();
	
	List<Lottery539> findAll();
}
