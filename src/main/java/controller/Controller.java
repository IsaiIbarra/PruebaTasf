
package controller;

import daos.SalesDao;
import daos.UserDao;
import dtos.SalesDto;
import dtos.UserDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/login", "/logout", "/home", "/edit", "/add", "/update", "/insert", "/search"})
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
            
            SalesDao saleDao = new SalesDao();
            SalesDto saleDto = new SalesDto();
            float totalSales = 0;
            int totalQuantity = 0;
            
            switch (ruta) {
                case "/logout":
                    Cookie loginCookie = new Cookie("username", null);
                    loginCookie.setMaxAge(0);
                    response.addCookie(loginCookie);
                    request.removeAttribute("message");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                    
                case "/home":
                    List<SalesDto> data = saleDao.getSales();
                    for (SalesDto sale : data)
                    {
                        totalSales += sale.getTotal();
                        totalQuantity += sale.getQuantity();
                    }
                    request.setAttribute("sales", data);
                    request.setAttribute("totalSales", totalSales);
                    request.setAttribute("totalQuantity", totalQuantity);
            
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    break;
                    
                case "/edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    saleDto.setId(id);
                    SalesDto sale = saleDao.findSale(id);
                    request.setAttribute("sale", sale);
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                    
                case "/add":
                    request.getRequestDispatcher("add.jsp").forward(request, response);
                    break;
                    
                case "/search":
                    List<SalesDto> dataSearch = new ArrayList<>();
                    int search = 0;
                    try {
                        if(request.getParameter("search").equals("")){
                            throw new Exception();
                        }
                        search = Integer.parseInt(request.getParameter("search"));
                    } catch (Exception e) {
                        search = 0;
                    }
                    saleDto.setId(search);
                    SalesDto saleSearch = saleDao.findSale(search);
                    
                    if(saleSearch.getId() > 0){
                        dataSearch.add(saleSearch);
                    }else if(request.getParameter("search").equals("")){
                        dataSearch = saleDao.getSales();
                    }else{
                        request.setAttribute("message", "No se encontraron resultados");
                    }
                    
                    for (SalesDto saleSe : dataSearch)
                    {
                        totalSales += saleSe.getTotal();
                        totalQuantity += saleSe.getQuantity();
                    }
                    
                    request.setAttribute("sales", dataSearch);
                    request.setAttribute("totalSales", totalSales);
                    request.setAttribute("totalQuantity", totalQuantity);
                    
                    request.getRequestDispatcher("home.jsp").forward(request, response);
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
            
            SalesDao saleDao = new SalesDao();
            SalesDto saleDto = new SalesDto();
                    
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
                    
                    String desProdUpdate = request.getParameter("des_prod");
                    int quantityUpdate = Integer.parseInt(request.getParameter("quantity"));
                    float uniPriceUpdate = Float.parseFloat(request.getParameter("uni_price"));
                    float totalUpdate = (float) (quantityUpdate * uniPriceUpdate);
                    
                    saleDto.setId((id));
                    saleDto.setDes_prod(desProdUpdate);
                    saleDto.setQuantity(quantityUpdate);
                    saleDto.setUni_price(uniPriceUpdate);
                    saleDto.setTotal(totalUpdate);
                    r = saleDao.editSale(saleDto);
                    
                    if(r == 1){
                        List<SalesDto> data = saleDao.getSales();
                        float totalSales = 0;
                        int totalQuantity = 0;
                        for (SalesDto sale : data)
                        {
                            totalSales += sale.getTotal();
                            totalQuantity += sale.getQuantity();
                        }
                        request.setAttribute("sales", data);
                        request.setAttribute("totalSales", totalSales);
                        request.setAttribute("totalQuantity", totalQuantity);
                    
                        request.setAttribute("message", "Venta actualizada correctamente.");
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }else{
                        request.setAttribute("message", "Error al actualizar la venta");
                        request.getRequestDispatcher("edit.jsp").forward(request, response);
                    }
                    break;
                    
                case "/insert":
                    String desProdAdd = request.getParameter("des_prod");
                    int quantityAdd = Integer.parseInt(request.getParameter("quantity"));
                    float uniPriceAdd = Float.parseFloat(request.getParameter("uni_price"));
                    float totalAdd = (float) (quantityAdd * uniPriceAdd);
                    
                    
                    saleDto.setDes_prod(desProdAdd);
                    saleDto.setQuantity(quantityAdd);
                    saleDto.setUni_price(uniPriceAdd);
                    saleDto.setTotal(totalAdd);
                    r = saleDao.addSale(saleDto);
                    
                    if(r == 1){
                        List<SalesDto> data = saleDao.getSales();
                        float totalSales = 0;
                        int totalQuantity = 0;
                        for (SalesDto sale : data)
                        {
                            totalSales += sale.getTotal();
                            totalQuantity += sale.getQuantity();
                        }
                        request.setAttribute("sales", data);
                        request.setAttribute("totalSales", totalSales);
                        request.setAttribute("totalQuantity", totalQuantity);
                    
                        request.setAttribute("message", "Venta agregada correctamente.");
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }else{
                        request.setAttribute("message", "Error al agregar la venta");
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
