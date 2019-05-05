package paixaoporti.com.br.fvg;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AjudaActivity extends AppCompatActivity {

    private ImageView telefone;
    private TextView telefoneCadastro;
    private TextView emailCadastro;
    private ImageView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ajuda );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Ajuda entre em contato");     //Titulo para ser exibido na sua Action Bar em frente à seta

        telefoneCadastro  = (TextView) findViewById ( R.id.telefoneCadastro );
        telefone          = (ImageView) findViewById ( R.id.telefone );
        email             = (ImageView) findViewById ( R.id.email );
        emailCadastro     = (TextView) findViewById ( R.id.emailCadastro );

        telefone.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("tel:0800 8008-4004");
                Intent itNavegar = new Intent( Intent.ACTION_DIAL,uri);
                startActivity(itNavegar);

            }
        } );

        telefoneCadastro.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("tel:0800 8008-4004");
                Intent itNavegar = new Intent( Intent.ACTION_DIAL,uri);
                startActivity(itNavegar);

            }
        } );

        email.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("mailto:projeto@mouseweb.com.br");
                Intent itNavegar = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(itNavegar);

            }
        } );

        emailCadastro.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("mailto:projeto@mouseweb.com.br");
                Intent itNavegar = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(itNavegar);

            }
        } );

    }

}
