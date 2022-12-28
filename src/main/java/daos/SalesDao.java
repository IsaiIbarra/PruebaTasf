package daos;
import config.DbConnection;
import dtos.SalesDto;
import interfaces.ISales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDao implements ISales{
    Connection con;
    DbConnection cn = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    @Override
    public List<SalesDto> getSales() {
        List<SalesDto> data = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SalesDto sale = new SalesDto();
                sale.setId(rs.getInt("id"));
                sale.setDes_prod(rs.getString("des_prod"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUni_price(rs.getFloat("uni_price"));
                sale.setTotal(rs.getFloat("total"));

                data.add(sale);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return data;
    }

    @Override
    public int validateExistSale(SalesDto sale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addSale(SalesDto sale) {
        String sql = "INSERT INTO sales(des_prod, quantity, uni_price, total) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sale.getDes_prod());
            ps.setInt(2, sale.getQuantity());
            ps.setFloat(3, sale.getUni_price());
            ps.setFloat(4, sale.getTotal());
            r = ps.executeUpdate();
            
            if(r > 0) return 1;
            else return 0;
             
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int editSale(SalesDto sale) {
        String sql = "UPDATE sales SET des_prod = ?, quantity = ?, uni_price = ?, total = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sale.getDes_prod());
            ps.setInt(2, sale.getQuantity());
            ps.setFloat(3, sale.getUni_price());
            ps.setFloat(4, sale.getTotal());
            ps.setInt(5, sale.getId());
            r = ps.executeUpdate();
            
            if(r > 0)return 1;
            else return 0;
             
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public SalesDto findSale(int id) {
        SalesDto sale = new SalesDto();
        String sql = "SELECT * FROM sales WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                sale.setId(rs.getInt("id"));
                sale.setDes_prod(rs.getString("des_prod"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUni_price(rs.getFloat("uni_price"));
                sale.setTotal(rs.getFloat("total"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return sale;
    }
    
}
