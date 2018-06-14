package paixaoporti.com.br.fvg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListarRelatosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_listar_relatos );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Lista de Relatos");     //Titulo para ser exibido na sua Action Bar em frente à seta


    }

}
