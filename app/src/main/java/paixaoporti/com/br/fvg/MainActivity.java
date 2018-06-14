package paixaoporti.com.br.fvg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView novoRelato;
    private ImageView listarRelato;
    private ImageView hospitaisRelato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        novoRelato = (ImageView) findViewById(R.id.novoRelato);
        listarRelato = (ImageView) findViewById(R.id.listarRelato);
        hospitaisRelato = (ImageView) findViewById(R.id.hospitais);

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
        // Inflar o menu; isso adiciona itens à barra de ação, se estiver presente.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent (this, LoginActivity.class);
            startActivity(intent);
            System.exit(0);
            finish ();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.homeMenu){

//             teste fragment = new teste ();
//            FragmentTransaction fragmentTrasaction = getSupportFragmentManager().beginTransaction();
//            fragmentTrasaction.replace(R.id.pag_menu, fragment);
//            fragmentTrasaction.commit();

        } else if (id == R.id.novoRelatoMenu) {

            Intent intent = new Intent (this, NovoRelatoActivity.class);
            startActivity(intent);

        } else if (id == R.id.listarRelatoMenu) {

            Intent intent = new Intent (this, ListarRelatosActivity.class);
            startActivity(intent);

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

            Intent intent = new Intent (this, LoginActivity.class);
            startActivity(intent);
            System.exit(0);
            finish ();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
