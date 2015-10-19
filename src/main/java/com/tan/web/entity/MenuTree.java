package com.tan.web.entity;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * tree
 */
public class MenuTree {
	private int id;
	private String url;
	private String text;
	private List<MenuTree> children;

	public MenuTree() {
		super();
	}
	public MenuTree(int id, String url, String text, String tableName) {
		super();
		this.id = id;
		this.url = url;
		this.text = text;
		this.children = findChildrenByTableAndCheck(id, tableName);
	}
	private List<MenuTree> findChildrenByTableAndCheck(int id, String tableName){
		List<MenuTree> list = new ArrayList<MenuTree>();
		
		if(StrKit.isBlank(tableName)) tableName = "system_menu";
		List<Record> menus = Db.find("select * from " + tableName + " where menu_parent_id=? order by menu_sn", id);
		for(Record menu : menus){
			list.add(new MenuTree(menu.getInt("id"), menu.getStr("menu_url"), menu.getStr("menu_name"), tableName));
		}
		
		return list;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
}
