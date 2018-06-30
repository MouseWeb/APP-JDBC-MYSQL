package paixaoporti.com.br.fvg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import model.LoginDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button logar;
    private Animation animation;
    private CheckedTextView cadastro;
    private EditText login;
    private EditText senha;
    private int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        logar    = (Button) findViewById(R.id.loginEntrar);
        cadastro = (CheckedTextView) findViewById(R.id.cadastroUser);
        login    = (EditText) findViewById ( R.id.login );
        senha    = (EditText) findViewById ( R.id.senha );

        animation = new AlphaAnimation ( 1, 0 ); // Altera alpha de visível a invisível
        animation.setDuration(1500); // duração - meio segundo
        animation.setInterpolator(new LinearInterpolator () );
        animation.setRepeatCount( Animation.ZORDER_BOTTOM ); // Repetir infinitamente
        animation.setRepeatMode( Animation.REVERSE ); //Inverte a animação no final para que o botão vá desaparecendo
        logar.startAnimation( animation );

        logar.setOnClickListener ( this );
        cadastro.setOnClickListener ( this );

    }

    public void salvarNovoRelato() {

        LoginDAO dao = new LoginDAO ();

        if(dao.checkLogin(login.getText().toString (), senha.getText().toString ())){

            Intent f = new Intent(this,MainActivity.class);
            ProgressDialog.show ( LoginActivity.this, "Aguarde",
                    "Por Favor Aguarde..." );
            startActivity(f);

            Toast.makeText ( LoginActivity.this, "Logado com sucesso!!!", Toast.LENGTH_SHORT ).show ( );

//            limparCampos();
            finish ();

        }else{

            Toast.makeText ( LoginActivity.this, "Senha incorreta!!!", Toast.LENGTH_SHORT ).show ( );

        }

    }

    public void validacao(){

        String userNameGet = login.getText ().toString ();
        String userPassGet = senha.getText ().toString ();

        if ( temConexao(LoginActivity.this) == false ) {

            mostraAlerta();

        } else if ( userNameGet == null ||  userNameGet.equals ( "" ) ){

            login.setError( "Campo Obrigatorio!" );

        } else if( userPassGet == null || userPassGet.equals ( "" ) ){

            senha.setError ( "Campo Obrigatorio!" );

        } else {

            salvarNovoRelato( );
        }
    }

    public void limparCampos() {
        login.setText ( "" );
        senha.setText ( "" );
        this.codigo = 0;
    }

    // Se precisar desse método pra mais de uma classe, mude ele pra ser estático.
    private boolean temConexao(Context classe) {
        //Pego a conectividade do contexto passado como argumento
        ConnectivityManager gerenciador = (ConnectivityManager) classe.getSystemService( Context.CONNECTIVITY_SERVICE);
        //Crio a variável informacao que recebe as informações da Rede
        NetworkInfo informacao = gerenciador.getActiveNetworkInfo();
        //Se o objeto for nulo ou nao tem conectividade retorna false
        if ((informacao != null) && (informacao.isConnectedOrConnecting()) && (informacao.isAvailable())) {
            return true;
        }
        return false;
    }

    // Mostra a informação caso não tenha internet.
    private void mostraAlerta() {
        AlertDialog.Builder informa = new AlertDialog.Builder(LoginActivity.this);
//        informa.setMessage("Sem conexão com a internet.");
//        informa.setNeutralButton("Voltar", null).show();
        showSettingsAlert();
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Internet desativado!");
        alertDialog.setMessage("Ativar Internet?");
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent( Settings.ACTION_NETWORK_OPERATOR_SETTINGS );
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

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.loginEntrar ) {

            validacao ();

        }else if(view.getId () == R.id.cadastroUser ){

            Intent f = new Intent(this,CadastroActivity.class);
            startActivity(f);

        }else{
            Toast.makeText ( LoginActivity.this, "Erro ao salvar Relato!", Toast.LENGTH_SHORT ).show ( );
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy ( );
    }
}
