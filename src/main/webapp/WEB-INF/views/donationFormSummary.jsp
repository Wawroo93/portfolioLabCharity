<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="help">
    <div class="help--slides-items">
        <h2>Podsumowanie</h2>
        <h2>Wybrane kategorie:</h2>
        <c:forEach items="${donation.categories}" var="category">
            <div class="form-group form-group--checkbox">
                <label>
                    <span class="checkbox"></span>
                    <span class="description">${category.name}</span>
                </label>
            </div>
        </c:forEach>
    </div>
    <div class="help--slides-items">
        <h2>Wybrana organizacja:</h2>
        <div class="form-group form-group--checkbox">
            <label>
                <span class="checkbox radio"></span>
                <span class="description">
                    <div class="title">${donation.institution.name}</div>
                    <div class="subtitle">${donation.institution.description}</div>
                </span>
            </label>
        </div>
    </div>
    <div class="help--slides-items">
        <div class="form-group">
            <h2>Ilość worków:</h2>
            <p>${donation.quantity}</p>
        </div>
    </div>
    <div class="help--slides-items">
        <div class="form-group">
            <h2>Adres odbioru:</h2>
            <p>Kod pocztowy: ${donation.zipCode}</p>
            <p>Ulica: ${donation.street}</p>
            <p>Miasto: ${donation.city}</p>
        </div>
    </div>
    <div class="help--slides-items">
        <div class="form-group">
            <h2>Termin odbioru:</h2>
            <p>Komentarz: ${donation.pickUpComment}</p>
            <jsp:useBean id="date" class="java.util.Date" />
            <fmt:parseDate value="${donation.pickUpDate}" pattern="yyyy-MM-dd" var="date" />
            <p>Data odbioru: <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></p>
            <p>Godzina odbioru: <fmt:formatDate value="${pickUpTime}" pattern="HH:mm"/></p>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>