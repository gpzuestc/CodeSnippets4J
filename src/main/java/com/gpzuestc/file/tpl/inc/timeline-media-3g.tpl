
{{#media}}
	<div class="media{{#video}}{{#imgThumbnail}} mult{{/imgThumbnail}}{{/video}}"  data-tid="{{id}}" data-wifi="false" onclick="javascript:var self = this; kola('t5touch.image.ImageEffect', function(ImageEffect) {ImageEffect.expand(self);})">
		{{#video}}
		<div class="item itV" data-video="true" data-select="item" data-bigPicUrl="{{bigPicPath}}">
			<p class="ih wgh" data-select="imgP">
				<img src="{{videoThumbnail}}" alt="">
				<i class="i iO iO3"></i>
			</p>
		</div>
		{{/video}}
		{{#imgThumbnail}}
		<div class="item" data-select="item" data-bigPicUrl="{{bigPicPath}}">
			<p class="ih wgh"  data-select="imgP">
				<img class="m" src="{{imgThumbnail}}" alt="">
				{{#multiPic}}
				<i class="ihT" data-select="multiPicCount"  data-count="{{multiPicCount}}">{{multiPicCount}}张</i>
				{{/multiPic}}
				<a href="javascript:;" data-action="showOriginalImg" class="iZo"><i class="i iS iS15"></i></a>
			</p>
		</div>
		{{/imgThumbnail}}
	</div>
{{/media}}
