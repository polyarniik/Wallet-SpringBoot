<#macro header>
<header>
    <div class="logo">
        <a href="<@spring.url '/'/>">
            <img src="/static/img/logo.png" alt="Logo" width="60" height="60"/>
        </a>
    </div>
    <nav class="menu">
        <a href="<@spring.url '/'/>">Wallet</a>
        <p>1 USD = 73 pуб</p>
    </nav>
    <div class="profile">
        <#if currentUser?has_content>
            <a href="<@spring.url 'logout'/>">${currentUser.name}</a>
        <#else>
            <a href="<@spring.url 'logout'/>">Выйти</a>
        </#if>
    </div>
</header>
</#macro>