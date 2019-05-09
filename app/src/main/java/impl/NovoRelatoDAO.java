package impl;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controller.LoginControle;
import Controller.NovoRelatoControle;
import connection.JDBCconnection;
import model.NovoRelatoInterface;

public class NovoRelatoDAO implements NovoRelatoInterface {

    LoginControle c = new LoginControle ( );

    @Override
    public void userFindById(String login, String senha) {

        Connection con = JDBCconnection.getConnection ( );

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement ( "SELECT * FROM tbl_usuarios WHERE usu_login = ? and usu_senha = ?" );
            stmt.setString ( 1, login );
            stmt.setString ( 2, senha );

            rs = stmt.executeQuery ( );

            if (rs.next ()){
                c.setId ( rs.getInt ( "idusuario" ) );
            }

        } catch (SQLException e) {
            Log.e ( "BANCO", e.getMessage ( ) );
        } catch (Exception e) {
            Log.e ( "BANCO", e.getMessage ( ) );
        } finally {
            JDBCconnection.closeConnection ( con, stmt, rs );
        }

    }

    public void insertRelato(NovoRelatoControle n) {

        Connection con = JDBCconnection.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(" INSERT INTO tbl_relatos "
                                         + " ( rel_nome, rel_dosagem, rel_qd_med_ini, rel_qd_med_inter, "
                                         + " rel_gravidade, rel_descricao, idusuario) "
                                         + " VALUES (?,?,?,?,?,?,?) ");

            stmt.setString ( 1, n.getMedicaemnto ());
            stmt.setString ( 2, n.getDosagem ());
            stmt.setString ( 3, n.getDataInicio ());
            stmt.setString ( 4, n.getDataTermino ());
            stmt.setString ( 5, n.getGravidade ());
            stmt.setString ( 6, n.getDescricao ());
            stmt.setInt ( 7, c.getId () );

            stmt.executeUpdate();

            Log.e("BANCO", "Salvo com sucesso!");
        } catch (SQLException e) {
            Log.e("BANCO", "Erro ao salvar!" + e);
        } finally {
            JDBCconnection.closeConnection(con, stmt);
        }

    }

}
