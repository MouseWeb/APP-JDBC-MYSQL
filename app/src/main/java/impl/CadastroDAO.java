package impl;

import android.util.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.CadastroControle;
import connection.JDBCconnection;
import model.CadastroInterface;

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

           // Log.e("CADASTRO: ", "Salvo com sucesso!");
        } catch (SQLException e) {
            Log.e("CADASTRO", "Erro ao salvar! " + e.getMessage());
        } finally {
            JDBCconnection.closeConnection(con, stmt);
        }

    }

}