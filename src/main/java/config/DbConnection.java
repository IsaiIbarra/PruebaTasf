/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Isai - HP
 */
public class DbConnection {
    Connection con;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://n0t3efvnqcbexytp:footy9cmy3lx33ut@o2olb7w3xv09alub.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/gxtwx29qqa7qat9w?useSSL=false", "n0t3efvnqcbexytp", "footy9cmy3lx33ut");
            
        } catch (Exception e) {
        }
        return con;
    }
    
}
