<#macro header>
<header>
    <div class="logo">
        <a href="/wallet">
            <img src="/static/img/logo.png" alt="Logo" width="60" height="60"/>

        </a>
    </div>
    <nav class="menu">
        <a href="/wallet">Wallet</a>
    </nav>
    <div class="profile">
        <#if currentUser?has_content>
            <a href="/logout">${currentUser.name}</a>
        <#else>
            <a href="/logout">Выйти</a>
        </#if>

        <#--                <a href="/logout">${currentUser.getName}</a>-->
        <#--                <a>${cookie.userName.value}</a>-->
        <#--                <c:url value="" var="quitURI">-->
        <#--                    <c:param name="quit" value="1"/>-->
        <#--                </c:url>-->
        <#--                <a href="${quitURI}">Выход</a>-->
    </div>
</header>
</#macro>