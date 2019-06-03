package impl;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ListarRelatoControle;
import controller.LoginControle;
import connection.JDBCconnection;
import model.ListarRelatoInterface;

public class ListarRelatoDAO implements ListarRelatoInterface {

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

        getListaRelato();

    }

    @Override
    public List <ListarRelatoControle> getListaRelato() {

        Connection con = JDBCconnection.getConnection ( );
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List <ListarRelatoControle> list = null;
        try {
            list = new ArrayList <> ( );

            stmt = con.prepareStatement ( " SELECT tbl_relatos.rel_nome, tbl_relatos.rel_dosagem, tbl_relatos.rel_qd_med_ini, tbl_relatos.rel_qd_med_inter, "
                                         +   "       tbl_relatos.rel_descricao, tbl_relatos.rel_gravidade "
                                         +   " FROM "
                                         +   " tbl_usuarios "
                                         +   " INNER JOIN tbl_relatos ON tbl_usuarios.idusuario = tbl_relatos.idusuario "
                                         +   " WHERE "
                                         +   " tbl_usuarios.idusuario = ?" );

            stmt.setInt ( 1, c.getId () );

            rs = stmt.executeQuery ( );

            while (rs.next ( )) {
                ListarRelatoControle obj = new ListarRelatoControle ( );
                obj.setMedicaemnto ( rs.getString ( "rel_nome" ) );
                obj.setDosagem ( rs.getString ( "rel_dosagem" ) );
                obj.setDataInicio ( rs.getString ( "rel_qd_med_ini" ) );
                obj.setDataTermino ( rs.getString ( "rel_qd_med_inter" ) );
                obj.setDescricao ( rs.getString ( "rel_descricao" ) );
                obj.setGravidade ( rs.getString ( "rel_gravidade" ) );
                list.add ( obj );
                //System.out.println ( obj );
            }
           // Log.e("LISTA: ", "Lista Sucesso!");
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