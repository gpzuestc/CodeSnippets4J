{{#users}}
	<div class="usr usr1">
		<div class="avt">
	<a href="/m/profile.do?u={{id}}"><i class="img"><img src="{{s_icon}}" alt=""></i>
</a>
	{{#verified}}<a><i class="i iO iO1"></i></a>{{/verified}}
</div>
		{{#relation.follow}}
			{{#relation.followed}}
				<a href="javascript:;" data-userid="{{id}}" data-follow="true" data-followed="true" data-action="cancelFollow" class="btn btnC btnC2 btnAdd btnAdded btnEacho"><i class="i iS iS14"></i>互关注</a>
			{{/relation.followed}}
			{{^relation.followed}}
				<a href="javascript:;" data-userid="{{id}}" data-follow="true" data-followed="false" data-action="cancelFollow" class="btn btnC btnC2 btnAdd btnAdded"><i class="i iS iS14"></i>已关注</a>
			{{/relation.followed}}
		{{/relation.follow}}
		{{^relation.follow}}
			{{^relation.myself}}
				<a href="javascript:;" data-userid="{{id}}" data-follow="false" data-myself="false" {{#relation.followed}}data-followed="true"{{/relation.followed}} {{^relation.followed}}data-followed="false"{{/relation.followed}} data-action="addFollow" class="btn btnC btnC2 btnAdd"><i class="i iS iS14"></i>关注</a>
			{{/relation.myself}}
		{{/relation.follow}}
	<a class="nm"  href="/m/profile.do?u={{id}}">{{name}}</a>
</div>
{{/users}}