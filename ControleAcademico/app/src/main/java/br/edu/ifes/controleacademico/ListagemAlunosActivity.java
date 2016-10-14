package br.edu.ifes.controleacademico;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.edu.ifes.controleacademico.dados.Aluno;

public class ListagemAlunosActivity extends Activity {

    public static final int CODIGO_INSERIR = 0;
    public static final int CODIGO_ALTERAR = 1;
    public static final int CODIGO_CANCELAR = 0;
    public static final int CODIGO_SALVAR = 1;

    public static final String CHAVE_ALUNO = "aluno";
    public static final int MENU_ID_EXCLUIR = 1;

    private ArrayList<Aluno> listaAlunos;
    private ArrayAdapter<Aluno> adapter;
    private int posicaoItemEmEdicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_alunos);

        listaAlunos = new ArrayList<>();

        // inclusao de dois registros para testes
        GregorianCalendar calendar = new GregorianCalendar(1986, 11, 24);
        listaAlunos.add(new Aluno("20152IN011", "Bruno Mars", calendar.getTime(), 'M',
                12345678901L, "(28)9555-5555", "bruno@mail.com"));
        calendar = new GregorianCalendar(1992, 3, 2);
        listaAlunos.add(new Aluno("20152IN012", "Brunela Machado de Assis", calendar.getTime(), 'M',
                12345678901L, "(28)9555-5555", "bruno@mail.com"));

        // configuraçao do adaptador de dados para exibiçao no listViewAlunos
        adapter = new ArrayAdapter<Aluno>(this,
                android.R.layout.simple_list_item_1, listaAlunos);
        ListView listViewAlunos = (ListView)findViewById(R.id.listViewAlunos);
        listViewAlunos.setAdapter(adapter);

        // definiçao do tratamento de evento de clique em item da lista de alunos
        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoItemEmEdicao = position;
                Intent intent = new Intent(ListagemAlunosActivity.this, CadastroAlunosActivity.class);
                Aluno alunoParaEditar = listaAlunos.get(position);
                intent.putExtra(CHAVE_ALUNO, alunoParaEditar);
                startActivityForResult(intent, CODIGO_ALTERAR);
            }
        });

        // configuraçao da exibiçao de lista (se houver dados) ou de textview indicando ausencia
        // de dados
        TextView textViewVazio = (TextView)findViewById(R.id.textViewVazio);
        if (listaAlunos.size() > 0) { // ha dados para exibir
            listViewAlunos.setVisibility(View.VISIBLE);
            textViewVazio.setVisibility(View.GONE);
        } else { // nao ha dados para exibir
            listViewAlunos.setVisibility(View.GONE);
            textViewVazio.setVisibility(View.VISIBLE);
        }

        // configuraçao do clique no botao "Inserir aluno"
        Button buttonInserirAluno = (Button)findViewById(R.id.buttonInserirAluno);
        buttonInserirAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListagemAlunosActivity.this, CadastroAlunosActivity.class);
                startActivityForResult(intent, CODIGO_INSERIR);
            }
        });

        registerForContextMenu(listViewAlunos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, MENU_ID_EXCLUIR, Menu.NONE, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_ID_EXCLUIR) {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            listaAlunos.remove(info.position);
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODIGO_INSERIR:
                if (resultCode == CODIGO_SALVAR) {
                    Aluno aluno = (Aluno) data.getSerializableExtra(CHAVE_ALUNO);
                    listaAlunos.add(aluno);
                    adapter.notifyDataSetChanged(); // para atualizar exibiçao da lista
                }
                break;
            case CODIGO_ALTERAR:
                if (resultCode == CODIGO_SALVAR) {
                    Aluno aluno = (Aluno) data.getSerializableExtra(CHAVE_ALUNO);
                    listaAlunos.set(posicaoItemEmEdicao, aluno);
                    adapter.notifyDataSetChanged(); // para atualizar exibiçao da lista
                }
                break;
        }
    }
}
