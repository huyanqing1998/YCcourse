package com.yc.spring.bank;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.yc.spring.bank.action.BankAction;
import com.yc.spring.bank.bean.Account;
import com.yc.spring.bank.biz.AccountBiz;
import com.yc.spring.bank.dao.AccountDao;

@RunWith(SpringRunner.class)
@ContextConfiguration("/bank-beans.xml")
public class BankTest {
	
	@Autowired
	private BankAction bankAction;
	
	@Test
	public void test1() {
		bankAction.deposit(new Account());
		System.out.println("*************************************************");
		bankAction.withdraw(new Account());
		System.out.println("*************************************************");
		bankAction.details(1);
		System.out.println("*************************************************");
		bankAction.details1(1);
		System.out.println("*************************************************");
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test2() {
		Date now = jdbcTemplate.queryForObject("select now()", Date.class);
		System.out.println(now);
	}
	
	@Autowired
	private AccountBiz aBiz;
	
	@Test
	public void test3() {
		Account account = new Account();
		account.setId(1);
		account.setMoney(500d);
		aBiz.deposit(account);
	}

}
