<#include '../partials/base.ftlh'>
<#import '../partials/header.ftlh' as header/>
<#macro content>
    <body>
    <div class="wrapper">
        <@header.header/>
        <div class="banks flex-wrap flex">
            <#list banks as bank>
                <div class="account col-md-4" style="background-color: ${bank.color}">
                    <img src="img/coins.png" alt="Монеты">
                    <div class="account-data">
                        <div class="account-data-title"><b>${bank.name}</b></div>
                        <div class="account-data-value">${bank.amount}</div>
                    </div>
                </div>
            </#list>
            <button type="button" class="modal-btn btn btn-primary col-md-4" data-toggle="modal"
                    data-target="#staticBackdrop">
                Добавить банк +
            </button>
        </div>
        <div class="record-add-container">
            <@spring.bind "recordForm"/>
            <form method="post" action="" id="add-record-form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <#--                <input type="hidden" name="accountId" value="${currentUser.id}"/>-->
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <select class="bank-select form-control" name="bankId" required>
                            <#list banks as bank>
                                <#if editRecord?? && editRecord.bank.id == bank.id>
                                    <option value="${bank.id}" selected>${bank.name}</option>
                                <#else>
                                    <option value="${bank.id}">${bank.name}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <select class="record-type-select form-control" name="isIncome" required>
                            <#if editRecord?? && editRecord.sum < 0>
                                <option value="False" selected>Расход</option>
                            <#else>
                                <option value="False">Расход</option>
                            </#if>
                            <#if editRecord?? && (editRecord.sum > 0) >
                                <option value="True" selected>Доход</option>
                            <#else>
                                <option value="True">Доход</option>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <input class="record-input  form-control" id="record-input" name="sum" type=number
                               placeholder="0"
                                <#if editRecord??>
                                    value="${editRecord.sum}"
                                </#if>
                               min="0"
                               max="1000000000"
                               step="0.01"
                               pattern="([0-9]+\.?[0-9]{1,2})"
                               required>
                    </div>
                    <div class="form-group col-md-2">
                        <select class="category-select form-control" name="categoryId" required>
                            <#list categories as category>
                                <#if editRecord?? && editRecord.category.id = category.id>
                                    <option value="${category.id}" selected>${category.name}</option>
                                <#else>
                                    <option value="${category.id}">${category.name}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <#if editRecord??>
                            <input type="date" class="form-control" id="date" name="date" placeholder="Дата"
                                   value="${editRecord.date}" required>
                        <#else>
                            <input type="date" class="form-control" id="date" name="date" placeholder="Дата" required>
                        </#if>

                    </div>
                    <div class="form-group col-md-2">
                        <#if editRecord??>
                            <input class="description  form-control" name="description" type="text"
                                   placeholder="Введите описание" maxlength="100" value="${editRecord.description}">
                        <#else>
                            <input class="description  form-control" name="description" type="text"
                                   placeholder="Введите описание" maxlength="100">
                        </#if>

                        <div class="account">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4">
                        <#if editRecord??>
                            <button type="button" class="add-record btn">Изменить</button>
                        <#else>
                            <button type="button" class="add-record btn">Добавить</button>
                        </#if>
                    </div>
                </div>
            </form>
        </div>
        <div class="record-list-container">
            <div class="records-list">
                <div class="table-responsive">
                    <table class="table" id="record-table">
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
                            <tr>
                                <td class="edit-icon">
                                    <a href="/edit/${record.id}"><i class="fas fa-edit"></i></a>
                                </td>
                                <td>
                                    <div class="record-category">
                                        <div class="record-icon"><i class="fas ${record.category.iconName} mr-4"
                                                                    style="color: ${record.category.color}"></i></div>
                                        ${record.category.name}
                                    </div>
                                </td>
                                <td class="record-account text-center">${record.bank.name}</td>
                                <td class="record-description text-center">
                                    <#if record.description??>
                                        ${record.description}
                                    </#if>
                                </td>
                                <td class="record-sum text-center">${record.sum}</td>
                                <td class="record-date text-right">
                                    <#if record.date??>${record.date}</#if>
                                </td>
                                <td class="delete-icon">
                                    <a href="/del/${record.id}"><i class="fas fa-trash"></i></a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@page/>
