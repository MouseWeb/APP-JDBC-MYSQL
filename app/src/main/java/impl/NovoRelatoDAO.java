package impl;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.LoginControle;
import controller.NovoRelatoControle;
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

    @Override
    public List <NovoRelatoControle> getMailUser() {

        Connection con = JDBCconnection.getConnection ( );
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <NovoRelatoControle> list = null;
        try {
            list = new ArrayList <> ( );

            stmt = con.prepareStatement ( " SELECT tbl_usuarios.usu_email "
                    + "  FROM tbl_usuarios "
                    + "  WHERE tbl_usuarios.idusuario = ? ");

            stmt.setInt ( 1, c.getId () );

            rs = stmt.executeQuery ( );

            while (rs.next ( )) {
                NovoRelatoControle obj = new NovoRelatoControle ( );
                obj.setEmail ( rs.getString ( "usu_email" ) );

                list.add ( obj );
                //System.out.println ( obj );
            }
            // Log.e("PERFIL: ", "Lista email user Menu Sucesso!");
        } catch (SQLException e) {
            Log.e ( "BANCO (SQLException): ", e.getMessage ( ) );
        } catch (Exception e) {
            Log.e ( "BANCO (Exception); ", e.getMessage ( ) );
        } finally {
            JDBCconnection.closeConnection ( con, stmt, rs );
        }
        return list;
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

           // Log.e("BANCO", "Salvo com sucesso!");
        } catch (SQLException e) {
            Log.e("BANCO", "Erro ao salvar!" + e);
        } finally {
            JDBCconnection.closeConnection(con, stmt);
        }

    }

}
