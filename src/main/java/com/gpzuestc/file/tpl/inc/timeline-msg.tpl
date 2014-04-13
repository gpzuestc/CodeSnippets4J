{{#media}}
<!--<div class="twi hasMedia"   data-twid="{{id}}">-->
<div class="twi hasMedia" data-twid="{{id}}">
    {{#wifiVersion}}
    <div class="media{{#timelineMedia}} mult mult{{multShowSize}}{{/timelineMedia}}"  data-tid="{{id}}" data-wifi="true" onclick="javascript:var self = this; kola('t5touch.image.ImageEffect', function(ImageEffect) {ImageEffect.expand(self);})">
		{{#timelineMedia}}
			{{>tpl/inc/timeline-mult-img}}
		{{/timelineMedia}}
		{{^timelineMedia}}
			{{>tpl/inc/timeline-img}}
		{{/timelineMedia}}
    </div>
    {{/wifiVersion}}
{{/media}}
{{^media}}
	<div class="twi" data-twid="{{id}}">
{{/media}}
	{{^profile}}
    <div class="avt">
        <a href="{{#login}}/m/profile.do?u={{user.id}}{{/login}}{{^login}}/m/login.do{{/login}}"><i class="img"><img src="{{user.m_icon}}" alt=""></i></a>{{#user.verified}}<a><i class="i iO iO1"></i></a>{{/user.verified}}
	</div>
	{{/profile}}
    <div class="cnt">
        <div class="ugc">
            {{^profile}}<a class="nm auth" href="{{#login}}/m/profile.do?u={{user.id}}{{/login}}{{^login}}/m/login.do{{/login}}">{{user.name}}</a>  {{/profile}}  <b data-content="lessContent">{{{text}}} </b> {{#overflow}}<a href="javascript:void(0)" class="btn btnB" data-twid="{{id}}" data-isExpand="false" onclick="kola('t5touch.twitter.ViewContent', function(ViewContent) {ViewContent.viewAll(this);}, {scope:this})"><i class="i iT iT5"></i></a>{{/overflow}}
        </div>
		{{#retweeted}}
        <div class="twiRoot">
            <div class="twi">
                <div class="cnt">
                    <div class="ugc">
                        <a class="nm" href="{{#login}}/m/profile.do?u={{sourceMsg.user.id}}{{/login}}{{^login}}/m/login.do{{/login}}">@{{sourceMsg.user.name}}</a> <b data-content="lessContent"> {{{sourceMsg.text}}} </b>  {{#sourceMsg.overflow}}<a href="javascript:void(0)" class="btn btnB" data-twid="{{sourceMsg.id}}" data-isExpand="false" onclick="kola('t5touch.twitter.ViewContent', function(ViewContent) {ViewContent.viewAll(this);}, {scope:this})"><i class="i iT iT5"></i></a>{{/sourceMsg.overflow}}
                    </div>
                    {{#wifiVersion}}
						{{#sourceMsg.singleVote}}
						<div class="ap">
							<a class="btn" id="t_vote_{{sourceMsg.id}}" onclick="kola('t5touch.twitter.RendeVote', function(RendeVote) {RendeVote.rende(this,'{{sourceMsg.id}}');}, {scope:this})" href="{{#login}}javascript:void(0);{{/login}}{{^login}}/m/login.do{{/login}}"><i class="i iO iO2"></i></a>
						</div>
						{{/sourceMsg.singleVote}}
					{{/wifiVersion}}
					{{^wifiVersion}}
						{{#sourceMsg.singleMediaVote}}
							<div class="ap"><!--WIFI版不输出-->
								{{>tpl/inc/timeline-media-3g}}
								{{#vote}}
									<a class="btn" id="t_vote_{{sourceMsg.id}}" onclick="kola('t5touch.twitter.RendeVote', function(RendeVote) {RendeVote.rende(this,'{{sourceMsg.id}}');}, {scope:this})" href="{{#login}}javascript:;{{/login}}{{^login}}/m/login.do{{/login}}"><i class="i iO iO2"></i></a>
								{{/vote}}
							</div>
						{{/sourceMsg.singleMediaVote}}
					{{/wifiVersion}}
                </div>
                <div class="opt">
                	<div class="grp">
						<a class="btn btnB" href="{{locationUrl}}&type=twitter" data-actiontype="forward" data-twid="{{sourceMsg.id}}" data-direction="up" >原文转发({{sourceMsg.retweetCount}})</a><a class="btn btnB" href="/m/reply_page.do?i={{sourceMsg.id}}">原文评论({{sourceMsg.repliedCount}})</a>
					</div>
				</div>
            </div>
        </div>
		{{/retweeted}}
		{{#wifiVersion}}
			{{#singleVote}}
			<div class="ap" >
            	<a class="btn" id="t_vote_{{id}}" onclick="kola('t5touch.twitter.RendeVote', function(RendeVote) {RendeVote.rende(this,'{{id}}');}, {scope:this})" href="{{#login}}javascript:void(0);{{/login}}{{^login}}/m/login.do{{/login}}"><i class="i iO iO2"></i></a>
        	</div>
			{{/singleVote}}
		{{/wifiVersion}}
		{{^wifiVersion}}
			{{#singleMediaVote}}
			<div class="ap"><!--WIFI版不输出-->
				{{>tpl/inc/timeline-media-3g}}
				{{#vote}}
					<a class="btn" id="t_vote_{{#retweeted}}{{sourceMsg.id}}{{/retweeted}}{{^retweeted}}{{id}}{{/retweeted}}" onclick="kola('t5touch.twitter.RendeVote', function(RendeVote) {RendeVote.rende(this,'{{#retweeted}}{{sourceMsg.id}}{{/retweeted}}{{^retweeted}}{{id}}{{/retweeted}}');}, {scope:this})" href="{{#login}}javascript:;{{/login}}{{^login}}/m/login.do{{/login}}"><i class="i iO iO2"></i></a>
				{{/vote}}				
			</div>
			{{/singleMediaVote}}			
		{{/wifiVersion}}
    </div>
	
	{{#geo}}
	<div class="locat">
        <b class="place"><i class="i iT iT4"></i>{{geo}}</b>
    </div>
	{{/geo}}
    <div class="opt">
    	{{#showDelButton}}
			<a class="btn btnB" href="javascript:;" onclick="kola('t5touch.twitter.Del', function(DelTwitter) {DelTwitter.del('{{id}}',this);}, {scope:this})"><i class="i iT iT6"></i></a>
		{{/showDelButton}}
		<div class="grp">
			<a class="btn btnB" href="{{locationUrl}}&type=twitter" data-actiontype="forward" data-twid="{{id}}" data-direction="up" ><i class="i iT iT2"></i>{{retweetCount}}</a><a class="btn btnB" href="/m/reply_page.do?i={{id}}"><i class="i iT iT3"></i>{{repliedCount}}</a>
		</div>
		<a class="btn btnB" href="javascript:;" onclick="kola('t5touch.twitter.Fav', function(Fav) {Fav.add('{{id}}',this);}, {scope:this})"><i class="i iT iT7"></i></a><!-- @js: 收藏成功时增加class： on -->
</div>
    <div class="info">
        <b class="time">{{createdOn}}</b>
    </div>
</div>
<!------------------------------------>
