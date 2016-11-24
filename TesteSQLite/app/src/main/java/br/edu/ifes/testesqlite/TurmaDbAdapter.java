package br.edu.ifes.testesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by romanelli on 18/11/16.
 */

public class TurmaDbAdapter {

    public static final String ID = "_id";
    public static final String ABREVIACAO = "abreviacao";
    public static final String DESCRICAO = "descricao";
    public static final String ANO = "ano";
    public static final String SEMESTRE = "semestre";

    public static final String NOME_TABELA_TURMA = "turma";

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    private Context ctx;

    public TurmaDbAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public TurmaDbAdapter open() throws SQLiteException {
        dbHelper = new DatabaseHelper(ctx);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        database.close();
    }

    public Cursor selecionarTurmas() {
        String[] colunas = new String[] { ID, ABREVIACAO, DESCRICAO,
                ANO, SEMESTRE };
        return database.query(NOME_TABELA_TURMA, colunas,
                null, null, null, null, null);
    }

    public Cursor selecionarTurmaPorId(long id) {
        String[] colunas = new String[] { ID, ABREVIACAO, DESCRICAO,
                ANO, SEMESTRE };

        String clausulaWhere = ID + " = ?";
        String[] argumentosWhere = new String[] { Long.toString(id) };

        Cursor c = database.query(
                NOME_TABELA_TURMA,
                colunas,
                clausulaWhere,
                null, null, null, null);
        /*
        select _id, abreviacao, descricao, ano, semestre
        from turma
        where _id = ?
        ------------------------------------------------
        ? -> substituída pelo valor do parâmetro id
         */

        return c;
    }

    public long inserirTurma(Turma t) {
        /*
        insert into turma (abreviacao, descricao, ano, semestre)
        values ('fdjs','sdçlfkjass',2016,2)
         */
        ContentValues valores = new ContentValues();
        valores.put(ABREVIACAO, t.getAbreviacao());
        valores.put(DESCRICAO, t.getDescricao());
        valores.put(ANO, t.getAno());
        valores.put(SEMESTRE, t.getSemestre());

        long idGerado = database.insert(NOME_TABELA_TURMA, null, valores);
        return idGerado;
    }

    public boolean alterarTurma(Turma t) {
        ContentValues valores = new ContentValues();
        valores.put(ABREVIACAO, t.getAbreviacao());
        valores.put(DESCRICAO, t.getDescricao());
        valores.put(ANO, t.getAno());
        valores.put(SEMESTRE, t.getSemestre());

        String clausulaWhere = ID + " = ?";
        String[] argumentosWhere = new String[] { Long.toString(t.getId()) };

        int quantidadeRegistrosAlterados = database.update(
                NOME_TABELA_TURMA,
                valores,
                clausulaWhere,
                argumentosWhere);

        return quantidadeRegistrosAlterados == 1;
    }

    public boolean excluirTurma(Turma t) {
        /**
         * delete from turma where _id = ?
         */
        String[] argumentosWhere = new String[] { Long.toString(t.getId()) };
        int quantidadeRegistrosExcluidos =
                database.delete(NOME_TABELA_TURMA, "_id = ?", argumentosWhere);

        return quantidadeRegistrosExcluidos == 1;
    }
}
