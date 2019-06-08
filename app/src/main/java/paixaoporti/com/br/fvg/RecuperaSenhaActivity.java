package paixaoporti.com.br.fvg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import util.GerarSenha;

public class RecuperaSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_recuparar_senha );

    }

    public void recSenhaUser(View view){
        String r = (String) GerarSenha.newPassword ();
        Toast.makeText(RecuperaSenhaActivity.this, r, Toast.LENGTH_SHORT).show();
        System.out.println(r);

        /*Intent f = new Intent( RecuperaSenhaActivity.this, LoginActivity.class);
        startActivity(f);*/
    }

    public void irParaLogin(View view){
        Intent f = new Intent( RecuperaSenhaActivity.this, LoginActivity.class);
        startActivity(f);
        finish ();
    }

}
