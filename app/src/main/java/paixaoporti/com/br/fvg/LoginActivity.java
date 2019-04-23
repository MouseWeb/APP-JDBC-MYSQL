package paixaoporti.com.br.fvg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.widget.CheckBox;

import impl.LoginDAO;
import model.LoginInterface;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PREF_NAME = "LoginActivityPreferences";
    private CheckedTextView cadastro;

    LoginInterface loginInterface = new LoginDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        cadastro = (CheckedTextView) findViewById(R.id.cadastroUser);
        cadastro.setOnClickListener (this);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String login = sp.getString("login", "");
        String senha = sp.getString("senha", "");

        if(loginInterface.checkLogin(login, senha)){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }

    }

    public void signIn(View view){

        if ( temConexao(LoginActivity.this) == false ) {
            mostraAlerta();
            return;
        }

        EditText etLogin = (EditText) findViewById(R.id.login);
        EditText etPassword = (EditText) findViewById(R.id.senha);

        String login = etLogin.getText().toString();
        String senha = etPassword.getText().toString();

        if(loginInterface.checkLogin(login, senha)){

                SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("login", login);
                editor.putString("senha", senha);
                editor.commit();

            if ( sp.getString("login", "") != null || sp.getString("login", "") != ""
                    && sp.getString("senha", "") != null || sp.getString("senha", "") != "") {

                ProgressDialog.show ( LoginActivity.this, "Aguarde",
                        "Por Favor Aguarde..." );
            }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(LoginActivity.this, "Senha incorreta!!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {

         if (view.getId () == R.id.cadastroUser ){
            Intent f = new Intent(this,CadastroActivity.class);
            startActivity(f);

        }

    }

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
