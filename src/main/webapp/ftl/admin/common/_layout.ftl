<#macro layout page_tab="" page_tab_aim="">

	<!doctype html>
	<html class="no-js">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Amaze UI Admin index Examples</title>
		<meta name="description" content="这是一个 index 页面">
		<meta name="keywords" content="index">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="apple-mobile-web-app-title" content="Amaze UI" />
		<link rel="stylesheet" href="${ctx!}/assets/amaze/css/amazeui.min.css"/>
		<link rel="stylesheet" href="${ctx!}/assets/amaze/css/admin.css">

		<!--[if lt IE 9]>
		<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
		<script src="${ctx!}/assets/amaze/js/amazeui.ie8polyfill.min.js"></script>
		<![endif]-->

		<!--[if (gte IE 9)|!(IE)]><!-->
		<script src="${ctx!}/assets/amaze/js/jquery.min.js"></script>
		<!--<![endif]-->
		<script src="${ctx!}/assets/amaze/js/amazeui.min.js"></script>
		<script src="${ctx!}/assets/amaze/js/app.js"></script>
	</head>
	<body>
	<!--[if lte IE 9]>
	<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
		以获得更好的体验！</p>
	<![endif]-->

	<header class="am-topbar admin-header">
		<div class="am-topbar-brand">
			<strong>Amaze UI</strong> <small>后台管理模板</small>
		</div>

		<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

			<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
				<li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
				<li class="am-dropdown" data-am-dropdown>
					<a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
						<span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
					</a>
					<ul class="am-dropdown-content">
						<li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
						<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
						<li><a href="${ctx!}/admin/login/logout"><span class="am-icon-power-off"></span> 退出</a></li>
					</ul>
				</li>
				<li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
			</ul>
		</div>
	</header>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<ul class="am-list admin-sidebar-list">
					<li class="am-active"><a href="${ctx!}/admin"><span class="am-icon-home"></span> 首页</a></li>
					<li class="admin-parent">
						<a class="am-cf" data-am-collapse="{target: '#collapse-nav-system'}"><span class="am-icon-file"></span> 系统设置 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class='am-list am-collapse admin-sidebar-sub <#if page_tab_aim="system"> am-in </#if> ' id="collapse-nav-system">
							<li><a href="${ctx!}/admin/user"><span class="am-icon-puzzle-piece"></span>用户管理</a></li>
							<li><a href="${ctx!}/admin/menu"><span class="am-icon-puzzle-piece"></span>菜单管理</a></li>
							<li><a href="${ctx!}/admin/role"><span class="am-icon-puzzle-piece"></span>角色管理</a></li>
						</ul>
					</li>
				</ul>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p><span class="am-icon-bookmark"></span> 公告</p>
						<p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
					</div>
				</div>
			</div>
		</div>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
			<#nested>
		</div>
		<!-- content end -->

	</div>

	<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
		<span class="am-icon-btn am-icon-th-list"></span>
	</a>

	<footer>
		<hr>
		<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
	</footer>
	</body>
	</html>


</#macro>