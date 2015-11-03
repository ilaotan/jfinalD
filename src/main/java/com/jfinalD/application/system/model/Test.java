package com.jfinalD.application.system.model;

import java.sql.SQLException;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@TableBind(tableName = "test",configName = "db2")
public class Test extends Model<Test> {
	
	public static final Test dao = new Test();
	
	public Test findByUsername(String username){
		
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
