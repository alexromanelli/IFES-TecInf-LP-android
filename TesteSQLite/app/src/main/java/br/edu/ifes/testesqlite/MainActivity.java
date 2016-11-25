package br.edu.ifes.testesqlite;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private TurmaDbAdapter dbAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new TurmaDbAdapter(this);
        dbAdapter.open();
        carregarDados();
    }

    private void carregarDados() {
        cursor = dbAdapter.selecionarTurmas();
        startManagingCursor(cursor);
        String[] from = new String[] { TurmaDbAdapter.ABREVIACAO,
            TurmaDbAdapter.ANO, TurmaDbAdapter.SEMESTRE };
        int[] to = new int[] { R.id.abreviacao, R.id.ano,
            R.id.semestre };
        SimpleCursorAdapter adapterTurma = new
                SimpleCursorAdapter(this, R.layout.item_lista_turma,
                    cursor, from, to);
        ListView listViewTurmas = (ListView) findViewById(R.id.listViewTurmas);
        listViewTurmas.setAdapter(adapterTurma);
    }
}
