	<!--S 私信、评论 Board-->
<menu class="tabs msg">
	<li{{#mailTpl}} class="on"{{/mailTpl}}><a href="javascript:;" data-url="/m/a_message.do?tab=inbox" data-action="toMessage">私信</a><section class="msgCt{{^remind.mailNew}} noDis{{/remind.mailNew}}" data-select="remindIcon" data-type="mail"><i class="btn btnH on"><b>{{remind.mailNew}}</b></i></section></li>
	<li{{#rplTpl}} class="on"{{/rplTpl}}><a href="javascript:;" data-url="/m/a_message.do?tab=rpl" data-action="toRpl">评论</a><section class="msgCt{{^remind.replyNew}} noDis{{/remind.replyNew}}" data-select="remindIcon" data-type="reply"><i class="btn btnH on"><b>{{remind.replyNew}}</b></i></section></li>
	<li{{#mentionTpl}} class="on"{{/mentionTpl}}><a href="javascript:;" data-url="/m/a_message.do?tab=mentions" data-action="toMentions">提到我</a><section class="msgCt{{^remind.atNew}} noDis{{/remind.atNew}}" data-select="remindIcon" data-type="at"><i class="btn btnH on"><b>{{remind.atNew}}</b></i></section></li>
</menu>
