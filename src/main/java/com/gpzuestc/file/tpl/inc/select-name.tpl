	<header class="header">
		<div class="opt opt1">
			<div class="grp"><a href="javascript:void(0);" data-sys-action="back"  data-direction="right" class="btn"><i class="i iH iH4"></i></a></div>
		</div>
	</header>
	<!--S 发表框-->
    <div data-selector='suggest-container'>
        <section class="ttl post input">
            <input class="t" type="text" placeholder="请输入" autocomplete="off" data-selector='suggest-input'>
            <a href="javascript:;" class="btn btnC btnC3"  data-action="submit-select">确定</a>
        </section>
        <section class="ttl post assoc">
            <ul data-selector="item-container">
            {{>tpl/inc/select-name-item}}
            </ul>
        </section>

    </div>

	<!--E 发表框-->