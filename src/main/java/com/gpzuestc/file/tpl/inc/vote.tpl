<section class="vote show{{#voteDto.single}} sgl{{/voteDto.single}}{{^voteDto.single}} mtp{{/voteDto.single}}" data-vote-single="{{voteDto.single}}" data-selector="vote-container" data-twid="{{msg.id}}" data-voteId="{{vi}}">
			{{#voteDto.voteOptions}}
				<div class="item{{#choose}} act{{/choose}}" data-voteItemId="{{id}}" data-selector="vote-item">
					<p class="sta"><i class="i iS iS13"></i></p>
					<div class="ttl"><p>{{{text}}}</p></div>
					<div class="num">
						<p><b>{{count}}</b> 票</p>
						<p>{{percentage}}%</p>
					</div>
					<p class="pbar" style="width:{{percentage}}%;"></p>
				</div>
			{{/voteDto.voteOptions}}
			<div class="btns">
				{{^commentFlag}}
				<div class="opt1">
						<a href="javascript:;" class="btn btnB on" onclick="kola('t5touch.twitter.RendeVote', function(RendeVote) {RendeVote.removeVote(this,'{{i}}');}, {scope:this})"><i class="i iT iT5"></i></a>
				</div>
				{{/commentFlag}}
				<div class="opt2">
				{{#vod}}
					<a href="javascript:;" class="btn btnC btnC1 btnDis">投票</a>
				{{/vod}}				
				{{^vod}}
					{{#voteDto.expire}}
						<a href="javascript:;" class="btn btnC btnC1 btnDis">已结束</a>
					{{/voteDto.expire}}					
					{{^voteDto.expire}}
						<a href="javascript:;" class="btn btnC btnC1"  data-action="submit-vote">投票</a>
					{{/voteDto.expire}}
				{{/vod}}	
				</div>			
			</div>
		</section>