<#ftl encoding="UTF-8"/>
<#import "../spring.ftl" as spring/>
<#macro content></#macro>
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>

        <title>Wallet</title>
        <link rel="stylesheet" href="<@spring.url "static/css/bootstrap.min.css"/>" type="text/css">
        <link rel="stylesheet" href="<@spring.url "static/css/main.css"/>" type="text/css">
        <link rel="stylesheet" href="<@spring.url "static/fontawesome/css/all.css"/>">
        <script src="<@spring.url "static/js/jquery-3.6.0.min.js"/>"></script>
        <script src="<@spring.url "static/js/script.js"/>"></script>
    </head>
    <@content/>
    </html>
</#macro>