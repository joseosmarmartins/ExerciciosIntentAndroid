package com.jose.aulaead;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executaAtividade(R.id.phone, new Intent(Intent.ACTION_CALL, Uri.parse("tel:99999999")));
        executaAtividade(R.id.browser, new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com")));
        executaAtividade(R.id.contacts, new Intent(Intent.ACTION_PICK, Uri.parse("content://com.android.contacts/contacts/")));
        executaAtividade(R.id.maps, new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-17.8126106, -49.2127548")));
        executaAtividade(R.id.camera, new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        executaAtividade(R.id.search, new Intent(Intent.ACTION_VIEW));
    }

    private void executaAtividade(final int id, final Intent i) {
        Button button = ((Button) findViewById(id));
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (id == R.id.search) {
                        TextView texto = (TextView) findViewById(R.id.searchText);
                        String textoPesquisar = texto.getText().toString().replace(" ", "+").trim();

                        Uri uri = Uri.parse("http://www.google.com/#q=" + textoPesquisar);
                        i.setData(uri);

                        startActivity(i);
                    }

                    if (id == R.id.contacts) {
                        startActivityForResult(i, retorno);
                    } else {
                        startActivity(i);
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            Toast.makeText(MainActivity.this, "Nenhum retorno", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == retorno) {
            Uri contatoSelecionado = data.getData();
            Toast.makeText(MainActivity.this, "Contato" + contatoSelecionado, Toast.LENGTH_SHORT).show();
        }
    }
}
