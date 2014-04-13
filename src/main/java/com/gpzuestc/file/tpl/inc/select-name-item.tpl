{{#data}}
	<li ontouchstart = "this.className+=' on'"  ontouchend="this.className=this.className.replace('on','')">
        <a href="javascript:;" data-selector="item" data-uid="{{uid}}" data-name="{{name}}" data-pinyinName="{{pinyinName}}" data-firstWord="{{firstWord}}" data-pic="{{pic}}">{{#pic}}<i class="img"><img src="{{pic}}" alt=""></i>{{/pic}}{{name}}</a>
    </li>
{{/data}}