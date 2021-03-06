package impl;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.LoginControle;
import controller.PerfilControle;
import connection.JDBCconnection;
import model.PerfilInterface;

public class PerfilDAO implements PerfilInterface {

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

        getListaPerfil();

    }

    @Override
    public void updatePerfil(PerfilControle obj) {

        Connection con = JDBCconnection.getConnection ( );
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement  ( "UPDATE tbl_usuarios "
                                                + " SET usu_nome = ? , "
                                                + " usu_login = ? , "
                                                + " usu_senha = ? , "
                                                + " usu_telefone = ? , "
                                                + " usu_email = ? "
                                                + " WHERE idusuario = ? " );

            stmt.setString ( 1, obj.getNome ());
            stmt.setString ( 2, obj.getLogin ());
            stmt.setString ( 3, obj.getSenha ());
            stmt.setString ( 4, obj.getTelefone ());
            stmt.setString ( 5, obj.getEmail ());
            stmt.setInt ( 6, c.getId () );

            stmt.executeUpdate ( );

           // Log.e("PERFIL: ", "Update Perfil Sucesso!");
        }catch (SQLException e) {
            Log.e ( "BANCO (SQLException): ", e.getMessage ( ) );
        } catch (Exception e) {
            Log.e ( "BANCO (Exception); ", e.getMessage ( ) );
        } finally {
            JDBCconnection.closeConnection ( con, stmt, rs );
        }

    }

    @Override
    public List <PerfilControle> getListaPerfil() {

        Connection con = JDBCconnection.getConnection ( );
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <PerfilControle> list = null;
        try {
            list = new ArrayList <> ( );

            stmt = con.prepareStatement ( " SELECT tbl_usuarios.usu_nome, "
                                           + "        tbl_usuarios.usu_email, "
                                           + "        tbl_usuarios.usu_telefone, "
                                           + "        tbl_usuarios.usu_login, "
                                           + "        tbl_usuarios.usu_senha "
                                           + "  FROM tbl_usuarios "
                                           + "  WHERE tbl_usuarios.idusuario = ? ");

            stmt.setInt ( 1, c.getId () );

            rs = stmt.executeQuery ( );

            while (rs.next ( )) {
                PerfilControle obj = new PerfilControle ( );
                obj.setNome ( rs.getString ( "usu_nome" ) );
                obj.setEmail ( rs.getString ( "usu_email" ) );
                obj.setTelefone ( rs.getString ( "usu_telefone" ) );
                obj.setLogin ( rs.getString ( "usu_login" ) );
                obj.setSenha ( rs.getString ( "usu_senha" ) );

                list.add ( obj );
                //System.out.println ( obj );
            }
           // Log.e("PERFIL: ", "Lista Perfil Sucesso!");
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

