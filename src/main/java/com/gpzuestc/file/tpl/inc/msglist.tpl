{{#showLoading}}
	{{>tpl/inc/loading}}
{{/showLoading}}
{{^showLoading}}
	{{#docs}}
		{{>tpl/inc/timeline-msg}}
    {{/docs}}
{{/showLoading}}

