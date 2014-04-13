{{#msg.media}}
<div class="twi hasMedia">
    <div class="media">
		{{#msg.pic}}
			{{#msg.multiPic}}
				{{#msg.picList}}
					{{>tpl/inc/final-img}}
				{{/msg.picList}}
			{{/msg.multiPic}}
			{{^msg.multiPic}}
				{{>tpl/inc/final-img}}
			{{/msg.multiPic}}
		{{/msg.pic}}
		{{#msg.videoList}}
			{{>tpl/inc/final-video}}
		{{/msg.videoList}}
    </div>
{{/msg.media}}
{{^msg.media}}
	<div class="twi">
{{/msg.media}}
    <div class="avt">
        <a href="/m/profile.do?u={{msg.user.id}}"><i class="img"><img src="{{msg.user.m_icon}}" alt=""></i></a>{{#msg.user.verified}}<a><i class="i iO iO1"></i></a>{{/msg.user.verified}}
    </div>
    <div class="cnt">
        <div class="ugc">
            <a class="nm auth" href="/m/profile.do?u={{msg.user.id}}">{{msg.user.name}}</a>  {{{msg.text}}}
        </div>
		{{#msg.retweeted}}
        <div class="twiRoot">
            <div class="twi" data-href="/m/comment.do?i={{msg.sourceMsg.id}}">
                <div class="cnt">
                    <div class="ugc">
                        <a class="nm" href="/m/profile.do?u={{msg.sourceMsg.user.id}}">@{{msg.sourceMsg.user.name}}</a>  {{{msg.sourceMsg.text}}}
                    </div>
					{{#msg.sourceMsg.singleVote}}
					<div class="ap">
						<a class="btn" href="/m/comment.do?i={{msg.sourceMsg.id}}"><i class="i iO iO2"></i></a>
					</div>
					{{/msg.sourceMsg.singleVote}}
                </div>
            </div>
        </div>
		{{/msg.retweeted}}
		{{#msg.singleVote}}
			{{>tpl/inc/vote}}
		{{/msg.singleVote}}
    </div>
	
	{{#msg.geo}}
	<div class="locat">
        <a class="place"><i class="i iT iT4"></i>{{msg.geo}}</a>
    </div>
	{{/msg.geo}}
	<div class="info">
		<b class="time">{{msg.createdOn}}</b>
		<b class="from">通过{{msg.fromTypeName}}</b>
	</div>
</div>
<section class="opts">
		<div class="opt opt1">
			
			{{^favorited}}
			<a href="javascript:;" data-twid='{{msg.id}}' data-action="addFav" class="btn"><i class="i iS iS9"></i></a>
			{{/favorited}}
			
			{{#favorited}}
			<a href="javascript:;" data-twid='{{msg.id}}' data-action="delFav" class="btn on"><i class="i iS iS9"></i></a>
			{{/favorited}}
			
			{{#msg.showDelButton}}
			<a href="javascript:;" data-twid='{{msg.id}}' data-action="delTwitter" class="btn"><i class="i iS iS10"></i></a>
			{{/msg.showDelButton}}
		</div>
		<div class="opt opt2">
			<a href="/m/comment.do?i={{msg.id}}&type=twitter" data-actionType="forward" data-twid="{{msg.id}}" data-direction="down" class="btn"><i class="i iT iT2"></i>{{msg.retweetCount}}</a>
			<a href="/m/comment.do?i={{msg.id}}&type=twitter" data-actionType="reply" data-twid="{{msg.id}}" data-direction="down" class="btn"><i class="i iT iT3"></i>{{msg.repliedCount}}</a>
		</div>
	</section>
<!------------------------------------>
