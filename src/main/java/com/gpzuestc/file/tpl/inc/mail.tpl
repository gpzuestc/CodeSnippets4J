<div class="twi" data-select="item"><!--纯文本-->
		<div class="avt">
	<a href="/m/profile.do?u={{user.id}}"><i class="img"><img src="{{user.m_icon}}" alt=""></i>
</a>
	{{#user.verified}}<a href="/m/profile.do?u={{user.id}}"><i class="i iO iO1"></i></a><!--非认证用户不作输出-->{{/user.verified}}
</div>
		<div class="cnt">
			<div class="ugc"><a class="nm auth" href="/m/profile.do?u={{user.id}}">{{user.name}}</a><!--档案页里b.nm.auth不作输出-->  {{{content}}}</div>
		</div>
		<div class="opt">
	<a href="javascript:;" class="btn btnB delete" data-mailid="{{id}}" data-inboxflag="{{inboxFlag}}" data-action="delMail"><i class="i iT iT6"></i>删除</a>
	{{#inboxFlag}}
		<a href="/m/message.do?u={{user.id}}&type=mail" data-uid="{{user.id}}" data-name="{{user.name}}" data-direction="up" class="btn btnB"><i class="i iT iT3"></i>回复</a>
	{{/inboxFlag}}
</div>
		<div class="info">
	<b class="time">{{createdOn}}</b>
</div>
	</div>