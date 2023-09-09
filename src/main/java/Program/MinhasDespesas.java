package Program;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexpenses.R;

import java.util.ArrayList;

public class MinhasDespesas extends AppCompatActivity {

    private ListView listaMes;
    private TextView selectedItemText;
    private ListView addedItemsList;
    private ArrayList<String> selectedItems;
    private EditText edtRenda;
    private EditText edtAno;

    private int selectedMonthPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_despesas);

        listaMes = findViewById(R.id.lstMes);
        selectedItemText = findViewById(R.id.selectedItemText);
        addedItemsList = findViewById(R.id.addedItemsList);
        edtRenda = findViewById(R.id.edtRendaMensalTotal);
        edtAno = findViewById(R.id.edtAno);

        final ArrayList<String> mes = Meses();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, mes);
        listaMes.setAdapter(arrayAdapter);

        // Inicialize a lista de itens selecionados
        selectedItems = new ArrayList<>();

        listaMes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedMonthPosition = position;
                String selectedMonth = mes.get(position);
                selectedItemText.setText("Mês selecionado: " + selectedMonth);
            }
        });

        // Encontre o botão Adicionar
        Button btnAdicionar = findViewById(R.id.btnAdicionar);

        // Adicione um ouvinte de clique ao botão Adicionar
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedMonthPosition != -1) {
                    String renda = edtRenda.getText().toString();
                    String ano = edtAno.getText().toString();

                    // Verifique se a renda e o ano não estão vazios
                    if (!renda.isEmpty() && !ano.isEmpty()) {
                        String selectedMonth = mes.get(selectedMonthPosition);
                        selectedItems.add(selectedMonth + "/ " + ano + " - Renda : " + renda);
                        // Atualize a lista de itens adicionados
                        updateAddedItemsList();
                        edtRenda.setText("");
                        edtAno.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Preencha a renda e o ano antes de adicionar.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Selecione um mês antes de adicionar manualmente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<String> Meses() {
        ArrayList<String> mes = new ArrayList<>();
        mes.add("Janeiro");
        mes.add("Fevereiro");
        mes.add("Março");
        mes.add("Abril");
        mes.add("Maio");
        mes.add("Junho");
        mes.add("Julho");
        mes.add("Agosto");
        mes.add("Setembro");
        mes.add("Outubro");
        mes.add("Novembro");
        mes.add("Dezembro");
        return mes;
    }

    private void updateAddedItemsList() {
        ArrayAdapter<String> addedItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedItems);
        addedItemsList.setAdapter(addedItemsAdapter);
    }
}