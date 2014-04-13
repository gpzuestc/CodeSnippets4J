{{>tpl/inc/header}}
<div data-selector="promptPosition" class="fixed"></div>
<div class="content" data-selector="wrapper">
	<div class="mWrap">

<!--S 微博正文 原文-->
<section class="twis final">
	{{#showLoading}}
		{{>tpl/inc/loading}}
	{{/showLoading}}
	{{^showLoading}}
		{{>tpl/inc/final-msg}}
	{{/showLoading}}
</section>
<!--E 微博正文 原文-->
<!------------------------------------>
{{>tpl/inc/final-reply-section}}

	</div>
</div>
{{>tpl/inc/comment-bar}}