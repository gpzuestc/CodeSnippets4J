
<!--S 顶部导航 navagation-->
{{>tpl/inc/post-header}}
<div data-selector="promptPosition" class="fixed"></div>
	<div data-selector="reply-dialog-container">
	<!------------------------------------>
	{{#sendMail}}
	<section data-select="addresseeContainer" class="ttl post {{#hasDefaultName}}on{{/hasDefaultName}}">
		<em>收件人：</em>
		<a {{^isProfile}}href="{{locationUrl}}&type=suggest&mail=true"{{/isProfile}}{{#isProfile}}href="javascript:;"{{/isProfile}} class="input" data-select="addressee" data-direction="up"></a>
		{{^isProfile}}<a href="{{locationUrl}}&type=suggest&mail=true" class="btn" data-direction="up">更改</a>{{/isProfile}}
	</section>
	{{/sendMail}}
	<!--S 发表框-->
	<section class="tarea post">		
		<textarea class="t" style="height:{{#sendMail}}50{{/sendMail}}{{^sendMail}}50{{/sendMail}}px;"  data-select='simpleEdit'  required="{{isRequireWord}}" {{^feedback}}placeholder="点击这里输入内容"{{/feedback}} {{#feedback}}placeholder="欢迎您留下宝贵的意见和建议"{{/feedback}}></textarea>		
	</section>
	{{^feedback}}
	<section class="opts post">
		{{^sendMail}}
			<div class="opt opt1">

				<a href="{{locationUrl}}&type=expression" class="btn {{^isShowExpress}}noDis{{/isShowExpress}}" data-direction="up"><i class="i iO iO5"></i></a>
				<a href="javascript:;" class="btn {{^isShowTopic}}noDis{{/isShowTopic}}" data-action="topic"><i class="i iO iO6"></i></a>
				<a href="{{locationUrl}}&type=suggest" class="btn {{^isShowAt}}noDis{{/isShowAt}}" data-direction="up"><i class="i iO iO7"></i></a>
				<a href="javascript:;" class="btn {{^isShowGeo}}noDis{{/isShowGeo}}" data-action="geoLocation" ><i class="i iO iO8"></i></a>
			</div>
		{{/sendMail}}
		<div class="opt opt2">
			{{#limit}}
				<b class="num">还可输入<em data-select="limitNum">140</em>字</b>
			{{/limit}}
			{{^limit}}
				<a href="javascript:;" class="btn {{^isShowForward}}noDis{{/isShowForward}}" data-select='isForward'><i class="i iO iO9"></i>同时转发</a>
			{{/limit}}
		</div>
	</section>
	{{/feedback}}
	<!--E 发表框-->
</div>