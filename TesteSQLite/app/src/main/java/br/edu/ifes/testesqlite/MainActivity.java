package br.edu.ifes.testesqlite;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_ID_EXCLUIR = 1;

    private TurmaDbAdapter dbAdapter;
    private Cursor cursor;
    private SimpleCursorAdapter adapterTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new TurmaDbAdapter(this);
        dbAdapter.open();
        carregarDados();

        ListView listViewTurmas = (ListView) findViewById(R.id.listViewTurmas);
        registerForContextMenu(listViewTurmas);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, MENU_ID_EXCLUIR, Menu.NONE, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_ID_EXCLUIR) {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            cursor.moveToPosition(info.position);

            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(TurmaDbAdapter.ID));
            String abreviacao = cursor.getString(
                    cursor.getColumnIndexOrThrow(TurmaDbAdapter.ABREVIACAO));
            String descricao = cursor.getString(
                    cursor.getColumnIndexOrThrow(TurmaDbAdapter.DESCRICAO));
            int ano = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TurmaDbAdapter.ANO));
            int semestre = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TurmaDbAdapter.SEMESTRE));

            Turma t = new Turma(id, abreviacao, descricao, ano, semestre);

            dbAdapter.excluirTurma(t);

            adapterTurma.notifyDataSetChanged();

            return true;
        }
        return false;
    }

    private void carregarDados() {
        cursor = dbAdapter.selecionarTurmas();
        startManagingCursor(cursor);
        String[] from = new String[] { TurmaDbAdapter.ABREVIACAO,
            TurmaDbAdapter.ANO, TurmaDbAdapter.SEMESTRE };
        int[] to = new int[] { R.id.abreviacao, R.id.ano,
            R.id.semestre };
        adapterTurma = new
                SimpleCursorAdapter(this, R.layout.item_lista_turma,
                    cursor, from, to);
        ListView listViewTurmas = (ListView) findViewById(R.id.listViewTurmas);
        listViewTurmas.setAdapter(adapterTurma);
    }
}
