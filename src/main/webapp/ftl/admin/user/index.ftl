<#include "/ftl/admin/common/_layout.ftl"/>
<@layout page_tab="system" page_tab_aim="system">


<form class="form-inline"  action="${ctx}/admin/user" id ="fenyeForm" method="post">
	<div class="control-group">
		<div class="controls">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNumber!1}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!10}"/>
		</div>
	</div>
</form>


<div class="am-cf am-padding">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户管理</strong> / <small>Table</small></div>
</div>

<div class="am-g">
	<div class="am-u-sm-12 am-u-md-6">
		<div class="am-btn-toolbar">
			<div class="am-btn-group am-btn-group-xs">
				<button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
			</div>
		</div>
	</div>
	<div class="am-u-sm-12 am-u-md-3">
		<div class="am-form-group">
			<select data-am-selected="{btnSize: 'sm'}">
				<option value="option1">所有类别</option>
				<option value="option2">IT业界</option>
				<option value="option3">数码产品</option>
				<option value="option3">笔记本电脑</option>
				<option value="option3">平板电脑</option>
				<option value="option3">只能手机</option>
				<option value="option3">超极本</option>
			</select>
		</div>
	</div>
	<div class="am-u-sm-12 am-u-md-3">
		<div class="am-input-group am-input-group-sm">
			<input type="text" class="am-form-field">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
		</div>
	</div>
</div>

<div class="am-g">
	<div class="am-u-sm-12">
		<form class="am-form">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-id">ID</th>
					<th class="table-title">用户名</th>
					<th class="table-type">用户类型</th>
					<th class="table-author am-hide-sm-only">类型描述</th>
					<th class="table-date am-hide-sm-only">账号状态</th>
					<th class="table-set">操作</th>
				</tr>
				</thead>
				<tbody>
				<#list page.list?if_exists as row>
					<tr data="id:${row.id};">
						<td>${row.id}</td>
						<td>${row.username}</td>
						<td>${row.name}</td>
						<td>${row.description}</td>
						<td>${row.is_locked?string("失效","正常")}</td>
						<td>
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
									<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
								</div>
							</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
			<div class="am-cf">
				共 15 条记录
				<div class="am-fr">
					<ul class="am-pagination">
						<li class="am-disabled"><a href="#">«</a></li>
						<li class="am-active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">»</a></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		alert("111");
	});

</script>

</@layout>