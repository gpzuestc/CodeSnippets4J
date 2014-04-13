	<header class="header">
		<div class="opt opt1">
			{{#back}}
				<div class="grp"><a href="javascript:void(0);" data-sys-action="back" class="btn"><i class="i iH iH4"></i></a></div>
			{{/back}}
			{{^back}}
				<div class="grp"><a href="/m/home.do" class="btn" data-direction="right"><i class="i iH iH2"></i></a></div>
			{{/back}}
		</div>
		<div class="opt opt2">
			<div class="grp"><a href="/m/prereg.do" class="btn btnH on"><b>注册</b></a></div>
		</div>
		<!-- <p><i class="tLogo"></i></p> -->
		<p>登录</p>
	</header>
<!-- S 登录注册框 -->
	<div data-selector="promptPosition" class="fixed"></div>
	<section class="sso">
		<form method="post" action="/m/login.do" target="_self" data-selector="loginForm">
		<div class="frm">
			<i class="frmT">帐号</i>
			<input class="t" type="text" data-selector="name" placeholder="手机号/搜狐通行证">
		</div>
		
		<div class="frm">
			<i class="frmT">密码</i>
			<input class="t" type="password" data-selector="password">
		</div>
		<div class="frm help">
			<div class="opts">
				<div class="opt opt1"><a href="javascript:;" class="btn auto on" data-selector="autoLogin"><i class="i iO iO9"></i>下次自动登录</a></div>
				<div class="opt opt2"><a href="/m/findpass.do" class="btn find">找回密码</a></div>
			</div>
		</div>
		
		<div class="frm">
			<!--<a href="#" class="btn btnC btnC3 login">登录</a>-->
			<input type="submit" value="登录" class="btn btnC btnC3 login"/>
		</div>
		<div class="frm">
			<a href="/m/prereg.do" class="btn btnC btnC2 reg">立即注册微博</a>
		</div>
		<div class="frm info">
			<div class="opts">
				{{>tpl/inc/login-footer}}
			</div>
		</div>
		</form>
	</section>
	<!-- E 登录注册框 -->