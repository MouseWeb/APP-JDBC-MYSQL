package paixaoporti.com.br.fvg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import controller.MainControle;
import impl.MainDAO;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView novoRelato, listarRelato, hospitaisRelato;

    MainDAO dao = new MainDAO ();

    private static final String PREF_NAME = "MainActivityPreferences";
    private static final String PREF_NENU = "LoginActivityPreferences";
    private int count1;

    private SharedPreferences.OnSharedPreferenceChangeListener callback = new SharedPreferences.OnSharedPreferenceChangeListener(){
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.i("Script", key+" updated");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        novoRelato      = (ImageView) findViewById(R.id.novoRelato);
        listarRelato    = (ImageView) findViewById(R.id.listarRelato);
        hospitaisRelato = (ImageView) findViewById(R.id.hospitais);

        // Obtém a referência do layout de navegação
        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        // Obtém a referência da view de cabeçalho
        View headerView = navigation.getHeaderView(0);

        // Obtém a referência do nome, email
        TextView nome = (TextView) headerView.findViewById(R.id.textNome);
        TextView email = (TextView) headerView.findViewById(R.id.textMail);

        SharedPreferences sp = getSharedPreferences(PREF_NENU, MODE_PRIVATE);

        String login = sp.getString("login", "");
        String senha = sp.getString("senha", "");

        if ( temConexao(MainActivity.this) == false ) {
            mostraAlerta();
            return;
        } else {
            dao.userFindById ( login, senha );

            List <MainControle> list = dao.getPerfilMenu ();

            for( MainControle d : list ) {
                //System.out.println ( d.getNome () );
                //System.out.println ( d.getEmail () );
                nome.setText ( d.getNome () );
                email.setText ( d.getEmail () );

            }

        }

        novoRelato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent f = new Intent(MainActivity.this,NovoRelatoActivity.class);
                startActivity(f);
            }

        });

        listarRelato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent f = new Intent(MainActivity.this,ListarRelatosActivity.class);
                startActivity(f);
            }

        });

        hospitaisRelato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent f = new Intent(MainActivity.this,GoogleApiMaps.class);
                startActivity(f);
            }

        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Dúvida ou sugestões: 0800 8000-4004", Snackbar.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        count1 = sp1.getInt("count_1", 0);
        Log.i("Script", "COUNT 1: "+count1);
        sp1.registerOnSharedPreferenceChangeListener(callback);

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
        AlertDialog.Builder informa = new AlertDialog.Builder(MainActivity.this);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar o menu = isso adiciona itens à barra de ação, se estiver presente.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            shareText();
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareText() {
        // cria a intent e define a ação
        Intent intent = new Intent(Intent.ACTION_SEND);
        // tipo de conteúdo da intent
        intent.setType("text/plain");
        // string a ser enviada para outra intent
        intent.putExtra(Intent.EXTRA_TEXT, "Link para baixa o aplicativo (FARMACOVIGILÂNCIA): https://mouseweb.com.br/sis/fvg/fvg.apk" );
        // inicia a intent
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String PREF_NAME = "LoginActivityPreferences";

        int id = item.getItemId();

        if (id == R.id.homeMenu){


        } else if (id == R.id.novoRelatoMenu) {
            Intent intent = new Intent (this, NovoRelatoActivity.class);
            startActivity(intent);

        } else if (id == R.id.listarRelatoMenu) {
            /*ProgressDialog.show ( MainActivity.this, "Aguarde",
                    "Por Favor Aguarde..." );*/
            Intent f = new Intent(MainActivity.this,ListarRelatosActivity.class);
            startActivity(f);

        } else if (id == R.id.hopitalMenu) {
            Intent intent = new Intent (this, GoogleApiMaps.class);
            startActivity(intent);

        } else if (id == R.id.ajudaMenu) {
            Intent intent = new Intent (this, AjudaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nortificadorMenu) {
            Intent intent = new Intent (this, PerfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.sairMenu) {

            SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear().commit();
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        count1 = sp1.getInt("count_1", 0);
        SharedPreferences.Editor editor = sp1.edit();
        editor.putInt("count_1", count1 + 1);
        editor.commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        sp1.unregisterOnSharedPreferenceChangeListener(callback);

        SharedPreferences.Editor editor = sp1.edit();
        editor.clear();
        editor.commit();

    }

}
