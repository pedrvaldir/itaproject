package com.example.valdir.appitarare.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.data.SettingsFirebase;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu_white);
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Criando uma lista,
          Precisa de um arquivo xml para inserir a referencia da lista
          Necessário um List<String> ou um Vector de String para inserir os dados
          Atraves do construtor do adapter seta a referencia, passando contexto, referencia xml e os dados
          por fim usa-se o ListView para alimentar a lista usando o setAdapter
        */

        SettingsFirebase.getDataBase();


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
          switch( menuItem.getItemId()) {
              case R.id.nav_item_one :
                  Toast.makeText(this, "Clicked item one", Toast.LENGTH_SHORT).show();
                  break;
              case R.id.nav_item_two :
                  Toast.makeText(this, "Clicked item two", Toast.LENGTH_SHORT).show();
                  break;
              case R.id.nav_item_three :
                  Toast.makeText(this, "Clicked item three", Toast.LENGTH_SHORT).show();
                  break;
              case R.id.nav_item_four :
                  Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show();
                  break;
              default:
                  Toast.makeText(this, "Clicked other item", Toast.LENGTH_SHORT).show();
          }

      return false;
    }
}