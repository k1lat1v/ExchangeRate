<!--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->
<html>
<head>
    <title>Курс UAH</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>

    <div class="d-flex flex-column justify-content-center align-items-center bg-info">
        <img height="100" width="155" src="<c:url value="/static/logo.png"/>"/>
        <p class="h3">Выберите дату, чтобы посмотреть курс гривны UAH</p>
        <form action="/index" method="post">
            <input type="date" name="date" min="${min}" max="${max}">
            <input type="submit" value="Выбрать" class="btn btn-success">
        </form>
        <p class="bg-warning rounded" id="date">Дата: <c:out value="${date}"/></p>
    </div>

    <div class="d-flex justify-content-center bg-dark">
        <table class="table table-dark table-hover table-bordered text-center">
            <tr>
                <th>Базовая валюта</th>
                <th>Валюта сделки</th>
                <th>Курс продажи НБУ</th>
                <th>Курс покупки НБУ</th>
                <th>Курс продажи ПриватБанка</th>
                <th>Курс покупки ПриватБанка</th>
            </tr>
            <c:forEach items="${exchangeRate}" var="er">
                <tr>
                    <td>${er.baseCurrency}</td>
                    <td>${er.currency}</td>
                    <td>${er.saleRateNB}</td>
                    <td>${er.purchaseRateNB}</td>
                    <td>${er.saleRate}</td>
                    <td>${er.purchaseRate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>

</body>
</html>
