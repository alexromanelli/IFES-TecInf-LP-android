package br.edu.ifes.controleacademico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonCadastroAlunos = (Button)findViewById(R.id.buttonCadastroAlunos);
        buttonCadastroAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cadastro de alunos clicado",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CadastroAlunosActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCadastroCursos = (Button)findViewById(R.id.buttonCadastroCursos);
        buttonCadastroCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroCursosActivity.class);
                startActivity(intent);
            }
        });
    }
}
