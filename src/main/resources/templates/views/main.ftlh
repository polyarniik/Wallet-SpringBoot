<#include 'base.ftlh'>
<#macro content>
    <body>
    <div class="wrapper">
        <header>
            <div class="logo">
                <a href="#">
                    <img src="/static/img/logo.png" alt="Logo" width="60" height="60"/>
                </a>
            </div>
            <nav class="menu">
                <p>Wallet</p>
            </nav>
            <div class="profile">
                <#if currentUser?has_content>
                    <a href="/logout">${currentUser.getName()}</a>
                </#if>
                <#--                <a href="/logout">${currentUser.getName}</a>-->
                <#--                <a>${cookie.userName.value}</a>-->
                <#--                <c:url value="" var="quitURI">-->
                <#--                    <c:param name="quit" value="1"/>-->
                <#--                </c:url>-->
                <#--                <a href="${quitURI}">Выход</a>-->
            </div>
        </header>
        <div class="banks flex-wrap flex">
            <#list banks as bank>
                <div class="account col-md-4" style="background-color: ${bank.getColor()}">
                    <img src="img/coins.png" alt="Монеты">
                    <div class="account-data">
                        <div class="account-data-title"><b>${bank.getName()}</b></div>
                        <div class="account-data-value">${bank.getAmount()}</div>
                    </div>
                </div>
            </#list>
            <#--            <c:url var="coins" value="/static/img/coins.png"/>-->
            <#--            <c:forEach items="${requestScope.banksList}" var="bank">-->
            <#--                <div class="account col-md-4" style="background-color: ${bank.color}">-->
            <#--                    <img src="${coins}" alt="">-->
            <#--                    <div class="account-data">-->
            <#--                        <div class="account-data-title"><b>${bank.name}</b></div>-->
            <#--                        <div class="account-data-value">${bank.amount}</div>-->
            <#--                    </div>-->
            <#--                </div>-->
            <#--            </c:forEach>-->
            <#--            <c:if test="${requestScope.addBanksList.size() > 0}">-->
            <!-- Button trigger modal -->
            <button type="button" class="modal-btn btn btn-primary col-md-4" data-toggle="modal"
                    data-target="#staticBackdrop">
                Добавить банк +
            </button>
            <#--            </c:if>-->
            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Новый банк</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post">
                                <input type="hidden" name="form" value="form1"/>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <div class="form-group">
                                            <#--                                            <select class="bank-select form-control" name="bank-add" required="required">-->
                                            <#--                                                <c:forEach var="bank" items="${requestScope.addBanksList}">-->
                                            <#--                                                    <option value="${bank.id}">${bank.name}</option>-->
                                            <#--                                                </c:forEach>-->
                                            <#--                                            </select>-->
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input class="record-input  form-control" name="bank-add-amount" type=number
                                               placeholder="0"
                                               min="0"
                                               max="1000000000"
                                               step="0.01"
                                               pattern="([0-9]+\.?[0-9]{1,2})" value="0">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Добавить банк</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                            </form>
                        </div>
                        <%--
                        <div class="modal-footer justify-content-between">--%>
                            <%--
                        </div>
                        --%>
                    </div>
                </div>
            </div>
        </div>
        <div class="record-add-container">
            <form method="post">
                <input type="hidden" name="form" value="form2"/>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <select class="bank-select form-control" name="bank-select">
                            <#--                            <c:forEach items="${requestScope.banksList}" var="bank">-->
                            <#--                                <option value="${bank.id}" <c:if-->
                            <#--                                        test="${not empty requestScope.editRecord and requestScope.editRecord.category.id == bank.id}">-->
                            <#--                                    selected="selected"</c:if>>${bank.name}</option>-->
                            <#--                            </c:forEach>-->
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <select class="record-type-select form-control" name="record-type-select" required="required">
                            <#--                            <option value="0" <c:if-->
                            <#--                                    test="${not empty requestScope.editRecord and requestScope.editRecord.sum < 0}"> selected="selected" </c:if>>-->
                            Расход
                            </option>
                            <#--                            <option value="1" <c:if-->
                            <#--                                    test="${not empty requestScope.editRecord and requestScope.editRecord.sum > 0}"> selected="selected" </c:if>>-->
                            Доход
                            </option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <input class="record-input  form-control" id="record-input" name="record-input" type=number
                               placeholder="0"
                                <#--                                                <c:if test="${not empty requestScope.editRecord}">-->
                                <#--                            value="<c:out value="${requestScope.editRecord.sum < 0 ? -requestScope.editRecord.sum : requestScope.editRecord.sum}"/>"-->
                                <#--                        </c:if>-->
                               min="0"
                               max="1000000000"
                               step="0.01"
                               pattern="([0-9]+\.?[0-9]{1,2})"
                               required="required">
                    </div>
                    <div class="form-group col-md-2">
                        <select class="category-select form-control" name="category-select">
                            <#--                            <c:forEach var="category" items="${requestScope.categoryList}">-->
                            <#--                                <option value="${category.id}"-->
                            <#--                                <c:if test="${not empty requestScope.editRecord and requestScope.editRecord.category.id == category.id}">-->
                            <#--                                    selected="selected"</c:if>>-->
                            <#--                                ${category.name}</option>-->
                            <#--                            </c:forEach>-->
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="date" class="form-control" id="date" name="date" placeholder="Дата">
                        <#--                        <c:if test="${not empty requestScope.editRecordDate}">-->
                        <#--                            value="<c:out value="${requestScope.editRecordDate}"/>"-->
                        <#--                        </c:if> required>-->
                    </div>
                    <div class="form-group col-md-2">
                        <input class="description  form-control" name="description" type="text"
                               placeholder="Введите описание" maxlength="100">

                        <#--                        <c:if test="${not empty requestScope.editRecord}">-->
                        <#--                            value="${requestScope.editRecord.description}" </c:if>>-->
                        <div class="account">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4">
                        <#--                                                <button type="submit" class="add-record btn"><c:if test="${not empty requestScope.editRecord}">-->
                        <#--                                <c:out value="Изменить"/> </c:if>-->
                        <#--                            <c:if test="${empty requestScope.editRecord}"> <c:out value="Добавить"/> </c:if></button>-->
                    </div>
                </div>
            </form>
        </div>
        <div class="record-list-container">
            <form style="display:flex; justify-content: center;">
                <div class="form-group col-md-3">
                    <select class="select-record-date form-control">
                        <option>Последняя неделя</option>
                        <option>Последний месяц</option>
                        <option>Последний год</option>
                    </select>
                </div>
            </form>
            <div class="records-list">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th scope="col">
                                <div class="record-category">
                                    <div class="record-icon"><i class=" fas fa-shopping-bag mr-4"></i></div>
                                    Категория
                                </div>
                            </th>
                            <th scope="col" class="record-account text-center">Банк</th>
                            <th scope="col" class="record-description text-center">Заметка</th>
                            <th scope="col" class="record-sum text-center">Сумма</th>
                            <th scope="col" class="record-date text-right">Дата</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list records as record>
                        <#--                        <c:forEach var="record" items="${requestScope.recordsList}">-->
                            <tr>
                                <td class="edit-icon">
                                    <#--                                    <c:url value="" var="editURI">-->
                                    <#--                                        <c:param name="rec" value="${record.id}"/>-->
                                    <#--                                    </c:url>-->
                                    <a href="/edit/${record.getId()}"><i class="fas fa-edit"></i></a>
                                </td>
                                <td>
                                    <div class="record-category">
                                        <div class="record-icon"><i class=" fas ${record.getCategory()} mr-4"></i></div>
                                        ${record.getCategory().getName()}
                                    </div>
                                </td>
                                <td class="record-account text-center">${record.getBank().getName()}</td>
                                <td class="record-description text-center">${record.getDescription()}</td>
                                <td class="record-sum text-center">${record.getSum()}</td>
                                <td class="record-date text-right">${record.getDate()}</td>
                                <td class="delete-icon">
                                    <#--                                    <c:url value="" var="delURI">-->
                                    <#--                                        <c:param name="del" value="${record.id}"/>-->
                                    <#--                                    </c:url>-->
                                    <a href="/del/${record.id}"><i class="fas fa-trash"></i></a>
                                </td>
                            </tr>
                        </#list>

                        <#--                        </c:forEach>-->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@page/>
