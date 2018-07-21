package com.example.valdir.appitarare.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.Advertisement;
import com.example.valdir.appitarare.util.Constants;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private LinearLayout mLayout;
    private Context mContext;

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
        mContext = this;
        mProgressBar = findViewById(R.id.progressBar);
        mLayout = findViewById(R.id.layoutMain);

        Button btActivity = findViewById(R.id.btn_inic);
        Button btInsert = findViewById(R.id.btn_insert_fake);

        btActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, CategoryActivity.class);

                startActivity(startCategorActivity);
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                setLoading(true);
                FakeDataAdvertisement();
            }
        });
    }

    private void setLoading(boolean isLoading) {
        if(isLoading){
            mLayout.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        }else{
            mLayout.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private static DatabaseReference buildReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public void FakeDataAdvertisement() {

        DatabaseReference eventReference = buildReference();

        Advertisement newAdv = new Advertisement(
                "EMPRESA 8",
                "Venha conhecer nosso ambiente .....",
                "8h as 18h",
                "dinheiro e cartões",
                "15 3531- 1748",
                90,
                12312312,
                0,
                1);

        eventReference
                .child(Constants.CHILD_NAME_ANUNCIO)
                .push()
                .setValue(newAdv).onSuccessTask(new SuccessContinuation<Void, Object>(){
            @NonNull
            @Override
            public Task<Object> then(@Nullable Void aVoid) throws Exception {
                setLoading(false);

                Toast.makeText(mContext,
                        getString(R.string.insert_data_success),
                        Toast.LENGTH_LONG).show();

                return null;
            }
        });
    }
}