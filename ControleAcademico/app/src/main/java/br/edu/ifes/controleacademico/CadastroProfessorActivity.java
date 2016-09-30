package br.edu.ifes.controleacademico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CadastroProfessorActivity extends AppCompatActivity {

    private ArrayList<String> listaAreaAtuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        listaAreaAtuacao = new ArrayList<>();
        listaAreaAtuacao.add("Ciencia da Computaçao");
        listaAreaAtuacao.add("Engenharias");
        listaAreaAtuacao.add("Matematica");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaAreaAtuacao);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerAreaAtuacao = (Spinner)findViewById(R.id.spinnerAreaAtuacao);
        spinnerAreaAtuacao.setAdapter(adapter);
    }
}
