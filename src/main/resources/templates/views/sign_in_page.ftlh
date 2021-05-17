<#include 'base.ftlh'>
<#macro content>
    <body class="sign-body">
    <div class="sign-wrapper">
        <h1 class="name">Wallet</h1>
        <form method="post">
            <div class="form-row">
                <div class="form-group col-md-12">
                    <input class="email  form-control" name="email" type="text" placeholder="Введите email" required>
                </div>
                <div class="form-group col-md-12">
                    <input class="password  form-control" name="password" type="password" placeholder="Введите пароль"
                           required>
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-primary col-md-6">Войти</button>
                <a class="link-to-registration" href="">Зарегистрироваться!</a>
            </div>
        </form>
        <#--        <c:if test="${not empty requestScope.loginErr}">-->
        <#--            <span class="loginErr" style="color:red;"><c:out value=""/></span>-->
        <#--        </c:if>-->
    </div>
    </body>
</#macro>
<@page/>