<div data-selector="message-container">
<section class="opts msg">
	<div class="opt opt2">
		<a href="/m/message.do?type=mail" data-direction="up" class="btn btnC btnC2">发私信</a>
	</div>
	<menu class="opt tabs2">
		<li class="{{#inboxFlag}}on{{/inboxFlag}}"><a href="javascript:;" data-url="/m/a_message.do?tab=inbox" data-action="toInbox" class="btn">收件箱</a></li>
		<li class=" {{^inboxFlag}}on{{/inboxFlag}}"><a href="javascript:;" data-url="/m/a_outbox.do" data-action="toOutbox" class="btn">发件箱</a></li>
	</menu>
</section>
<!--S 私信列表，收/发信箱-->
<section class="twis msgs" data-selector="item-container">
	{{>tpl/inc/mail-list}}
</section>
<!--E 私信列表，收/发信箱-->

  <!-- 底部区域 -->
    {{#showMore}}
    	{{>tpl/inc/more}}
    {{/showMore}}
</div>