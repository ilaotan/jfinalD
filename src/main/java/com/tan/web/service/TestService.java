package com.tan.web.service;

import java.sql.SQLException;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.activerecord.tx.TxConfig;
import com.tan.web.model.Test;

public class TestService {
	
	@Before(Tx.class)
	@TxConfig("db2")
	public void testInsert(String uname) throws SQLException{
		
		Test.dao.testInsert(uname);
		
//		new Test().set("name", uname).save();
//		new Test().use("main").set("name", uname).save();
		
		
//		Db.use("main2").update("insert into test(name) values(?)",uname);
		throw new SQLException("出错啦...");
	}
	
}
