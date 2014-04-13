<div class="twi" data-selector="item">
	<div class="cnt">
		<div class="ugc">
			<a href="/m/profile.do?u={{user.id}}" class="nm auth">{{usrUsername}}</a>{{#user.verified}}<i class="i iO iO1"></i>{{/user.verified}}{{{text}}}
		</div>
	</div>
	<div class="opt">
		{{#showDelButton}}
		<a href="javascript:;" class="btn btnB delete" data-replyId='{{id}}' data-twid='{{msgId}}' data-action="delReply"><i class="i iT iT6"></i>删除</a>
	<!--	<span class="vhr">|</span> -->
		{{/showDelButton}}
		<a href="/m/comment.do?i={{msgId}}&type=twitter" data-actionType="back_reply" data-twid="{{msgId}}" data-toReplyId="{{id}}" data-direction="up" data-ReplyUser="{{usrUsername}}" class="btn btnB"><i class="i iT iT3"></i>回复</a>
	</div>
	<div class="info">
		<b class="time">{{createdOn}}</b>
	</div>
</div>