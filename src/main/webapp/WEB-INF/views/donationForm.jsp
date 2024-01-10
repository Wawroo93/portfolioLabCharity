<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="POST" action="/donations/new" modelAttribute="donation">
    <div>
        <div>
            <h3>Zaznacz co chcesz oddać:</h3>
            <c:forEach items="${categories}" var="category">
                <div class="form-group form-group--checkbox">
                    <label>
                        <input type="checkbox" name="categories" value="${category.id}"/>
                        <span class="checkbox"></span>
                        <span class="description">${category.name}</span>
                    </label>
                </div>
            </c:forEach>
        </div>
        <div>
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>
            <c:forEach items="${institutions}" var="institution">
                <div class="form-group form-group--checkbox">
                    <label>
                        <input type="radio" name="institution" value="${institution.id}"/>
                        <span class="checkbox radio"></span>
                        <span class="description">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">${institution.description}</div>
                </span>
                    </label>
                </div>
            </c:forEach>
        </div>
        <div class="form-section--column">
            <h4>Adres odbioru:</h4>
            <div>
                Kod pocztowy:
                <form:input path="zipCode"/>
            </div>

            <div>
                Ulica:
                <form:input path="street"/>
            </div>

            <div>
                Miasto:
                <form:input path="city"/>
            </div>
        </div>

        <div class="form-section--column">
            <h4>Termin odbioru:</h4>
            <div>
                Ilość:
                <form:input path="quantity"/>
            </div>

            <div>
                Komentarz:
                <form:textarea path="pickUpComment"/>
            </div>

            <div>
                Data odbioru:
                <form:input type="date" path="pickUpDate"/>
            </div>

            <div>
                Godzina odbioru:
                <form:input type="time" path="pickUpTime"/>
            </div>
        </div>
        <input type="submit" value="Submit"/>
    </div>
</form:form>

<%@ include file="footer.jsp" %>
