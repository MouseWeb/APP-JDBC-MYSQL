package impl;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.LoginControle;
import Controller.PerfilControle;
import connection.JDBCconnection;
import model.PerfilInterface;

public class PerfilDAO implements PerfilInterface {

    LoginControle c = new LoginControle ( );

    @Override
    public void buscarPerfil(String login, String senha) {

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
    public void update(PerfilControle obj) {

        Connection con = JDBCconnection.getConnection ( );
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement  ( "UPDATE department " +
                                                "SET Name = ? " +
                                                "WHERE Id = ?" );

            stmt.setInt ( 1, c.getId () );

            stmt.executeUpdate ( );

            Log.e("PERFIL: ", "Update Perfil Sucesso!");
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
                System.out.println ( obj );
            }
            Log.e("PERFIL: ", "Lista Perfil Sucesso!");

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

