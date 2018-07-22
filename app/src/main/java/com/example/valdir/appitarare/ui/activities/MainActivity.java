package com.example.valdir.appitarare.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.valdir.appitarare.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Criando uma lista,
          Precisa de um arquivo xml para inserir a referencia da lista
          Necessário um List<String> ou um Vector de String para inserir os dados
          Atraves do construtor do adapter seta a referencia, passando contexto, referencia xml e os dados
          por fim usa-se o ListView para alimentar a lista usando o setAdapter
        */

        Button btActivity = findViewById(R.id.btn_inic);

        btActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, CategoryActivity.class);

                startActivity(startCategorActivity);
            }
        });

    }
}