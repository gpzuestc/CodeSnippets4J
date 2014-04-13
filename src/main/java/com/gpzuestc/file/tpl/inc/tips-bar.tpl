	<!--S 新消息提示条 new messages noticing bar-->
	<section class="msgNtc fixed" {{#postFlag}}id="tipsBar"{{/postFlag}}{{^postFlag}}id="twittersBar"{{/postFlag}}><!--noDis-->
	<a href="javascript:;" class="btn" {{#postFlag}}data-action="showTipsRemind"{{/postFlag}}{{^postFlag}}data-action="showTwitterRemind"{{/postFlag}}>{{#postFlag}}{{statusText}}{{/postFlag}}{{^postFlag}}{{remind.tweetNew}}条微博更新{{/postFlag}}</a>
</section>
	<!--E 新消息提示条-->