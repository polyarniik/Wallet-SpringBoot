<#ftl encoding='UTF-8'/>
<#include 'base.ftlh'>
<#macro content>
    <body class="sign-body">
    <div class="sign-wrapper">
        <h1 class="name">Wallet</h1>
        <form method="post">
            <div class="form-row">
                <div class="form-group col-md-12">
                    <input class="name  form-control" name="name" type="text" placeholder="Введите имя" required>
                </div>
                <div class="form-group col-md-12">
                    <input class="email  form-control" name="email" type="text" placeholder="Введите email" required>
                </div>
                <div class="form-group col-md-12">
                    <input class="password  form-control" name="password" type="password" placeholder="Введите пароль" required>
                </div>
                <div class="form-group col-md-12">
                    <input class="passwordRepeat  form-control" name="passwordRepeat" type="password" placeholder="Введите пароль ещё раз" required>
                </div>
            </div>
            <div class="registration-bottom justify-content-between">
                <button type="submit" class="btn btn-primary col-md-6">Зарегистрироваться</button>
                <a class="link-to-login" href="#" style="margin-top: 10px; text-align: center">Войти</a>
            </div>
        </form>
<#--        <c:if test="${not empty requestScope.regErr}">-->
<#--            <span class="regErr" style="text-align: center; color: red"><c:out value="${requestScope.regErr}"/></span>-->
<#--        </c:if>-->
    </div>
    </body>
</#macro>
<@page/>
