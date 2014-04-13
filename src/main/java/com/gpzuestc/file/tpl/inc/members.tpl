{{#followersTpl}}
<!--S 粉丝列表-->
<div data-selector="followers-container">
	<section class="usrs usrs1" data-selector="item-container">
		{{>tpl/inc/member}}
	</section>
	{{#showMore}}
		{{>tpl/inc/more}}
	{{/showMore}}
</div>
<!--E 粉丝列表-->
<div data-selector="following-container"></div>
{{/followersTpl}}
{{#followingTpl}}
<div data-selector="followers-container"></div>
<!--S 关注列表-->
<div data-selector="following-container">
	<section class="usrs usrs1" data-selector="item-container">
		{{>tpl/inc/member}}
	</section>
	{{#showMore}}
		{{>tpl/inc/more}}
	{{/showMore}}
</div>
<!--E 关注列表-->
{{/followingTpl}}