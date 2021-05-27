<#include '../partials/base.ftlh'>
<#import "../spring.ftl" as spring/>
<#macro content>
    <body class="sign">
    <div class="form-center">
        <div class="h2 mb-3 font-weigh  t-normal text-center">Wallet</div>
        <form class="form-login" method="post" action="${springMacroRequestContext.contextPath}/signIn">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">


            <h1 class="h4 mb-3 font-weight-normal text-center">Авторизация</h1>
            <input class="form-control first" name="email" type="email" placeholder="Email" required>
            <input class="form-control last" name="password" type="password" placeholder="Пароль">
            <#if error?has_content>
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </#if>
            <button type="submit" class="btn btn-lg btn-primary btn-block">Войти</button>
            <a href="/signUp">Зарегистрироваться!</a>
        </form>
        <#--        <c:if test="${not empty requestScope.loginErr}">-->
        <#--            <span class="loginErr" style="color:red;"><c:out value=""/></span>-->
        <#--        </c:if>-->
    </div>
    </body>
</#macro>
<@page/>