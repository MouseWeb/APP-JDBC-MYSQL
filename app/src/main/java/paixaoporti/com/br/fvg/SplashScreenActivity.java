package paixaoporti.com.br.fvg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 600;
    private static int SPLASH_TIME_NETWORK = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (temConexao(SplashScreenActivity.this) == false) {
                    mostraAlerta();
                    onRestart();
                } else if (temConexao(SplashScreenActivity.this)){
                    Intent trocatela = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    SplashScreenActivity.this.startActivity(trocatela);
                    SplashScreenActivity.this.finish();

                }

//                Intent I = new Intent(SplashScreenActivity.this, LoginActivity.class);
//                startActivity(I);
//                finish();

            }
        },SPLASH_TIME_OUT);
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
        AlertDialog.Builder informa = new AlertDialog.Builder(SplashScreenActivity.this);
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
    protected void onRestart() {

        super.onRestart ( );
        new Handler ( ).postDelayed ( new Runnable ( ) {
            @Override
            public void run() {
                Intent i = new Intent ( SplashScreenActivity.this, SplashScreenActivity.class );  //your class
                startActivity ( i );
                finish ( );
            }
        }, SPLASH_TIME_NETWORK );

    }
}
