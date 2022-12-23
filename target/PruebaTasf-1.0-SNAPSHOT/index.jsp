<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("username")){
                userName = cookie.getValue();
                break;
            }
        }
    }
    
    if (userName != null) response.sendRedirect("home");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="login-container">
            <div class="alert alert-danger ${message != null ? "visible" : "invisible"}" role="alert">${message}</div>
            <div class="card p-3">
                <form action="login" method="POST">
                    <h2 class="text-center mb-4">Iniciar Sesión</h2>
                    <div class="form-group">
                        <label>Usuario (admin):</label>
                        <input type="text" class="form-control" name="user" />
                    </div>
                    <div class="form-group my-3">
                        <label>Contraseña (admin):</label>
                        <input type="password" class="form-control" name="password" />
                    </div>

                    <input type="submit" class="btn btn-primary mt-2" value="Ingresar"/>
                </form>
            </div>
        </div>
    </body>
</html>
