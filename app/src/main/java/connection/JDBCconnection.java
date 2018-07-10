package connection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JDBCconnection {

    //Método de Conexão//
    public static Connection getConnection() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection cn = null;
        try {
            String banco = "mouseinformati05";
            String url = "jdbc:mysql://mysql.mouseinformatica.com.br/" + banco;
            String usuario = "mouseinformati05";
            String senha = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(url, usuario, senha);

            Log.e("BANCO", "Conexão Aberta");
        } catch (SQLException e) {
            Log.e("BANCO", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("BANCO", e.getMessage());
        } catch (Exception e) {
            Log.e("BANCO", e.getMessage());
        }
        return cn;
    }


    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}