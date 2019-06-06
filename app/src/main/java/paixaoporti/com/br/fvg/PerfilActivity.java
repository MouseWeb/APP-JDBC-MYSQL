package paixaoporti.com.br.fvg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import controller.PerfilControle;
import impl.PerfilDAO;

public class PerfilActivity extends AppCompatActivity {

    private static final String PREF_NAME = "LoginActivityPreferences";

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText usuario;
    private EditText senhaPerfil;
    private ProgressBar progress;

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
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String login = sp.getString("login", "");
        String senha = sp.getString("senha", "");

        if ( temConexao(PerfilActivity.this) == false ) {
            mostraAlerta();
            return;
        } else {
            dao.userFindById ( login, senha );

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

    public void updatePerfil(View view){

        if ( temConexao(PerfilActivity.this) == false ) {
            mostraAlerta();
            return;
        } else if (validacao() == false){
            return;
        } else {
            progress.setVisibility(View.VISIBLE);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    PerfilControle p = new PerfilControle ( );

                    SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

                    String login = sp.getString("login", "");
                    String senha = sp.getString("senha", "");

                    dao.userFindById ( login, senha );

                    p.setNome ( nome.getText ().toString () );
                    p.setEmail ( email.getText ().toString () );
                    p.setTelefone ( telefone.getText ().toString () );
                    p.setLogin ( usuario.getText ().toString () );
                    p.setSenha ( senhaPerfil.getText ().toString () );
                    dao.updatePerfil ( p );

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            exibeTela();
                        }
                    });
                }
            }).start();

        }

    }

    public boolean validacao() {

        String userNameGet = nome.getText ().toString ();
        String userEmailGet = email.getText ().toString ();
        String userLoginGet = usuario.getText ().toString ();
        String userSenhaGet = senhaPerfil.getText ().toString ();

        if ( userNameGet == null ||  userNameGet.equals ( "" ) ){
            nome.setError( "Campo Obrigatorio!" );
            return false;
        } else if( userEmailGet == null || userEmailGet.equals ( "" ) ){
            email.setError ( "Campo Obrigatorio!" );
            return false;
        } else if( userLoginGet == null || userLoginGet.equals ( "" ) ){
            usuario.setError ( "Campo Obrigatorio!" );
            return false;
        } else if( userSenhaGet == null || userSenhaGet.equals ( "" ) ){
            senhaPerfil.setError ( "Campo Obrigatorio!" );
            return false;
        } else {
            return true;
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

    private void exibeTela() {
        progress.setVisibility(View.GONE);
        Toast.makeText ( PerfilActivity.this, "Salvo com sucesso!!!", Toast.LENGTH_SHORT ).show ( );
    }

    public void voltaHome(View view){
        Intent f = new Intent(PerfilActivity.this,MainActivity.class);
        startActivity(f);
    }

}
