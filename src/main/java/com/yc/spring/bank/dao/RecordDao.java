package com.yc.spring.bank.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yc.spring.bank.bean.Record;
import com.yc.spring.common.dao.BaseDao;

@Repository
public class RecordDao extends BaseDao<Record> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Record e) {
		jdbcTemplate.update(
				"insert into oprecord values(null,?,?,?)",
				e.getAccountId(),e.getMoney(),
				new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public void update(Record e) {
		// TODO 自动生成的方法存根
		super.update(e);
	}

	@Override
	public void delete(Record e) {
		// TODO 自动生成的方法存根
		super.delete(e);
	}

	@Override
	public Record selectById(Object id) {
		// TODO 自动生成的方法存根
		return super.selectById(id);
	}

	@Override
	public List<Record> selectByObject(Record e) {
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
