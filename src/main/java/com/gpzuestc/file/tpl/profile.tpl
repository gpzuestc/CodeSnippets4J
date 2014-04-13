{{>tpl/inc/header}}
<div data-selector="promptPosition" class="fixed"></div>
<div class="content" data-selector="wrapper">
	<div class="mWrap">
{{>tpl/inc/profile-board}}
{{#profileTpl}}
	{{>tpl/inc/timeline-main}}
	<div data-selector="followers-container"></div>
	<div data-selector="following-container"></div>
{{/profileTpl}}

{{^profileTpl}}
  	{{#showLoading}}
		{{>tpl/inc/loading}}
	{{/showLoading}}
	{{^showLoading}}
	<div data-selector='timeLine-list-container' id="hometimeline" ></div>
	{{>tpl/inc/members}}
	{{/showLoading}}
{{/profileTpl}}
	</div>
</div>