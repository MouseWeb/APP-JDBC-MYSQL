package paixaoporti.com.br.fvg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controller.CadastroControle;
import model.CadastroDAO;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btSalvarCadastro;
    private EditText nomeCadastro, userCadastro, senhaCadastro;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cadastro );

        nomeCadastro = (EditText) findViewById(R.id.nameCadastro);
        userCadastro = (EditText) findViewById(R.id.novo_user_nome);
        senhaCadastro = (EditText) findViewById(R.id.novo_user_senha);

        btSalvarCadastro = (Button) findViewById(R.id.cadastra_user);

        btSalvarCadastro.setOnClickListener(this);

    }

    public void salvarCadastro() {

        CadastroControle c = new CadastroControle ();
        CadastroDAO dao = new CadastroDAO ();

        c.setNome ( nomeCadastro.getText().toString () );
        c.setUser ( userCadastro.getText ().toString () );
        c.setSenha ( senhaCadastro.getText ().toString () );
        dao.create ( c );

        limparCampos();
    }

    public void validacao(){

        CadastroDAO dao = new CadastroDAO ();

        String nomeCadastroGet  = nomeCadastro.getText ().toString ();
        String userCadastroGet  = userCadastro.getText ().toString ();
        String senhaCadastroGet = senhaCadastro.getText ().toString ();

        if ( nomeCadastroGet == null ||  nomeCadastroGet.equals ( "" ) ){

            nomeCadastro.setError( "Campo Obrigatorio!" );

        } else if( userCadastroGet == null || userCadastroGet.equals ( "" ) ){

            userCadastro.setError ( "Campo Obrigatorio!" );

        } else if( senhaCadastroGet == null || senhaCadastroGet.equals ( "" ) ) {

            senhaCadastro.setError ( "Campo Obrigatorio!" );

        }else if(dao.checkLogin(userCadastro.getText().toString (), senhaCadastro.getText().toString ())){

            Toast.makeText ( CadastroActivity.this, "Usuário já existe!!!", Toast.LENGTH_SHORT ).show ( );
            limparCampos ();

        } else {

            Intent f = new Intent(CadastroActivity.this,LoginActivity.class);
            startActivity(f);
            salvarCadastro ();
            limparCampos ();

            Toast.makeText(CadastroActivity.this, "Cadastro Salvo com sucesso!!!", Toast.LENGTH_SHORT).show();

            finish ();

        }
    }

    public void limparCampos() {
        nomeCadastro.setText("");
        userCadastro.setText("");
        senhaCadastro.setText("");
        this.codigo = 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cadastra_user) {

            validacao();

        }

    }
}
