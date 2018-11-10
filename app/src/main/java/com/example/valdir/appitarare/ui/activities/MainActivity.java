package com.example.valdir.appitarare.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.data.SettingsFirebase;
import com.example.valdir.appitarare.model.News;
import com.example.valdir.appitarare.ui.adapters.CardAdapter;
import com.example.valdir.appitarare.util.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyViewNews;
    private CardAdapter mCardAdapter;
    private ArrayList<News> mListNews = new ArrayList<>();
    private LinearLayout mLiLayoutIconR1C1;
    private LinearLayout mLiLayoutIconR1C2;
    private LinearLayout mLiLayoutIconR2C1;
    private LinearLayout mLiLayoutIconR2C2;
    private LinearLayout mLiLayoutIconR3C1;
    private LinearLayout mLiLayoutIconR3C2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);

        mRecyViewNews = findViewById(R.id.rv_news_card);

        mRecyViewNews.setLayoutManager(layoutManager);
        mRecyViewNews.setHasFixedSize(true);

        mListNews.add(new News("Heliton do Valle visita obras dos bairros Jardim Pauliceia e Alvorada em Itararé (SP)",
                "\"O prefeito de Itararé (SP), Heliton do Valle, visitou no início do mês de setembro as obras de pavimentação do Jardim Pauliceia e acompanhou a construção de calçadas e de um ponto de ônibus no Jardim Alvorada em Itararé (SP). Ainda aproveitou para conversar com os servidores municipais e com a população.    O Jardim Pauliceia foi contemplado com recursos próprios, por intermédio do deputado federal Guilherme Mussi, no valor de R$ 175 mil reais para a pavimentação de trechos das ruas: Walton Pinto, Francisco Vicente da Silva, Honorato Gomes Gaya e Francisco Rodrigues da Costa. No total, o bairro ganhou 2.825 metros quadrados de asfalto, além de 191 metros de galeria de águas pluviais e 825 metros de guias com sarjetas. As obras já se encontram em processo final.  No bairro do Jardim Alvorada, a equipe de Serviços Municipais está construindo calçadas e um ponto de ônibus na rua Roberto Teodorico Cortês. Um investimento de cerca de R$ 3 mil com recursos próprios. A expectativa é que a obra seja finalizada até a próxima sexta-feira (14).    Para o prefeito, todas as obras são de suma importância, contudo o asfalto merece uma atenção especial, pois melhora, dentre outros aspectos, a saúde da população. “Estamos trabalhando diariamente para atingir o maior programa de pavimentação que Itararé vai receber, além da parte estética, o asfalto acaba com as doenças causadas pela poeira das estradas de terra”, explica.  Sobre o ponto de ônibus, Heliton enfatiza que a construção foi realizada para facilitar o trânsito e contribuir para com aqueles que fazem uso do transporte coletivo. “Implantamos-o para garantir mais segurança aos usuários de ônibus e pedestres que transitam pelo local. Além de facilitar o fluxo de carros no local”, destaca.  Com relação as visitas no bairros, o chefe do Executivo voltou a frisar que uma boa gestão não se faz somente no gabinete. \"Vou e irei a rua sempre. Esta gestão não é somente minha, mas nossa”, finaliza.\"",
                "05/10/2018",
                "http://www.itarare.sp.gov.br",
                "https://firebasestorage.googleapis.com/v0/b/itarare-1530419471926.appspot.com/o/noticias%2FO_bairro_ganhou_2825_metros_quadrados_de_asfalto_alm_de_191_metros_de_galeria_de_guas_pluviais_e_825_metros_de_guias_com_sarjetas_1.jpg?alt=media&token=e8ccff09-b132-4d99-b705-b554f731e515"));

        mListNews.add(new News("Gestão Heliton do Valle constrói calçada na Vila São João em Itararé (SP)",
                "\"O prefeito de Itararé (SP),",
                "05/10/2018",
                "http://www.itarare.sp.gov.br",
                "https://firebasestorage.googleapis.com/v0/b/itarare-1530419471926.appspot.com/o/noticias%2Fcalcadasaojoaoitarare.jpg?alt=media&token=b0677c38-6880-42d4-9d31-3b32d7e7ca47"));

        mListNews.add(new News("Prefeitura realiza trabalho de captação de pneus inservíveis",
                "Prefeitura de Itararé (SP) realiza trabalho de captação de pneus inservíveis",
                "05/10/2018",
                "http://www.itarare.sp.gov.br",
                "https://firebasestorage.googleapis.com/v0/b/itarare-1530419471926.appspot.com/o/noticias%2Fpneusitarare.jpeg?alt=media&token=cf30849b-9a45-4117-83c2-9b638404dd61"));


        mListNews.add(new News("Prefeito se reúne com gerência da Caixa Econômica Federal",
                "\"O asdfasdf,",
                "05/10/2018",
                "http://www.itarare.sp.gov.br",
                "https://firebasestorage.googleapis.com/v0/b/itarare-1530419471926.appspot.com/o/noticias%2Finstituio__uma_excelente_parceira.jpg?alt=media&token=d8791da4-c207-47e3-815b-1445289c7b36"));

        loadNews();

        /*Criando uma lista,
          Precisa de um arquivo xml para inserir a referencia da lista
          Necessário um List<String> ou um Vector de String para inserir os dados
          Atraves do construtor do adapter seta a referencia, passando contexto, referencia xml e os dados
          por fim usa-se o ListView para alimentar a lista usando o setAdapter
        */
        SettingsFirebase.getDataBase();

        Button btActivity = findViewById(R.id.btn_inic);
        Button btNews = findViewById(R.id.btn_news);
        mLiLayoutIconR1C1 = findViewById(R.id.LiLayoutR1C1);
        mLiLayoutIconR1C2 = findViewById(R.id.LiLayoutR1C2);
        mLiLayoutIconR2C1 = findViewById(R.id.LiLayoutR2C1);
        mLiLayoutIconR2C2 = findViewById(R.id.LiLayoutR2C2);
        mLiLayoutIconR3C1 = findViewById(R.id.LiLayoutR3C1);
        mLiLayoutIconR3C2 = findViewById(R.id.LiLayoutR3C2);


        mLiLayoutIconR1C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, SearchResultsActivity.class);

                String pesquisa = Constants.TEXT_SEARCH_R1C1;

                startCategorActivity.putExtra("SEARCH_ICON", pesquisa);

                startActivity(startCategorActivity);
            }
        });

        mLiLayoutIconR1C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, SearchResultsActivity.class);

                String pesquisa = Constants.TEXT_SEARCH_R1C2;

                startCategorActivity.putExtra("SEARCH_ICON", pesquisa);

                startActivity(startCategorActivity);
            }
        });

        mLiLayoutIconR2C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, SearchResultsActivity.class);

                String pesquisa = Constants.TEXT_SEARCH_R2C1;

                startCategorActivity.putExtra("SEARCH_ICON", pesquisa);

                startActivity(startCategorActivity);
            }
        });

        mLiLayoutIconR2C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, SearchResultsActivity.class);

                String pesquisa = Constants.TEXT_SEARCH_R2C2;

                startCategorActivity.putExtra("SEARCH_ICON", pesquisa);

                startActivity(startCategorActivity);
            }
        });

        mLiLayoutIconR3C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, SearchResultsActivity.class);

                String pesquisa = Constants.TEXT_SEARCH_R3C1;

                startCategorActivity.putExtra("SEARCH_ICON", pesquisa);

                startActivity(startCategorActivity);
            }
        });

        mLiLayoutIconR3C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, SearchResultsActivity.class);

                String pesquisa = Constants.TEXT_SEARCH_R3C2;

                startCategorActivity.putExtra("SEARCH_ICON", pesquisa);

                startActivity(startCategorActivity);
            }
        });


        btActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, CategoryActivity.class);

                startActivity(startCategorActivity);
            }
        });

        btNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCategorActivity =
                        new Intent(MainActivity.this, AllNewsActivity.class);

                startActivity(startCategorActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);


        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }

    private void loadNews() {
        if (mCardAdapter == null) {
            mCardAdapter = new CardAdapter(mListNews);
            mRecyViewNews.setAdapter(mCardAdapter);
        } else {
            mCardAdapter.notifyDataSetChanged();
        }
    }
}


