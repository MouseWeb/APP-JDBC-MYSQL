package impl;

import android.util.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.CadastroControle;
import connection.JDBCconnection;
import model.CadastroInterface;
import model.PerfilInterface;

public class CadastroDAO implements CadastroInterface {

    @Override
    public boolean checkLogin(String loginC, String senhaC) {

        Connection con = JDBCconnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM tbl_usuarios WHERE usu_login = ? and usu_senha = ?");
            stmt.setString(1, loginC);
            stmt.setString(2, senhaC);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(LoginDAO.class.getName()).log( Level.SEVERE, null, ex.getMessage());
            Log.e("CADASTRO", "Erro ao consulta se já existe! " + ex.getMessage());
        } finally {
            JDBCconnection.closeConnection(con, stmt, rs);
        }

        return check;

    }

    @Override
    public void create(CadastroControle c) {

        Connection con = JDBCconnection.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(" INSERT INTO tbl_usuarios (usu_nome,usu_login,usu_senha,usu_nivel,usu_email) "
                    + " VALUES (?,?,?,?,?) ");
            stmt.setString( 1, c.getNome());
            stmt.setString( 2, c.getUser());
            stmt.setString( 3, c.getSenha());
            stmt.setString( 4, c.getNivel());
            stmt.setString( 5, c.getEmail());

            stmt.executeUpdate();

            Log.e("CADASTRO: ", "Salvo com sucesso!");
        } catch (SQLException e) {
            Log.e("CADASTRO", "Erro ao salvar! " + e.getMessage());
        } finally {
            JDBCconnection.closeConnection(con, stmt);
        }

    }

}