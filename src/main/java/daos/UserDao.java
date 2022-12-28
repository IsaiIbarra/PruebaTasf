
package daos;

import config.DbConnection;
import dtos.UserDto;
import interfaces.IUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao implements IUser{
    Connection con;
    DbConnection cn = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    int flag = 0;
    @Override
    public int validate(UserDto user) {
        String sql = "select * from users where username = ? and password = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                flag = 1;
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            System.out.println("RRRSSSSSSS");
            System.out.println(rs.next());
            
            if(flag == 1){
                flag = 0;
                return 1;
            }
            else{
                return 0;
            }
             
        } catch (Exception e) {
            return 0;
        }
    }
    
}
