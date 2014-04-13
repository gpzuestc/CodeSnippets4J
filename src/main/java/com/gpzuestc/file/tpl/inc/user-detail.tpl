<!--S 档案页用户信息页-->
	<div class="usrStat">
		<div class="avt">
	<a><i class="img"><img src="{{user.b_icon}}" alt=""></i>
</a>
</div>
		<h1>
			<a class="nm">{{user.name}}</a>{{#user.verified}}<a class="btn"><i class="i iO iO1"></i></a>{{/user.verified}}
		</h1>
		<p class="bio"><b class="gender">{{#user.male}}<i class="i iS iS6"></i>{{/user.male}}{{^user.male}}<i class="i iS iS7"></i>{{/user.male}}</b> {{user.location}}</p>
	</div>
	
	<div class="info pf">
		<p class="cnt"><i>简介：</i>{{user.description}}</p>
		<p class="cnt"><i>微博：</i>{{#user.url}}{{user.url}}{{/user.url}}{{^user.url}}http://t.sohu.com/u/{{user.id}}{{/user.url}}</p>
	</div>
	<!--E 档案页用户信息页-->