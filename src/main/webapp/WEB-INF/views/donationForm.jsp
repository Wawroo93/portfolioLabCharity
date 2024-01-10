<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="POST" action="/donations/new" modelAttribute="donation">
    <section class="help">
        <div class="help--slides-items">
            <h2>Zaznacz co chcesz oddać:</h2>
            <c:forEach items="${categories}" var="category">
                <div class="form-group form-group--checkbox">
                    <label>
                        <form:checkbox path="categories" value="${category.id}"/>
                        <span class="checkbox"></span>
                        <span class="description">${category.name}</span>
                    </label>
                </div>
            </c:forEach>
        </div>
        <div class="help--slides-items">
            <h2>Wybierz organizacje, której chcesz pomóc:</h2>
            <c:forEach items="${institutions}" var="institution">
                <div class="form-group form-group--checkbox">
                    <label>
                        <form:radiobutton path="institution" value="${institution.id}"/>
                        <span class="checkbox radio"></span>
                        <span class="description">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">${institution.description}</div>
                </span>
                    </label>
                </div>
            </c:forEach>
        </div>
        <div class="help--slides-items">
            <div class="form-group">
                <h2>Ilość worków:</h2>
                <form:input type="number" path="quantity" placeholder="Ilość worków"/>
            </div>
        </div>
        <div class="help--slides-items">
            <div class="form-group">
                <h2>Adres odbioru:</h2>
            </div>
            <div class="form-group">
                <form:input type="text" path="zipCode" placeholder="Kod pocztowy"/>
            </div>
            <div class="form-group">
                <form:input type="text" path="street" placeholder="Ulica"/>
            </div>
            <div class="form-group">
                <form:input type="text" path="city" placeholder="Miasto"/>
            </div>
        </div>
        <div class="help--slides-items">
            <div class="form-group">
                <h2>Termin odbioru:</h2>
            </div>
            <div class="form-group">
                <form:textarea path="pickUpComment" placeholder="Komentarz"/>
            </div>
            <div class="form-group">
                <form:input type="date" path="pickUpDate" placeholder="Data odbioru"/>
            </div>
            <div class="form-group">
                <form:input type="time" path="pickUpTime" placeholder="Godzina odbioru"/>
            </div>
        </div>
        <button class="btn" type="submit" style="margin-top: 20px;">Wyślij</button>
    </section>
</form:form>

<%@ include file="footer.jsp" %>
