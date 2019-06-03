package impl;
import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.LoginControle;
import connection.JDBCconnection;
import model.LoginInterface;

public class LoginDAO implements LoginInterface {

    @Override
    public boolean checkLogin(String login, String senha) {
        Connection con = JDBCconnection.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM tbl_usuarios WHERE usu_login = ? and usu_senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;

                LoginControle c = new LoginControle ();
                c.setId ( rs.getInt("idusuario") );

                //System.out.print( c.getId () );

            }
           // Log.e("LOGIN: ", "Login Sucesso!");
        } catch (SQLException e) {
            Log.e("BANCO (SQLException): ", e.getMessage());
        } catch (Exception e) {
            Log.e("BANCO (Exception): ", e.getMessage());
        }
        finally {
            JDBCconnection.closeConnection(con, stmt, rs);
        }

        return check;
    }

}
