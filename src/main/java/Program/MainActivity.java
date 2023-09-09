package Program;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myexpenses.R;

import java.util.List;

import Cadastro.model.Renda;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtonCadastro();
    }

    private void setupButtonCadastro() {
        Button btnCadastroFarm = findViewById(R.id.btnCadastro);
        btnCadastroFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificar_conexão_e_ir_para_proxima_tela();

            }

            private void verificar_conexão_e_ir_para_proxima_tela() {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();

                // Para fazer conexão com a internet
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

                    Intent intent = new Intent(MainActivity.this, Cadastro.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}