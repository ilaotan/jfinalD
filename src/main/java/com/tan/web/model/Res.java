package com.tan.web.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
@TableBind(tableName = "system_res")
public class Res extends Model<Res>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static Res dao = new Res();
	
	public List<String> getResUrl(String roleName){
		
		String sql = "select res.url from system_role_res rr " +
				"INNER JOIN system_role r on r.id = rr.role_id and r.name=? " +
				"INNER JOIN system_res res on res.id = rr.res_id";
		List<Res> resList = find(sql,roleName);
		List<String> list = new ArrayList<String>();
		for(Res res : resList){
			list.add(res.getStr("url"));
		}
		return list;
	}
}
