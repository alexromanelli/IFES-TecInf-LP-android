package br.edu.ifes.controleacademico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListagemAlunosActivity extends AppCompatActivity {

    private ArrayList<String> listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_alunos);

        listaAlunos = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, listaAlunos);
        ListView listViewAlunos = (ListView)findViewById(R.id.listViewAlunos);
        listViewAlunos.setAdapter(adapter);

        TextView textViewVazio = (TextView)findViewById(R.id.textViewVazio);

        if (listaAlunos.size() > 0) { // ha dados para exibir
            listViewAlunos.setVisibility(View.VISIBLE);
            textViewVazio.setVisibility(View.GONE);
        } else { // nao ha dados para exibir
            listViewAlunos.setVisibility(View.GONE);
            textViewVazio.setVisibility(View.VISIBLE);
        }
    }
}
