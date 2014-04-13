<div data-selector="expression-container">
   <!--S 顶部导航 navagation-->
	<header class="header">
		<div class="opt opt1">
			<div class="grp"><a href="javascript:;" class="btn" data-sys-action='close'><i class="i iH iH6"></i></a></div>
		</div>
		<p>添加表情</p>
	</header>
	<!------------------------------------>
	<!--S 发表框-->
	<section class="smile post" data-selector="items-container">
        
        {{#data}}
        	<img class="x" src="data:image/png;base64,{{base64}}" alt="{{desc}}" data-selector="item" title="{{desc}}" style="width:32px;height:32px;" />
        {{/data}}
        {{^data}}
        	{{>tpl/inc/loading}}
        {{/data}}
    </section>
	<!--E 发表框-->
</div>
