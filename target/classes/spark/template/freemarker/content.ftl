<#include "base.ftl">
 
<#macro page_head>
  <title>Mi página</title>
</#macro>
 
<#macro page_body>
  <h1>Mi Body</h1>
  <p>Hola ${name}</p>
</#macro>
 
<@display_page/>