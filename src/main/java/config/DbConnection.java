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
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://n0t3efvnqcbexytp:iq4x2a61w2yzg046@o2olb7w3xv09alub.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/gxtwx29qqa7qat9w?useSSL=false", "n0t3efvnqcbexytp", "iq4x2a61w2yzg046");
            
        } catch (Exception e) {
        }
        return con;
    }
    
}
