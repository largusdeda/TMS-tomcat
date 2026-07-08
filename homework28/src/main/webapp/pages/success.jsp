<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Заявка сохранена</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card border-success shadow">
                <div class="card-header bg-success text-white">
                    <h3 class="mb-0">Заявка успешно сохранена</h3>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Информация о заявке:</h5>
                    <ul class="list-group list-group-flush mb-3">
                        <li class="list-group-item">
                            <strong>Имя:</strong> ${savedName}
                        </li>
                        <li class="list-group-item">
                            <strong>Email:</strong> ${savedEmail}
                        </li>
                        <li class="list-group-item">
                            <strong>Тема:</strong> ${savedSubject}
                        </li>
                        <li class="list-group-item">
                            <strong>Текст заявки:</strong><br>
                            ${savedMessage}
                        </li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/save-request"
                       class="btn btn-primary">
                        Создать новую заявку
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>