package paixaoporti.com.br.fvg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import model.LoginDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button logar;
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

        logar.setOnClickListener ( this );
        cadastro.setOnClickListener ( this );

    }

    public void salvarNovoRelato() {

        LoginDAO dao = new LoginDAO ();


        if(dao.checkLogin(login.getText().toString (), senha.getText().toString ())){

             ProgressDialog.show ( LoginActivity.this, "Aguarde",
                    "Por Favor Aguarde..." );

            Intent f = new Intent(this,MainActivity.class);
            startActivity(f);
            Toast.makeText ( LoginActivity.this, "Logado com sucesso!!!", Toast.LENGTH_SHORT ).show ( );

            finish ();

        }else{

            Toast.makeText ( LoginActivity.this, "Senha incorreta!!!", Toast.LENGTH_SHORT ).show ( );

        }

    }

    public void validacao(){

        String userNameGet = login.getText ().toString ();
        String userPassGet = senha.getText ().toString ();

        if ( userNameGet == null ||  userNameGet.equals ( "" ) ){

            login.setError( "Campo Obrigatorio!" );

        } else if( userPassGet == null || userPassGet.equals ( "" ) ){

            senha.setError ( "Campo Obrigatorio!" );

        } else {

            salvarNovoRelato( );
            limparCampos();
        }
    }

    public void limparCampos() {
        login.setText ( "" );
        senha.setText ( "" );
        this.codigo = 0;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.loginEntrar ) {

            validacao();

        }else if(view.getId () == R.id.cadastroUser ){

            Intent f = new Intent(this,CadastroActivity.class);
            startActivity(f);

        }else{
            Toast.makeText ( LoginActivity.this, "Erro ao salvar Relato!", Toast.LENGTH_SHORT ).show ( );
        }

    }
}
