<div data-selector="receivedComment-container">
<!--S 评论列表-->
<section class="twis msgs" data-selector="item-container">
	{{>tpl/inc/receivedComment-list}}
</section>
<!--E 评论列表-->

  <!-- 底部区域 -->
    {{#showMore}}
    	{{>tpl/inc/more}}
    {{/showMore}}
</div>