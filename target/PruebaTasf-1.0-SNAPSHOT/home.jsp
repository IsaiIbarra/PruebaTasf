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
    <script>
    </script>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="row w-100 p-2">
                <div class="col col-md-6 text-start text-white">
                    <h4>¡Bienvenido <%=userName%>!</h4>
                </div>
                <div class="col col-md-6 text-end">
                    <a class="btn btn-sm btn-danger" href="logout">Cerrar Sesión</a>
                </div>
            </div>
        </nav>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col col-md-12 mt-2">
                        <div class="alert alert-success alert-dismissible ${message != null ? "visible" : "invisible"}" role="alert">
                            ${message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <a class="btn btn-success mb-3" href="add">Agregar venta</a>
                        <form action="search" method="GET">
                            <div class="row mb-3">
                                <div class="col col-md-4">
                                    <input type="text" class="form-control w-100" name="search" placeholder="Buscar por folio..."/>
                                </div>
                                <div class="col col-md-4">
                                    <input type="submit" class="btn btn-primary mr-1" value="Buscar"/>
                                    <a class="btn btn-secondary" href="home">Restablecer</a>
                                </div>
                            </div>
                        </form>
                            <table class="table table-hover text-center">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Folio</th>
                                        <th>Producto</th>
                                        <th>Cantidad</th>
                                        <th>Precio unitario</th>
                                        <th>Total</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody class="table-light">
                                    <c:forEach var="dto" items="${sales}">
                                        <tr>
                                            <td id="${dto.id}-id">${dto.id}</td>
                                            <td id="${dto.id}-des-prod">${dto.des_prod}</td>
                                            <td id="${dto.id}-quantity">${dto.quantity}</td>
                                            <td id="${dto.id}-uni-price">${dto.uni_price}</td>
                                            <td id="${dto.id}-total">${dto.total}</td>
                                            <td id="${dto.id}-edit"><a class="btn btn-sm btn-success" href="edit?id=${dto.id}">Editar</a></td>
                                        </tr>                                       
                                    </c:forEach>
                                        <tr>
                                            <td id="totalQuantity" class="text-end" colspan="3">Total productos: <b>${totalQuantity}</b></td>
                                            <td id="total-sales" class="text-end" colspan="2">Total Venta: <b>${totalSales}</b></td>
                                            <td id="acciones"></td>
                                        </tr>   
                                </tbody>
                            </table>
                    </div>
                </div>
            </div>
    </body>
</html>
