package paixaoporti.com.br.fvg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Controller.PerfilControle;
import impl.PerfilDAO;

public class PerfilActivity extends AppCompatActivity {

    private static final String PREF_NAME = "LoginActivityPreferences";

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText usuario;
    private EditText senhaPerfil;

    PerfilDAO dao = new PerfilDAO ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_perfil );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Perfil Notificador");     //Titulo para ser exibido na sua Action Bar em frente à seta

        nome  = (EditText) findViewById(R.id.txtnome);
        email = (EditText) findViewById(R.id.txtemail);
        telefone = (EditText) findViewById(R.id.txttelefone);
        usuario = (EditText) findViewById(R.id.txtusuario);
        senhaPerfil = (EditText) findViewById(R.id.txtsenha);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String login = sp.getString("login", "");
        String senha = sp.getString("senha", "");

        if ( temConexao(PerfilActivity.this) == false ) {
            mostraAlerta();
            return;
        } else {
            dao.buscarPerfil ( login, senha );

            List<PerfilControle> list = dao.getListaPerfil ();

            for( PerfilControle dados : list ) {
                 nome.setText ( dados.getNome () );
                 email.setText ( dados.getEmail () );
                 telefone.setText ( dados.getTelefone () );
                 usuario.setText ( dados.getLogin () );
                 senhaPerfil.setText ( dados.getSenha () );

            }

        }

    }

    private boolean temConexao(Context classe) {
        ConnectivityManager gerenciador = (ConnectivityManager) classe.getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo informacao = gerenciador.getActiveNetworkInfo();
        if ((informacao != null) && (informacao.isConnectedOrConnecting()) && (informacao.isAvailable())) {
            return true;
        }
        return false;
    }

    private void mostraAlerta() {
        AlertDialog.Builder informa = new AlertDialog.Builder(PerfilActivity.this);
        informa.setMessage("Sem conexão com a internet!");
        informa.setNeutralButton("Voltar", null).show();
        showSettingsAlert();
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Sem conexão com a internet!");
        alertDialog.setMessage("Verifique a conexão com a internet!");
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent( Settings.ACTION_WIRELESS_SETTINGS );
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

}
