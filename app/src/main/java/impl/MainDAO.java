package impl;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCconnection;
import controller.LoginControle;
import controller.MainControle;
import model.MainInterface;

public class MainDAO implements MainInterface {

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

        getPerfilMenu();

    }

    @Override
    public List <MainControle> getPerfilMenu() {

        Connection con = JDBCconnection.getConnection ( );
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <MainControle> list = null;
        try {
            list = new ArrayList <> ( );

            stmt = con.prepareStatement ( " SELECT tbl_usuarios.usu_nome, "
                    + "        tbl_usuarios.usu_email "
                    + "  FROM tbl_usuarios "
                    + "  WHERE tbl_usuarios.idusuario = ? ");

            stmt.setInt ( 1, c.getId () );

            rs = stmt.executeQuery ( );

            while (rs.next ( )) {
                MainControle obj = new MainControle ( );
                obj.setNome ( rs.getString ( "usu_nome" ) );
                obj.setEmail ( rs.getString ( "usu_email" ) );

                list.add ( obj );
                System.out.println ( obj );
            }
            Log.e("PERFIL: ", "Lista Perfil Menu Sucesso!");
        } catch (SQLException e) {
            Log.e ( "BANCO (SQLException): ", e.getMessage ( ) );
        } catch (Exception e) {
            Log.e ( "BANCO (Exception); ", e.getMessage ( ) );
        } finally {
            JDBCconnection.closeConnection ( con, stmt, rs );
        }
        return list;
    }
}
