<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                userName = cookie.getValue();
                break;
            }
        }
    }
    if (userName == null) response.sendRedirect("index.jsp");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-end">
            <span class="navbar-text text-white p-2">
                <h4>Agregar Producto</h4>
            </span>
            <div class="collapse navbar-collapse justify-content-end p-2" id="navbarTogglerDemo03">
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a class="btn btn-sm btn-danger mr-2" href="logout">Cerrar Sesión</a>
                </li>
              </ul>
            </div>
        </nav>
            
        <div class="login-container">
            <div class="alert alert-danger ${message != null ? "visible" : "invisible"}" role="alert">${message}</div>
            <div class="card p-3 text-center">
                <form action="insert" method="POST">
                    <h2 class="mb-4">Agregar</h2>
                    <div class="form-group text-start">
                        <label>Producto:</label>
                        <input type="text" class="form-control" name="product" />
                    </div>
                    <div class="form-group text-start my-3">
                        <label>Cantidad:</label>
                        <input type="number" class="form-control" name="quantity"/>
                    </div>

                    <a class="btn btn-secondary mt-2 mr-1" href="home">Volver</a>
                    <input type="submit" class="btn btn-success mt-2" value="Guardar"/>
                </form>
            </div>
        </div>
    </body>
</html>
