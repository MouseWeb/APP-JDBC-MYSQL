package paixaoporti.com.br.fvg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_perfil );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Perfil Notificador");     //Titulo para ser exibido na sua Action Bar em frente à seta

    }
}
