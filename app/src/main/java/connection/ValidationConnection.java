package connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ValidationConnection {

    public boolean temConexao(Context classe) {
        ConnectivityManager gerenciador = (ConnectivityManager) classe.getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo informacao = gerenciador.getActiveNetworkInfo();

        if ((informacao != null) && (informacao.isConnectedOrConnecting()) && (informacao.isAvailable())) {
            return true;
        }

        return false;
    }

}
