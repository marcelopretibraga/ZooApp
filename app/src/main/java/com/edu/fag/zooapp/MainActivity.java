package com.edu.fag.zooapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.edu.fag.zooapp.models.Animal;
import com.edu.fag.zooapp.models.Categoria;
import com.edu.fag.zooapp.models.Vacina;
import com.orm.SugarContext;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Spinner spCategoria;
    private ArrayAdapter<Categoria> categoriaAdapter;
    private Button btSalvar, btRemover, btVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SugarContext.init(this);//Responsável por iniciar o Sugar

        carregaComponentes();
        loadList();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void carregaComponentes() {
        spCategoria = findViewById(R.id.spCategoria);
        btRemover = findViewById(R.id.btCancel);
        btSalvar = findViewById(R.id.btSave);
        btVacina = findViewById(R.id.btVacina);
        carregaEventos();
    }

    private void carregaEventos(){
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        btRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        btVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadVacina();
            }
        });
    }

    private void loadVacina() {
        Animal animal = Animal.first(Animal.class);

        Vacina last = Vacina.last(Vacina.class);
        //Last pega o ultimo registro e Soma um
        int registro = last != null ? last.getRegistro()+1 : 1;

        Vacina vacina = new Vacina();
        vacina.setDsVacina("Vacina "+registro);
        vacina.setDtVacina(new Date());
        vacina.setPsAnimal(10.0);
        vacina.setQtVacina(0.5);
        vacina.setRegistro(registro);
        vacina.setDsObservacao("Obs "+registro);
        vacina.setAnimal(animal);
        vacina.save();

        animal = Animal.first(Animal.class);
        animal.getVacinaList();
        System.out.println(animal.getVacinaList());


    }

    private void save() {
        Categoria last = Categoria.last(Categoria.class);
        //Last pega o ultimo registro e Soma um
        int codigo = last != null ? last.getCodigo()+1 : 1;

        Categoria  categoria = new Categoria(codigo, "Categoria "+codigo, true);
        categoria.save();//Metodo responsável por salvar o registro

        loadList();
    }

    private void delete(){
        //Recupera o item selecionado e Remove
        Categoria catRemove = (Categoria) spCategoria.getSelectedItem();
        if (catRemove != null)
            catRemove.delete();
        loadList();
    }

    private void loadList(){
        List<Categoria> categorias = Categoria.listAll(Categoria.class, "codigo desc");
        categoriaAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,
                categorias);
        spCategoria.setAdapter(categoriaAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, CategoriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, AnimalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
