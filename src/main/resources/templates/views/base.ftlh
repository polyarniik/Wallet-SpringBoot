<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>
<#macro content></#macro>
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

        <title>Wallet</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/main.css" type="text/css">
        <link rel="stylesheet" href="fontawesome/css/all.css">
        <script src="js/jquery-3.6.0.min.js"></script>
    </head>
    <@content/>
    </html>
</#macro>