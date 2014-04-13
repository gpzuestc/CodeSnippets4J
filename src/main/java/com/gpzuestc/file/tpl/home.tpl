{{>tpl/inc/header}}
<div data-selector="promptPosition" class="fixed"></div>
<div class="content" data-selector="wrapper">
	<div class="mWrap">
<section class="pubNav">
		<section class="cover">
		{{#coverChannels}}
			{{>tpl/inc/cover}}
		{{/coverChannels}}
		</section>
		<nav class="media cnlList">
			{{#commonChannels}}<div class="item cnl" data-href="/m/channel.do?cid={{id}}">
					<i class="ih {{imgStyle}}"><img src="{{imgUrl}}" alt=""></i>
					<b class="nm">{{title}}</b>
				</div>{{/commonChannels}}
		</nav>
	</section>
	</div>
</div>