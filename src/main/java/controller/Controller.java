/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.ProductDao;
import daos.UserDao;
import dtos.ProductDto;
import dtos.UserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Isai - HP
 */
@WebServlet(name = "Controller", urlPatterns = {"/login", "/logout", "/home", "/edit", "/add", "/update", "/insert"})
public class Controller extends HttpServlet {

    UserDao userDao = new UserDao();
    UserDto userDto = new UserDto();
    
    int r;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ruta = request.getServletPath();
            response.setContentType("text/html;charset=UTF-8");
            ProductDao prodDao = new ProductDao();
            ProductDto prodDto = new ProductDto();
            switch (ruta) {
                case "/logout":
                    Cookie loginCookie = new Cookie("username", null);
                    loginCookie.setMaxAge(0);
                    response.addCookie(loginCookie);
                    request.removeAttribute("message");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                    
                case "/home":
                    List<ProductDto> data = prodDao.getProducts();
                    request.setAttribute("prods", data);
            
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    break;
                    
                case "/edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    prodDto.setId(id);
                    ProductDto prod = prodDao.findProduct(id);
                    request.setAttribute("product", prod);
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                    
                case "/add":
                    request.getRequestDispatcher("add.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String ruta = request.getServletPath();
            response.setContentType("text/html;charset=UTF-8");
            
            ProductDao prodDao = new ProductDao();
            ProductDto prodDto = new ProductDto();
                    
            switch (ruta) {
                case "/login":
                    String user = request.getParameter("user");
                    String password = request.getParameter("password");
                    userDto.setUsername(user);
                    userDto.setPassword(password);
                    r = userDao.validate(userDto);
                    
                    if(r == 1){
                        Cookie loginCookie = new Cookie("username", user);
                        //setting cookie to expiry in 30 mins
                        loginCookie.setMaxAge(30 * 60);
                        response.addCookie(loginCookie);
                        
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }else{
                        request.setAttribute("message", "Usuario o contraseña erronea.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
                    
                case "/update":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String product = request.getParameter("product");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    prodDto.setId((id));
                    prodDto.setProduct(product);
                    prodDto.setQuantity(quantity);
                    r = prodDao.editProduct(prodDto);
                    
                    if(r == 1){
                        List<ProductDto> data = prodDao.getProducts();
                        request.setAttribute("prods", data);
                    
                        request.setAttribute("message", "Producto actualizado correctamente.");
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }else{
                        request.setAttribute("message", "Error al actualizar el producto");
                        request.getRequestDispatcher("edit.jsp").forward(request, response);
                    }
                    break;
                    
                case "/insert":
                    String productadd = request.getParameter("product");
                    int quantityadd = Integer.parseInt(request.getParameter("quantity"));
                    
                    prodDto.setProduct(productadd);
                    prodDto.setQuantity(quantityadd);
                    r = prodDao.addProduct(prodDto);
                    
                    if(r == 1){
                        List<ProductDto> data = prodDao.getProducts();
                        request.setAttribute("prods", data);
                    
                        request.setAttribute("message", "Producto agregado correctamente.");
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }else{
                        request.setAttribute("message", "Error al agregar el producto");
                        request.getRequestDispatcher("add.jsp").forward(request, response);
                    }
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
