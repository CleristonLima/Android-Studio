package Program;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexpenses.R;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        TextView txtProximo = findViewById(R.id.txtProximo);
        txtProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificar_conexão_e_ir_para_proxima_tela();

            }

            private void verificar_conexão_e_ir_para_proxima_tela() {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();

                // Para fazer conexão com a internet
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

                    Intent intent = new Intent(Cadastro.this, MinhasDespesas.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Cadastro.this, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}