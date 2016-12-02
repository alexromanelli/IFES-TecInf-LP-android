package br.edu.ifes.testesqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CadastroTurma extends AppCompatActivity {

    private static final int OPCAO_ALTERAR = 1;
    private static final int OPCAO_INSERIR = 2;

    private int opcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        // verifica se há registro para exibir (operação de alteração de turma)
        if (getIntent().getExtras() != null) {
            long id = getIntent().getExtras().getLong(TurmaDbAdapter.ID);
            Cursor cursor = TurmaDbAdapter.getInstance().selecionarTurmaPorId(id);
            if (cursor.moveToFirst()) {
                String abreviacao = cursor.getString(
                        cursor.getColumnIndexOrThrow(TurmaDbAdapter.ABREVIACAO));
                String descricao = cursor.getString(
                        cursor.getColumnIndexOrThrow(TurmaDbAdapter.DESCRICAO));
                int ano = cursor.getInt(
                        cursor.getColumnIndexOrThrow(TurmaDbAdapter.ANO));
                int semestre = cursor.getInt(
                        cursor.getColumnIndexOrThrow(TurmaDbAdapter.SEMESTRE));

                Turma t = new Turma(id, abreviacao, descricao, ano, semestre);
                exibirRegistroNaTela(t);
                opcao = OPCAO_ALTERAR;
            }
        } else {
            opcao = OPCAO_INSERIR;
        }
    }

    private void exibirRegistroNaTela(Turma t) {
        // implementar...
    }
}
