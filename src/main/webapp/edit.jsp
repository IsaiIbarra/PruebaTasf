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
    <script>
        function getTotal(){
            const quantity = document.getElementById('quantity')?.value;
            const uni_price = document.getElementById('uni_price')?.value;
            if(quantity && uni_price){
                document.getElementById('total').value = quantity * uni_price;
            }
        }
    </script>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="row w-100 p-2">
                <div class="col col-md-6 text-start text-white">
                    <h4>Editar Venta</h4>
                </div>
                <div class="col col-md-6 text-end">
                    <a class="btn btn-sm btn-danger" href="logout">Cerrar Sesión</a>
                </div>
            </div>
        </nav>
            
        <div class="login-container">
            <div class="alert alert-danger ${message != null ? "visible" : "invisible"}" role="alert">${message}</div>
            <div class="card p-3 text-center">
                <form action="update" method="POST">
                    <h2 class="mb-4">Editar</h2>
                    <input type="text" hidden name="id" value="${sale.id}"/>
                    <div class="form-group text-start">
                        <label>Descripción del producto:</label>
                        <input type="text" class="form-control" name="des_prod" required value="${sale.des_prod}"/>
                    </div>
                    <div class="form-group text-start my-3">
                        <label>Cantidad:</label>
                        <input id="quantity" type="number" class="form-control" name="quantity" onchange="getTotal()" required value="${sale.quantity}"/>
                    </div>
                    <div class="form-group text-start my-3">
                        <label>Precio unitario:</label>
                        <input id="uni_price" type="number" class="form-control" name="uni_price" onchange="getTotal()" required value="${sale.uni_price}"/>
                    </div>
                    <div class="form-group text-start my-3">
                        <label>Total:</label>
                        <input id="total" type="number" class="form-control" name="total" disabled required value="${sale.total}"/>
                    </div>

                    <a class="btn btn-secondary mt-2 mr-1" href="home">Volver</a>
                    <input type="submit" class="btn btn-success mt-2" value="Actualizar"/>
                </form>
            </div>
        </div>
    </body>
</html>
