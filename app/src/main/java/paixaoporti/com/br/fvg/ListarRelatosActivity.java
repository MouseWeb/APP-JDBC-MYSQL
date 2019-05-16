package paixaoporti.com.br.fvg;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import controller.ListarRelatoControle;
import impl.ListarRelatoDAO;

public class ListarRelatosActivity extends AppCompatActivity {

    private static final String PREF_NAME = "LoginActivityPreferences";
    private ListView listaRelatos;
    ListarRelatoDAO dao = new ListarRelatoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_listar_relatos );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Lista de Relatos");    //Titulo para ser exibido na sua Action Bar em frente à seta

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String login = sp.getString("login", "");
        String senha = sp.getString("senha", "");

        if ( temConexao(ListarRelatosActivity.this) == false ) {
            mostraAlerta();
            return;
        } else {
            dao.userFindById ( login, senha );
        }

        // lista
        listaRelatos = (ListView) findViewById(R.id.listadeEventosCadastrados);

        List<ListarRelatoControle> list = dao.getListaRelato ();

        ArrayAdapter<ListarRelatoControle> adapter = new ArrayAdapter<ListarRelatoControle>(
                this, android.R.layout.simple_list_item_1, list);

        listaRelatos.setAdapter(adapter);

        listaRelatos.setOnItemClickListener(new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicou no item " + position, Toast.LENGTH_LONG).show();
               /* Intent f = new Intent(ListarRelatosActivity.this,RelatoListDetalhe.class);
                startActivity(f);*/
            }
        });

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
        AlertDialog.Builder informa = new AlertDialog.Builder(ListarRelatosActivity.this);
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
