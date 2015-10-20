package com.tan.web.model;

import java.sql.SQLException;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@TableBind(tableName = "test",configName = "db2")
public class TestModel extends Model<TestModel> {
	
	public static final TestModel dao = new TestModel();
	
	public TestModel findByUsername(String username){
		
		return super.findFirst("select * from test where name=?",username);
	}
	
	public void testInsert(String uname) throws SQLException{
		//INSERT INTO `test` VALUES (1, '123');
//		Db.use("db1").update("");
		
//		Db.use("db2").update("insert into test(name) values(?)",uname);
		
		dao.set("name",uname).save();
		
//		throw new SQLException("出错啦...");
		
	}
	
}
