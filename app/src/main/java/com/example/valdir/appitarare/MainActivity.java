package com.example.valdir.appitarare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity{

    private Button btActivity;

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

        btActivity = (Button) findViewById(R.id.btn_inic);

        btActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(startCategorActivity);


            }
        });
    }

    //private void connectingToInternet() {

        //  URL githubSearchUrl = NetworksUtils.buildUrl();

        //    new GithubQueryTask().execute(githubSearchUrl);

        //     try {
            //   NetworksUtils.getResponseFromHttpUrl(githubSearchUrl);
        //  } catch (IOException e) {
        //   e.printStackTrace();
        // }
    }

    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    // public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        //  @Override
        //   protected String doInBackground(URL... params) {
        //      URL searchUrl = params[0];
        //     String githubSearchResults = null;
        //    try {
        //        githubSearchResults = NetworksUtils.getResponseFromHttpUrl(searchUrl);
        //    } catch (IOException e) {
        //       e.printStackTrace();
        //   }
    //   return githubSearchResults;


        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        //     @Override
    //     protected void onPostExecute(String githubSearchResults) {
    //        if (githubSearchResults != null && !githubSearchResults.equals("")) {
    //       }
    //  }
    //  }
