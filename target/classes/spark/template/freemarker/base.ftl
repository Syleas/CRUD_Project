<#macro page_head>
	<title>Base Template</title>
	<link rel="stylesheet" href="/css/default.css">
</#macro>

<#macro page_body>
	<h1>Basic Page</h1>
	<p>This is the body of the page</p>
</#macro>

<#macro display_page>
	<!DOCTYPE html>
	<html>
		<head>
			<@page_head/>
		</head>
		<body>
			<@page_body/>
		</body>
	</html>
</#macro>