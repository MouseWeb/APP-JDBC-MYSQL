package paixaoporti.com.br.fvg;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.List;

import controller.NovoRelatoControle;
import impl.NovoRelatoDAO;
import util.Mail;

public class NovoRelatoActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIAVEIS DO COMPONETE DATE CALENDAR
    private static final String TAG = "activityNovoRelato";
    private TextView inicioDataNovoRelato;
    private DatePickerDialog.OnDateSetListener mostrarInicioDataRelatoSetListenerNovoRelato;
    private TextView terminoDataNovoRelato;
    private DatePickerDialog.OnDateSetListener mostrarTerminoDataRelatoSetListenerNovoRelato;
    private ImageView gravarNovoRelato;
    private ImageView codeBarraPaciente;
    private ImageView cancelarEventoADV;
    private EditText medicamentoNovoRelato, dosagemNovoRelato, descricaoNovoRelato, gravidadeNovoRelato;
    private int codigo;
    private ProgressBar progress;
    private static final String PREF_NAME = "LoginActivityPreferences";

    NovoRelatoDAO dao = new NovoRelatoDAO ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_novo_relato );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);       //Ativar o botão
        getSupportActionBar().setTitle("Novo Relato");          //Titulo para ser exibido na sua Action Bar em frente à seta

        inicioDataNovoRelato    = (TextView) findViewById ( R.id.textinicioTratamentoNovoRelato );
        terminoDataNovoRelato   = (TextView) findViewById ( R.id.textfimTratamentoNovoRelato );
        medicamentoNovoRelato   = (EditText) findViewById ( R.id.editmedicamentoNovoRelato );
        dosagemNovoRelato       = (EditText) findViewById ( R.id.editdosagemMedicamentoNovoRelato );
        descricaoNovoRelato     = (EditText) findViewById ( R.id.editdescricaoNovoRelato );
        gravidadeNovoRelato     = (EditText) findViewById ( R.id.editgravidadeNovoRelato );
        gravarNovoRelato        = (ImageView) findViewById ( R.id.imggravarNovoRelato );
        codeBarraPaciente       = (ImageView) findViewById ( R.id.imgcodeBarraPaciente );
        cancelarEventoADV       = (ImageView) findViewById ( R.id.imgcancelarEventoADV );
        progress                = findViewById(R.id.progress);

        progress.setVisibility(View.GONE);

        gravarNovoRelato.setOnClickListener( this );
        cancelarEventoADV.setOnClickListener ( this );

        inicioDataNovoRelato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get( Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NovoRelatoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mostrarInicioDataRelatoSetListenerNovoRelato,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable ( Color.TRANSPARENT));
                dialog.show();

                limparDateIni();

            }
        });

        mostrarInicioDataRelatoSetListenerNovoRelato = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                inicioDataNovoRelato.setText(date);
            }
        };

        terminoDataNovoRelato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get( Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NovoRelatoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mostrarTerminoDataRelatoSetListenerNovoRelato,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable ( Color.TRANSPARENT));
                dialog.show();

                limparDateTer();

            }
        });

        mostrarTerminoDataRelatoSetListenerNovoRelato = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                terminoDataNovoRelato.setText(date);

            }
        };

        final Activity activity = this;
        codeBarraPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats( IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Digitalizando");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("FragmentNovoRelato", "Digitalização cancelada");
                Toast.makeText(NovoRelatoActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {

                medicamentoNovoRelato.setText(result.getContents());
                Log.d("NovoRelatoActivity", "Digitalizado");
                Toast.makeText(this, "Paciente: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            //Isso é importante, caso contrário, o resultado não será passado para o Activity
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imggravarNovoRelato ) {

            validacao();

        }else if(v.getId () == R.id.imgcancelarEventoADV ){

            Intent f = new Intent(this,MainActivity.class);
            startActivity(f);

            finish ();

        }else{
            Toast.makeText ( NovoRelatoActivity.this, "Erro ao salvar Relato!", Toast.LENGTH_SHORT ).show ( );
        }
    }

    public void validacao() {

        String medicamentoCompleteGet   = medicamentoNovoRelato.getText ( ).toString ( );
        String dosagemMedicamentoGet    = dosagemNovoRelato.getText ( ).toString ( );
        String inicioDataGet            = inicioDataNovoRelato.getText ( ).toString ( );
        String gravidadeMedicamentoGet  = gravidadeNovoRelato.getText ( ).toString ( );
        String descricaoMedicamentoGet  = descricaoNovoRelato.getText ( ).toString ( );

        if ( temConexao(NovoRelatoActivity.this) == false ) {

            mostraAlerta();

        } else if ( medicamentoCompleteGet == null ||  medicamentoCompleteGet.equals ( "" ) ){
            medicamentoNovoRelato.setError( "Campo Obrigatorio" );

        } else if( dosagemMedicamentoGet == null || dosagemMedicamentoGet.equals ( "" ) ){
            dosagemNovoRelato.setError ( "Campo Obrigatorio" );

        } else if ( inicioDataGet == null || inicioDataGet.equals ( "" ) ){
            inicioDataNovoRelato.setError ( "Campo Obrigatorio" );

        } else if( gravidadeMedicamentoGet == null || gravidadeMedicamentoGet.equals ( "" ) ){
            gravidadeNovoRelato.setError ( "Campo Obrigatorio" );

        } else if( descricaoMedicamentoGet == null || descricaoMedicamentoGet.equals ( "" ) ){
            descricaoNovoRelato.setError ( "Campo Obrigatorio" );

        } else {
            salvarNovoRelato( );
            limparCampos();
        }

    }

    public void salvarNovoRelato() {

        /*progress.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {*/

                NovoRelatoControle n = new NovoRelatoControle ();

                SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

                String login = sp.getString("login", "");
                String senha = sp.getString("senha", "");

                dao.userFindById ( login, senha );

                n.setMedicaemnto ( medicamentoNovoRelato.getText().toString () );
                n.setDosagem ( dosagemNovoRelato.getText ().toString () );
                n.setDataInicio ( inicioDataNovoRelato.getText ().toString () );
                n.setDataTermino ( terminoDataNovoRelato.getText ().toString () );
                n.setGravidade ( gravidadeNovoRelato.getText ().toString () );
                n.setDescricao ( descricaoNovoRelato.getText ().toString () );

                dao.insertRelato ( n );

               /* runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        exibeTela();
                    }
                });
            }
        }).start();*/

        Toast.makeText ( NovoRelatoActivity.this, "Relato enviado com sucesso!", Toast.LENGTH_SHORT ).show ( );
        enviarEmail();
    }

    private void enviarEmail(){

        final String medicamento = medicamentoNovoRelato.getText().toString();
        final String dosagem = dosagemNovoRelato.getText().toString();
        final String inicioData = inicioDataNovoRelato.getText().toString();
        final String terminoData = terminoDataNovoRelato.getText().toString();
        final String gravidade = gravidadeNovoRelato.getText().toString();
        final String descricao = descricaoNovoRelato.getText().toString();


        //final String email = txtEmail.getText().toString();
        //final String email = "douglas.developer.app@gmail.com";

        final String subject = "Relato de evento adverso do merdicamento";
        final String body = "<b>" + " Medicamento: " + "</b>" + medicamento + "<br>" +
                            "<b>" + " Dosagem: " + "</b>" + dosagem + "<br>" +
                            "<b>" + " Data de Inicio: " + "</b>" + inicioData + "<br>" +
                            "<b>" + " Data de Término: " + "</b>" + terminoData + "<br>" +
                            "<b>" + " Gravidade: " + "</b>" + gravidade + "<br>" +
                            "<b>" + " Descrição: " + "</b>" + descricao + "<br>";

        new Thread(new Runnable(){
            @Override
            public void run() {
                Mail m = new Mail();

                NovoRelatoDAO dao = new NovoRelatoDAO ();
                SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

                String login = sp.getString("login", "");
                String senha = sp.getString("senha", "");

                dao.userFindById ( login, senha );

                List <NovoRelatoControle> list = dao.getMailUser ();

                for( NovoRelatoControle d : list ) {
                     //System.out.println ( d.getEmail () );
                     String[] toArr = {d.getEmail ()};
                     m.setTo(toArr);

                }

                //String[] toArr = {email};
                //m.setTo(d.getEmail);

                //m.setFrom("fvg@mouseweb.com.br"); //nome do email de quem enviar
                m.setSubject(subject);
                m.setBody(body);

                try {
                    //m.addAttachment("pathDoAnexo");//anexo opcional
                    m.send();
                }

                catch(RuntimeException rex){ }//erro ignorado
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

        Toast.makeText(getApplicationContext(), "Relato enviado para o email!", Toast.LENGTH_SHORT).show();

    }

    public void limparCampos() {
        medicamentoNovoRelato.setText("");
        dosagemNovoRelato.setText("");
        gravidadeNovoRelato.setText("");
        descricaoNovoRelato.setText("");
        inicioDataNovoRelato.setText("");
        terminoDataNovoRelato.setText("");
        this.codigo = 0;
    }

    public void limparDateIni() {
        inicioDataNovoRelato.setText("");
    }

    public void limparDateTer() {
        terminoDataNovoRelato.setText("");
    }

    private boolean temConexao(Context classe) {
        ConnectivityManager gerenciador = (ConnectivityManager) classe.getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo informacao = gerenciador.getActiveNetworkInfo();
        if ((informacao != null) && (informacao.isConnectedOrConnecting()) && (informacao.isAvailable())) {
            return true;
        }
        return false;
    }

    // Mostra a informação caso não tenha internet.
    private void mostraAlerta() {
        AlertDialog.Builder informa = new AlertDialog.Builder(NovoRelatoActivity.this);
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

   /* private void exibeTela() {
        progress.setVisibility(View.GONE);
        //Toast.makeText ( PerfilActivity.this, "Salvo com sucesso!!!", Toast.LENGTH_SHORT ).show ( );
    }*/
}
