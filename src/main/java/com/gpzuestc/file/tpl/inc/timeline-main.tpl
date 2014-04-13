<div data-selector='timeLine-list-container' id="hometimeline" >
	{{#timeline}}
    <!-- list 控制区域  -->
    <div data-selector="control-bar">

    </div>

    <!-- 顶部刷新区域 -->
    <div data-selector="top" style="position:relative;">
    	{{>tpl/inc/refresh-top}}
    </div>
	{{/timeline}}
    <!--内容显示区域-->
    <section class="twis main{{^wifiVersion}} lite{{/wifiVersion}}" data-selector="item-container">
    	{{>tpl/inc/msglist}}
	</section>
	
    <!-- 底部区域 -->
    {{#showMore}}
    	{{>tpl/inc/more}}
    {{/showMore}}
   
</div>