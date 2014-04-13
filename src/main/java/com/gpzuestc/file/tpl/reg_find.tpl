	<!--S 顶部导航 navagation-->
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
			{{#regFlag}}
				<div class="grp"><a href="/m/login.do" class="btn btnH"><b>登录</b></a></div>
			{{/regFlag}}
			{{^regFlag}}
				<div class="grp logon"><a href="/m/login.do" class="btn btnH login"><b>登录</b></a><a href="/m/prereg.do" class="btn btnH on reg"><b>注册</b></a></div>
			{{/regFlag}}
		</div>
		<p>{{#regFlag}}注册微博{{/regFlag}}{{^regFlag}}找回密码{{/regFlag}}</p>
	</header>
	<!------------------------------------>
	<!-- S 登录注册框 帮助 -->
	<section class="sso help">
		{{#regFlag}}
		<fieldset>
			<h3>编辑短信</h3>
			<p class="dt">ZC + 密码 <em>（6-16位字母或数字）</em></p>
			<p class="dt">发送至 <b><a href="sms:1069006016">1069 0060 16</a></b></p>
		</fieldset>
		<p class="tip">无须等待确认短信即可直接使用手机号和密码登录微博</p>
		{{/regFlag}}
		{{^regFlag}}
		<fieldset>
			<h3>编辑短信</h3>
			<p class="dt">新密码（6—16位字母或数字）</em></p>
			<p class="dt">发送至 <b><a href="sms:106900601222">1069 0060 1222</a></b></p>
		</fieldset>
		<p class="tip">即可使用新密码</p>		
		{{/regFlag}}
		<div class="info opts">
			{{>tpl/inc/login-footer}}
		</div>
	</section>
	<!-- E 登录注册框 帮助-->