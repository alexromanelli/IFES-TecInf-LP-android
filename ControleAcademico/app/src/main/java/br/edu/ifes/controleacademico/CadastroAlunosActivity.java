package br.edu.ifes.controleacademico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import br.edu.ifes.controleacademico.dados.Aluno;

public class CadastroAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_alunos);

        final Spinner spinnerSexo = (Spinner) findViewById(R.id.spinnerSexo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        Button buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextMatricula = (EditText) findViewById(R.id.editTextMatricula);
                String matricula = editTextMatricula.getText().toString();
                EditText editTextNome = (EditText) findViewById(R.id.editTextNome);
                String nome = editTextNome.getText().toString();
                EditText editTextDataNascimento = (EditText) findViewById(R.id.editTextDataNascimento);

                DateFormat df = DateFormat.getDateInstance();
                Date dataNascimento = null;
                try {
                    dataNascimento = df.parse(editTextDataNascimento.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace(); // <-------------------- TRATAR EXCEÇAO
                }

                char sexo;
                if (((CharSequence) spinnerSexo.getSelectedItem()).toString().equals("Masculino"))
                    sexo = 'M';
                else
                    sexo = 'F';

                EditText editTextCpf = (EditText) findViewById(R.id.editTextCpf);
                long cpf = Long.parseLong(editTextCpf.getText().toString());

                EditText editTextTelefone = (EditText) findViewById(R.id.editTextTelefone);
                String telefone = editTextTelefone.getText().toString();

                EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
                String email = editTextEmail.getText().toString();

                Aluno aluno = new Aluno(matricula, nome, dataNascimento, sexo, cpf, telefone, email);

                Intent intent = new Intent();
                intent.putExtra(ListagemAlunosActivity.CHAVE_ALUNO, aluno);
                setResult(ListagemAlunosActivity.CODIGO_SALVAR, intent);
                finish();
            }
        });

        Aluno alunoParaEditar = (Aluno) getIntent().getSerializableExtra(ListagemAlunosActivity.CHAVE_ALUNO);
        if (alunoParaEditar != null) {
            exibirRegistroNaTela(alunoParaEditar);
        }
    }

    private void exibirRegistroNaTela(Aluno aluno) {
        EditText editTextMatricula = (EditText) findViewById(R.id.editTextMatricula);
        EditText editTextNome = (EditText) findViewById(R.id.editTextNome);
        EditText editTextDataNascimento = (EditText) findViewById(R.id.editTextDataNascimento);
        EditText editTextCpf = (EditText) findViewById(R.id.editTextCpf);
        Spinner spinnerSexo = (Spinner) findViewById(R.id.spinnerSexo);
        EditText editTextTelefone = (EditText) findViewById(R.id.editTextTelefone);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        editTextMatricula.setText(aluno.getMatricula());
        editTextNome.setText(aluno.getNome());

        DateFormat df = DateFormat.getDateInstance();
        editTextDataNascimento.setText(df.format(aluno.getDataNascimento()));

        editTextCpf.setText(Long.toString(aluno.getCpf()));

        for (int i = 0; i < spinnerSexo.getAdapter().getCount(); i++) {
            CharSequence item = (CharSequence) spinnerSexo.getAdapter().getItem(i);
            if (item.toString().charAt(0) == aluno.getSexo())
                spinnerSexo.setSelection(i);
        }

        editTextTelefone.setText(aluno.getTelefone());
        editTextEmail.setText(aluno.getEmail());
    }
}
