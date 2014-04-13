<!--S Profile Board-->
<section class="pfBoard">
	<!------------------------------------>
	
	<!--S 档案页用户信息区-->
	<div class="usrStat">
		<div class="avt">
	<a><i class="img"><img src="{{user.b_icon}}" alt=""></i>
</a>
</div>
		<a href="/m/userinfo.do?u={{user.id}}" class="btn info" data-direction="down"><i class="i iS iS5"></i></a>
		<h1>
			<a class="nm">{{user.name}}</a>{{#user.verified}}<a class="btn"><i class="i iO iO1"></i></a>{{/user.verified}}
		</h1>
		<p class="bio" data-selector="info" style="">{{#shortDesc}}{{shortDesc}}<a href="javascript:;" class="btn" data-action="showAll"><i class="i iS iS8"></i></a>{{/shortDesc}}{{^shortDesc}}{{user.description}}{{/shortDesc}}</p><!--展开时添加style="height:auto;"-->
		{{^myprofile}}
			<div class="panel">
				<div class="opt">
					{{#sendMail}}
					<a href="/m/profile.do?u={{user.id}}&type=mail" class="btn" data-uid="{{user.id}}" data-name="{{user.name}}" data-profile="true" data-direction="down" ><i class="i iS iS1"></i></a>
					{{/sendMail}}
					<a href="/m/profile.do?u={{user.id}}&type=twitter" class="btn" data-actiontype="add_twitter" data-default_message="@{{user.name}}│" data-title="对他说" data-direction="down"><i class="i iS iS2"></i></a>
					<a href="/m/profile.do?u={{user.id}}&type=twitter" class="btn" data-actiontype="add_twitter" data-default_message="│@{{user.name}} 的微博不错，推荐大家去看看！" data-title="推荐给粉丝" data-direction="down"><i class="i iS iS3"></i></a>
				</div>
			{{#user.relation.follow}}
				{{#user.relation.followed}}
					<a href="javascript:;" data-userid="{{user.id}}" data-follow="true" data-followed="true" data-action="cancelFollow" class="btn btnC btnC2 btnAdd btnAdded btnEacho"><i class="i iS iS14"></i>互关注</a>
				{{/user.relation.followed}}
				{{^user.relation.followed}}
					<a href="javascript:;" data-userid="{{user.id}}" data-follow="true" data-followed="false" data-action="cancelFollow" class="btn btnC btnC2 btnAdd btnAdded"><i class="i iS iS14"></i>已关注</a>
				{{/user.relation.followed}}
			{{/user.relation.follow}}
			{{^user.relation.follow}}
				<a href="javascript:;" data-userid="{{user.id}}" data-follow="false" {{#user.relation.followed}}data-followed="true"{{/user.relation.followed}} {{^user.relation.followed}}data-followed="false"{{/user.relation.followed}} data-myself="false" data-action="addFollow" class="btn btnC btnC2 btnAdd"><i class="i iS iS14"></i>关注</a>
			{{/user.relation.follow}}
			</div>
		{{/myprofile}}
	</div>
	<!--E 档案页用户信息区 -->
	
	<!------------------------------------>
	
	<!--S 档案页tabs-->
	<menu class="tabs pf">
		<li{{#profileTpl}} class="on"{{/profileTpl}}><a href="javascript:;" data-url="/m/a_profile.do?u={{user.id}}&tab=profile" data-action="toMsglist">{{user.msg_count}}<i>微博</i></a></li>
		<li{{#followersTpl}} class="on"{{/followersTpl}}><a href="javascript:;" data-url="/m/a_profile.do?u={{user.id}}&tab=followers" data-action="toFollowers">{{user.followers_count}}<i>粉丝</i></a></li>
		<li{{#followingTpl}} class="on"{{/followingTpl}}><a href="javascript:;" data-url="/m/a_profile.do?u={{user.id}}&tab=following" data-action="toFollowing">{{user.following_count}}<i>关注</i></a></li>
	</menu>
	<!--E 档案页tabs-->
</section>