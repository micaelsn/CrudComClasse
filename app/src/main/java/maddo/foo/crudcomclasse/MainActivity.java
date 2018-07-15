package maddo.foo.crudcomclasse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCriarContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriarContato = (Button) findViewById(R.id.btnCriarContato);
        btnCriarContato.setOnClickListener(new CreateContatoOnClickListener());

        contadorDeRegistros();
        atualizarListaDeContatos();

    }

    public void contadorDeRegistros(){

        String msg = "";

        int contador = new ContatoController(this).totalDeContatos();

        TextView txtContadorContatos = (TextView) findViewById(R.id.txtContadorContatos);

        if(contador == 0){

            msg = "Nenhum contato cadastrado.";

        }else if (contador ==1){

            msg = contador + " contato cadastrado.";

        }else {

            msg = contador + " contatos cadastrados.";

        }

        txtContadorContatos.setText(msg);

    }

    public void atualizarListaDeContatos() {

        LinearLayout linearLayoutRecords = (LinearLayout)
                findViewById(R.id.objetosContatos);
        linearLayoutRecords.removeAllViews();

        List<Contato> contatos = new ContatoController(this).listarContatos();

        if (contatos.size() > 0) {

            for (Contato obj : contatos) {

                int id = obj.getId(); // Chave PK da tabela
                String nome = obj.getNome();
                String email = obj.getEmail();

                String txtViewContatos = nome + " - " + email;

                TextView textViewContatoItem= new TextView(this);
                textViewContatoItem.setPadding(0, 10, 0, 10);
                textViewContatoItem.setText(txtViewContatos);
                textViewContatoItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewContatoItem);
                textViewContatoItem.setOnLongClickListener(new RetrieveOnLongClickListener());
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Nenhum Contato Cadastrado.");

            linearLayoutRecords.addView(locationItem);
        }

    }


}
