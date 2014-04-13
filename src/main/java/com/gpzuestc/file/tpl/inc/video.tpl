	<!--S 顶部导航 navagation-->
	<header class="header">
		<div class="opt opt1">
			<div class="grp"><a href="javascript:;" class="btn" data-sys-action="back"  data-direction="right"><i class="i iH iH4"></i></a></div>
		</div>
		<p>视频</p>
	</header>
	<!------------------------------------>
	<!-- S 视频播放 -->
	{{#videourl}} 
	    <video src="{{videourl}}" style="width:100%;height:100%;"></video>
	{{/videourl}} 
	{{#url}} 
	    <iframe src="{{url}}" style="width:100%;height:100%;"></iframe>
	{{/url}} 
	<!-- E 视频播放-->