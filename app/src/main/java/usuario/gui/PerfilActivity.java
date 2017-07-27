package usuario.gui;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

import usuario.dao.ContatoDao;
import usuario.dominio.ContatoEmergencia;
import usuario.negocio.SessaoUsuario;


public class PerfilActivity extends AppCompatActivity {
    private TextView tv_nome;
    //private TextView tv_endereco;
    private TextView tv_planoSaude;
    private TextView tv_contatoEmergencia1;
    private TextView tv_contatoEmergencia2;
    private TextView tv_contatoEmergencia3;
    private TextView tv_telefoneContatoEmergencia1;

    private Button btn_deletar;
    private ContatoEmergencia contatoExistente;
    private ContatoDao daoContato;
    private SessaoUsuario sessaoUsuario;



    ListView listViewContatos;
    Cursor  cursor;
    SimpleCursorAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Testes do listview, calma aí!


        /*
        List<ContatoEmergencia> cursos = todosOsContatos();

        ListView listaDeContatos = (ListView) findViewById(R.id.listaDeContatos);

        ArrayAdapter<ContatoEmergencia> adapter = new ArrayAdapter<ContatoEmergencia>(this,
                android.R.layout.simple_list_item_1, cursos);

        listaDeContatos.setAdapter(adapter);
        */
        tv_nome=(TextView)findViewById(R.id.tv_nome);
        //tv_endereco=(TextView)findViewById(R.id.tv_endereco);
        tv_planoSaude=(TextView)findViewById(R.id.tv_planoSaude);

        btn_deletar=(Button)findViewById(R.id.btn_deletarPerfil);

        sessaoUsuario = new SessaoUsuario(getApplicationContext());
        sessaoUsuario.iniciarSessao();

        btn_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sessaoUsuario = new SessaoUsuario(getApplicationContext());
        daoContato = new ContatoDao(getApplicationContext());
        sessaoUsuario.iniciarSessao();
        tv_nome.setText(sessaoUsuario.getPessoaLogada().getNome());
        tv_planoSaude.setText(sessaoUsuario.getPessoaLogada().getPlanoSaude());
        contatoExistente = daoContato.buscarContato(sessaoUsuario.getUsuarioLogado().getLogin());
        daoContato.buscarContato(sessaoUsuario.getUsuarioLogado().getLogin());
        criarLista();
    }
    public void criarLista(){
        listViewContatos = (ListView) findViewById(R.id.listaDeContatos);

        String[] from = {"nome","telefone"};
        int[] to = {R.id.txvContatoNome,R.id.txvContatoNumero};

        adpter = new SimpleCursorAdapter(getApplicationContext(), R.layout.modelo_listview_contatos, cursor, from, to, 0);

        //listViewContatos.setOnItemClickListener(this);

        listViewContatos.setAdapter(adpter);




    }
    public void editar(View view){
        startActivity(new Intent(this,EditarPerfilActivity.class ));
    }
}
