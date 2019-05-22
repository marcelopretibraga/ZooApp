package com.edu.fag.zooapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.edu.fag.zooapp.adapter.AnimalAdapter;
import com.edu.fag.zooapp.models.Animal;
import com.edu.fag.zooapp.models.Categoria;

import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    private EditText etDesc, etCode;
    private Spinner spCategoria;
    private Button btSave, btCancel;
    private ListView lvAnimais;
    private ArrayAdapter<Categoria> categoriaAdapter = null;
    private Animal animal = null;
    private AnimalAdapter animalAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        loadCategories();
        loadList();
    }

    private void loadCategories() {
        categoriaAdapter = new ArrayAdapter<Categoria>(AnimalActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                Categoria.listAll(Categoria.class));
        spCategoria.setAdapter(categoriaAdapter);
    }

    private void loadComponents() {
        etDesc = findViewById(R.id.etDesc);
        etCode = findViewById(R.id.etCode);
        spCategoria = findViewById(R.id.spCategoria);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);
        lvAnimais = findViewById(R.id.lvAnimais);
        loadEvents();
        initAnimal();
    }

    private void initAnimal() {
        Animal last = Animal.last(Animal.class);
        //Last pega o ultimo registro e Soma um
        int code = last != null ? last.getCodigo()+1 : 1;
        etCode.setText(String.valueOf(code));
    }


    private void loadEvents() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        lvAnimais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                animal = (Animal) animalAdapter.getItem(position);
                btSave.setText(R.string.lbUpdate);
                setFields();
            }
        });

        lvAnimais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Animal animalDel = (Animal) animalAdapter.getItem(position);
                AlertDialog.Builder alertConfirmacao = new AlertDialog.Builder(AnimalActivity.this);
                alertConfirmacao.setTitle("Confirmação Exclusão");
                alertConfirmacao.setMessage("Deseja Realmente excluir o Registro ???");
                alertConfirmacao.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        animalDel.delete();
                        loadList();
                    }
                });

                alertConfirmacao.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertConfirmacao.show();

                return true;
            }
        });
    }

    private void setFields() {
        etCode.setText(String.valueOf(animal.getCodigo()));
        etDesc.setText(animal.getDescricao());
        spCategoria.setSelection(categoriaAdapter.getPosition(animal.getCategoria()));
    }

    private void save() {
        if (animal != null){ // edição
            animal.setDescricao(etDesc.getText().toString());
            animal.setCategoria((Categoria) spCategoria.getSelectedItem());
            animal.setCodigo(Integer.parseInt(etCode.getText().toString()));
            animal.update();
            animal = null;
            btSave.setText(R.string.lbSave);
        }else {
            animal = new Animal();
            animal.setDescricao(etDesc.getText().toString());
            animal.setCategoria((Categoria) spCategoria.getSelectedItem());
            animal.setCodigo(Integer.parseInt(etCode.getText().toString()));
            animal.save();//Metodo responsável por salvar o registro
        }
        loadList();
        clearFields();
    }

    private void clearFields() {
        etDesc.setText("");
        initAnimal();
    }

    private void loadList(){
        animalAdapter = new AnimalAdapter(AnimalActivity.this,
                Animal.listAll(Animal.class, "codigo"));
        lvAnimais.setAdapter(animalAdapter);
    }

}
