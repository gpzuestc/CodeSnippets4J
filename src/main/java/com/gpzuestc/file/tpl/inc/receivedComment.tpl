	<div class="twi" data-selector="item" data-twid="{{msgId}}"><!--纯文本-->
		<div class="avt">
	<a href="/m/profile.do?u={{user.id}}"><i class="img"><img src="{{user.m_icon}}" alt=""></i>
</a>
	{{#user.verified}}<a href="/m/profile.do?u={{user.id}}"><i class="i iO iO1"></i></a><!--非认证用户不作输出-->{{/user.verified}}
</div>
		<div class="cnt">
			<div class="ugc"><a class="nm auth" href="/m/profile.do?u={{user.id}}">{{user.name}}</a><!--档案页里b.nm.auth不作输出-->{{{text}}}</div>
		</div>
		<div class="tor">
			<div class="ugc"><a href="/m/comment.do?i={{msgId}}">{{{msgContent}}}</a></div>
		</div>
		<div class="opt">
	{{#showDelButton}}
	<a href="javascript:;" class="btn btnB delete" data-replyId='{{id}}' data-twid='{{msgId}}' data-action="delReply"><i class="i iT iT6"></i>删除</a>
	{{/showDelButton}}
	<a href="/m/message.do?i={{msgId}}&type=twitter" data-actionType="back_reply" data-twid="{{msgId}}" data-toReplyId="{{id}}" data-direction="up" data-ReplyUser="{{usrUsername}}" class="btn btnB"><i class="i iT iT3"></i>回复</a>
</div>
		<div class="info">
	<b class="time">{{createdOn}}</b>
</div>
	</div>