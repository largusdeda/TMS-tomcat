<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма пользователя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3><c:choose>
                        <c:when test="${user != null}">Редактирование</c:when>
                        <c:otherwise>Создание</c:otherwise>
                    </c:choose> пользователя</h3>
                </div>
                <div class="card-body">
                    <form action="controller" method="post">
                        <input type="hidden" name="action" value="save">
                        <c:if test="${user != null}">
                            <input type="hidden" name="id" value="${user.id}">
                        </c:if>

                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username"
                                   name="username" value="${user.username}" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email"
                                   name="email" value="${user.email}" required>
                        </div>

                        <div class="mb-3">
                            <label for="fullName" class="form-label">Полное имя</label>
                            <input type="text" class="form-control" id="fullName"
                                   name="fullName" value="${user.fullName}" required>
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <a href="controller?action=list" class="btn btn-secondary">Отмена</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>