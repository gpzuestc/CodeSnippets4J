<header class="header">
	<div class="opt opt1">
		{{#back}}
			<div class="grp"><a href="javascript:void(0);" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" data-sys-action="back"  data-direction="right" class="btn"><i class="i iH iH4"></i></a></div>
		{{/back}}
		{{#login}}
			<div class="grp"><a href="javascript:void(0);" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" data-action="showMenu" class="btn"><i class="i iH iH7"></i></a></div>
		{{/login}}
		{{^login}}
			{{^back}}
				{{^homeFlag}}
					<div class="grp"><a href="/m/home.do" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn" data-direction="right"><i class="i iH iH2"></i></a></div>
				{{/homeFlag}}
			{{/back}}
		{{/login}}
	</div>
	<div class="opt opt2">
		{{#login}}
			<div class="grp msgCt"><a href="javascript:;" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" data-action="showRemind" class="btn btnH"><b data-select="newRemind">{{#remindNumber}}remindNumber{{/remindNumber}}{{^remindNumber}}0{{/remindNumber}}</b></a></div>
		{{/login}}
		{{^login}}
			<div class="grp logon"><a href="/m/login.do" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn btnH login"><b>登录</b></a><a href="/m/prereg.do" class="btn btnH on reg"><b>注册</b></a></div>
		{{/login}}
	</div>
	{{#timeline}}
		<p><i class="msgCt"><a href="javascript:;" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" data-action="showTwitterRemind" class="btn btnH on"><em>首页</em><b data-select="twitterRemindNum" class="noDis">0</b></a></i></p>
	{{/timeline}}
	
	{{#commentFlag}}
		<p>微博正文</p>
	{{/commentFlag}}
	
	{{#profile}}
		<p>{{shortName}}</p>
	{{/profile}}
	
	{{#homeFlag}}
		<p>广场</p>
	{{/homeFlag}}
	
	{{#channelFlag}}
		<h3>{{channelTitle}}</h3>
	{{/channelFlag}}
	
	{{#messageFlag}}
		<p>消息</p>
	{{/messageFlag}}
	
	{{#repliesFlag}}
		<p>评论</p>
	{{/repliesFlag}}
</header>

<!--S 主菜单导航-->
<menu class="main fixed noDis" data-selector="menu">
	<li><a href="{{#timeline}}javascript:void(0);{{/timeline}}{{^timeline}}/m/fridoc.do{{/timeline}}" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn{{#timeline}} on{{/timeline}}"><i class="i iN iN1"></i><b>首页</b></a></li>
	<li><a href="{{locationUrl}}&type=twitter" data-actionType="add_twitter" data-direction="up" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn"><i class="i iN iN2"></i><b>发微博</b></a></li>
	<li><a href="{{#homeFlag}}javascript:void(0);{{/homeFlag}}{{^homeFlag}}/m/home.do{{/homeFlag}}" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn{{#homeFlag}} on{{/homeFlag}}"><i class="i iN iN3"></i><b>广场</b></a></li>
	<li><a href="{{#profile}}{{#myprofile}}javascript:void(0);{{/myprofile}}{{^myprofile}}/m/profile.do{{/myprofile}}{{/profile}}{{^profile}}/m/profile.do{{/profile}}" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn{{#profile}}{{#myprofile}} on{{/myprofile}}{{/profile}}"><i class="i iN iN4"></i><b>档案</b></a></li>
	{{#wifiVersion}}
		<li><a href="javascript:void(0);" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" data-action="toLess" class="btn"><i class="i iN iN7"></i><b>3G版</b></a></li>
	{{/wifiVersion}}
	{{^wifiVersion}}
		<li><a href="javascript:void(0);" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" data-action="toWifi" class="btn"><i class="i iN iN10"></i><b>Wifi版</b></a></li>
	{{/wifiVersion}}
	<li><a href="{{locationUrl}}&type=feedback" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn"><i class="i iN iN9"></i><b>意见反馈</b></a></li>
	<li><a href="/m/logout.do"  target="_self" ontouchstart="javascript:kola('t5touch.hover.Hover', function(Hover) {Hover.bind(this);}, {scope:this})" class="btn"><i class="i iN iN8"></i><b>注销</b></a></li>
	<li><b class="btn"><i class="i iN"></i><b>&nbsp;</b></b></li>
</menu>

<!--E 主菜单导航-->

<!--S 顶部导航 navagation-->

    <!--E 顶部导航--><!------------------------------------>
