package model;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Controller.NovoRelatoControle;
import connection.JDBCconnection;

public class NovoRelatoDAO {

    public void create(NovoRelatoControle n) {

        Connection con = JDBCconnection.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbl_relatos" +
                    "(rel_nome,rel_dosagem,rel_qd_med_ini,rel_qd_med_inter,rel_gravidade,rel_descricao)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, n.getMedicaemnto ());
            stmt.setString ( 2, n.getDosagem ());
            stmt.setString ( 3, n.getDataInicio ());
            stmt.setString ( 4, n.getDataTermino ());
            stmt.setString ( 5, n.getGravidade ());
            stmt.setString ( 6, n.getDescricao ());

            stmt.executeUpdate();

            Log.e("BANCO", "Salvo com sucesso!");
        } catch (SQLException e) {
            Log.e("BANCO", "Erro ao salvar!" + e);
        } finally {
            JDBCconnection.closeConnection(con, stmt);
        }

    }
}
