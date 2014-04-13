{{>tpl/inc/header}}
<div data-selector="promptPosition" class="fixed"></div>
<div class="content" data-selector="wrapper">
	<div class="mWrap">
{{#showLoading}}
	{{>tpl/inc/loading}}
{{/showLoading}}
{{^showLoading}}
 {{>tpl/inc/message-tab}}
 {{/showLoading}}
{{#mailTpl}}
	{{>tpl/inc/mail-main}}
	<div data-selector="receivedComment-container"></div>
	<div data-selector='timeLine-list-container' id="hometimeline" ></div>
{{/mailTpl}}

{{#rplTpl}}
	<div data-selector="message-container"></div>
	{{>tpl/inc/receivedComment-main}}
	<div data-selector='timeLine-list-container' id="hometimeline" ></div>
{{/rplTpl}}

{{#mentionTpl}}
	<div data-selector="message-container"></div>
	<div data-selector="receivedComment-container"></div>
	{{>tpl/inc/timeline-main}}
{{/mentionTpl}}

{{>tpl/inc/footer}}
	</div>
</div>