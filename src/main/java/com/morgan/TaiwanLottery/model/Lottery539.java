package com.morgan.TaiwanLottery.model;

import java.util.Date;

import com.morgan.TaiwanLottery.POJO.LotteryObj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lottery539 extends LotteryObj{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lottery539id;
	
	@Column(columnDefinition = "VARCHAR(30)")
	private String lotterynumbers;
	@Column(columnDefinition = "Integer")
	private Integer lotteryperiod;
	@Column(columnDefinition = "DATETIME")
	private Date lotterytime;
	@Column(columnDefinition = "DATETIME")
	private Date createtime;
	
	public Integer getLotteryperiod() {
		return lotteryperiod;
	}
	public void setLotteryperiod(Integer lotteryperiod) {
		this.lotteryperiod = lotteryperiod;
	}
	public Integer getLottery539id() {
		return lottery539id;
	}
	public void setLottery539id(Integer lottery539id) {
		this.lottery539id = lottery539id;
	}
	public String getLotterynumbers() {
		return lotterynumbers;
	}
	public void setLotterynumbers(String lotterynumbers) {
		this.lotterynumbers = lotterynumbers;
	}
	public Date getLotterytime() {
		return lotterytime;
	}
	public void setLotterytime(Date lotterytime) {
		this.lotterytime = lotterytime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
