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
        <title>Principal</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-end">
            <span class="navbar-text text-white p-2">
                <h4>¡Bienvenido a tu lista de compras <%=userName%>!</h4>
            </span>
            <div class="collapse navbar-collapse justify-content-end p-2" id="navbarTogglerDemo03">
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a class="btn btn-sm btn-danger mr-2" href="logout">Cerrar Sesión</a>
                </li>
              </ul>
            </div>
        </nav>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col col-md-8 mt-5">
                        <div class="alert alert-success alert-dismissible ${message != null ? "visible" : "invisible"}" role="alert">
                            ${message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <a class="btn btn-success mb-3" href="add">Agregar</a>
                        <table class="table table-hover text-center">
                            <thead class="table-dark">
                                <tr>
                                    <th>Producto</th>
                                    <th>Cantidad</th>
                                    <th>Acción</th>
                                </tr>
                            </thead>
                            <tbody class="table-light">
                                <c:forEach var="dto" items="${prods}">
                                    <tr>
                                        <td id="${dto.id}-product">${dto.product}</td>
                                        <td id="${dto.id}-quantity">${dto.quantity}</td>
                                        <td id="${dto.id}-edit"><a class="btn btn-sm btn-success" href="edit?id=${dto.id}">Editar</a></td>
                                    </tr>                                       
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    </body>
</html>
