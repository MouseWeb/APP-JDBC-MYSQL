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

    //Método de Conexão
    public static Connection getConnection() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection cn = null;
        try {
            String banco = "mouseweb01";
            String url = "jdbc:mysql://mysql.mouseweb.com.br/" + banco;
            String usuario = "mouseweb01";
            String senha = "MOUSE456";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(url, usuario, senha);

            Log.e("BANCO: ", "Conexão Aberta");
        } catch (SQLException e) {
            throw new DbException("BANCO (SQLException): " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new DbIntegrityException ("BANCO (NumberFormatException): " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new DbException("BANCO (IllegalAccessException): " + e.getMessage());
        } catch (Exception e) {
            throw new DbException("BANCO (Exception): " + e.getMessage());
        }
        return cn;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                Log.e("BANCO: ", "Conexão Fechada (con)");
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
                Log.e("BANCO: ", "Conexão Fechada (con) + (stmt)");
            }

        } catch (SQLException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
                Log.e("BANCO: ", "Conexão Fechada (con) + (stmt) + (rs)");
            }

        } catch (SQLException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }

    }

}