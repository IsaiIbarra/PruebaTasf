/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import config.DbConnection;
import dtos.ProductDto;
import interfaces.IProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isai - HP
 */
public class ProductDao implements IProduct{
    Connection con;
    DbConnection cn = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    @Override
    public int addProduct(ProductDto prod) {
        String sql = "INSERT INTO products(product, quantity) VALUES (?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getProduct());
            ps.setInt(2, prod.getQuantity());
            r = ps.executeUpdate();
            
            if(r > 0)return 1;
            else return 0;
             
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int editProduct(ProductDto prod) {
        String sql = "UPDATE products SET product = ?, quantity = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getProduct());
            ps.setInt(2, prod.getQuantity());
            ps.setInt(3, prod.getId());
            r = ps.executeUpdate();
            
            if(r > 0)return 1;
            else return 0;
             
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int validateExistProduct(ProductDto prod) {
        String sql = "select * from products where product = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getProduct());
            rs = ps.executeQuery();
            
            if(rs.next())return 1;
            else return 0;
             
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> data = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ProductDto prod = new ProductDto();
                prod.setId(rs.getInt("id"));
                prod.setProduct(rs.getString("product"));
                prod.setQuantity(rs.getInt("quantity"));

                data.add(prod);
            }
        } catch (Exception e) {
        }
        return data;
    }

    @Override
    public ProductDto findProduct(int id) {
        ProductDto prod = new ProductDto();
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setProduct(rs.getString("product"));
                prod.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
        }
        
        return prod;
    }
}
