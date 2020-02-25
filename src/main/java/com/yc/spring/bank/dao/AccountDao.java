package com.yc.spring.bank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yc.spring.bank.bean.Account;
import com.yc.spring.common.dao.BaseDao;

@Repository
public class AccountDao extends BaseDao<Account> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Account e) {
		// TODO 自动生成的方法存根
		super.insert(e);
	}

	@Override
	public void update(Account e) {
		jdbcTemplate.update(
				"update account set balance = balance + ? where accountid = ?",
				e.getMoney(),e.getId());
	}

	@Override
	public void delete(Account e) {
		// TODO 自动生成的方法存根
		super.delete(e);
	}

	@Override
	public Account selectById(Object id) {
		// TODO 自动生成的方法存根
		return super.selectById(id);
	}

	@Override
	public List<Account> selectByObject(Account e) {
		// TODO 自动生成的方法存根
		return super.selectByObject(e);
	}

	@Override
	public int hashCode() {
		// TODO 自动生成的方法存根
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO 自动生成的方法存根
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO 自动生成的方法存根
		super.finalize();
	}

}
